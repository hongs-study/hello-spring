package com.example.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hellospring.domain.Member;
import com.example.hellospring.domain.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("1:다 관계 테이블이 이미 있고, '다'테이블에 이미 데이터가 있고, '1'테이블에는 데이터가 없을 때 '1'테이블에 데이터를 나중에 넣었을 때")
@Transactional
@SpringBootTest
public class TeamAndMemberTest {

    @Autowired
    private SpringDataJpaMemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    DataSource dataSource;
    @Autowired
    private EntityManager em;

    @DisplayName("DB에 자식데이터가 먼저 있을 때, 나중에 부모데이터를 JPA로 넣는다면?")
    @Nested
    class Test1 {

        @BeforeEach
        void beforeEach() {
            em.createNativeQuery("insert into temp_team (name) values (NULL)").executeUpdate();
            em.flush();
            Long lastTeamId = teamRepository.findFirstByOrderByIdDesc().get().getId() + 1;
            memberRepository.deleteAll();
            teamRepository.deleteAll();

            String member1 = """
            insert into temp_member(name, team_id) value('빈스', %d)
            """.formatted(lastTeamId);
            String member2 = """
            insert into temp_member(name, team_id) value('엘빈', %d)
            """.formatted(lastTeamId);
            String member3 = """
            insert into temp_member(name, team_id) value('아이샤', %d)
            """.formatted(lastTeamId);
            em.createNativeQuery(member1).executeUpdate();
            em.createNativeQuery(member2).executeUpdate();
            em.createNativeQuery(member3).executeUpdate();
            em.flush();
            em.clear();
        }

        @DisplayName("조회 테스트")
        @Test
        void testFindMember() {
            //given
            List<Member> all = memberRepository.findAll();

            //then
            assertThat(all).isNotEmpty();
            all.forEach(System.out::println);
        }

        @DisplayName("1. casecade 없음")
        @Test
        void testJpaTest() {
            //given
            Team newTeam = new Team();
            newTeam.setName("개발3팀");
            Team savedTeam = teamRepository.save(newTeam);
            Long teamId = savedTeam.getId();
            em.flush();
            em.clear();

            //when
            Team foundTeam = teamRepository.findById(teamId).get();

            //then
            System.out.println("==================================================");
            System.out.println("newTeam = " + newTeam);
            System.out.println("savedTeam = " + savedTeam);
            System.out.println("foundTeam = " + foundTeam);

            System.out.println("newTeam.getMemberList() = " + newTeam.getMemberList());     // 조회X. newTeam.getMemberList() = []
            System.out.println("savedTeam.getMemberList() = " + savedTeam.getMemberList()); // 조회X. savedTeam.getMemberList() = []
            System.out.println("foundTeam.getMemberList() = "
                + foundTeam.getMemberList()); // 조회O. foundTeam.getMemberList() = [Member(id=1, team=Team(id=10, name=홍길동), name=홍길동), Member(id=2, team=Team(id=10, name=홍길동), name=성춘향), Member(id=3, team=Team(id=10, name=홍길동), name=아이샤)]
            assertThat(foundTeam.getMemberList()).hasSize(3);
            // 결론:팀을 저장하기 전/후에는 member가 영속데이터에 포함되어있지 않다. 반드시 find() 하여 조회쿼리가 실행되어야 한다.
            System.out.println("==================================================");
        }
    }
}
