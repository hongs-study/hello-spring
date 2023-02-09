package com.example.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.hellospring.domain.Member;
import com.example.hellospring.domain.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("테이블 데이터와 JPA 처리 검토")
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


    @DisplayName("자식데이터가 먼저 존재하고, 나중에 부모데이터를 JPA로 넣는다면?")
    @Nested
    class Test1 {
        // 사원테이블에 사원정보가 이미 있고, 팀테이블은 아직 비워있다. 이 때 JPA로 팀정보를 넣고 조회하면 제대로 관계가 맺어진 엔티티 결과가 나오는가?
        // .부모-자식 = 1:다 관계이다. 예시) 팀-팀원
        // .엔티티클래스는 빌더패턴 사용
        // .부모-자식 간 cascade 옵션 적용. 부모엔티티클래스의 자식필드에 cascade=ALL로 적용

        @BeforeEach
        void beforeEach() {
            //given:자식(사원)테이블에 데이터가 미리 존재한다. 부모(팀)테이블에는 데이터가 없다.
            System.out.println(">>>>>>>>>> SET INIT DATA start.=======");
            em.createNativeQuery("insert into temp_team (name) values (NULL)").executeUpdate();
            em.flush();
            Long lastTeamId = teamRepository.findFirstByOrderByIdDesc().get().getId() + 1;
            memberRepository.deleteAll();
            teamRepository.deleteAll();

            String member1 = "insert into temp_member(name, team_id) value('빈스', %d)".formatted(lastTeamId);
            String member2 = "insert into temp_member(name, team_id) value('엘빈', %d)".formatted(lastTeamId);
            String member3 = "insert into temp_member(name, team_id) value('아이샤', %d)".formatted(lastTeamId);
            em.createNativeQuery(member1).executeUpdate();
            em.createNativeQuery(member2).executeUpdate();
            em.createNativeQuery(member3).executeUpdate();
            em.flush();
            em.clear();
            System.out.println("======= SET INIT DATA end.<<<<<<<<<<");
        }

        @Deprecated
        @DisplayName("init test")
        @Test
        void testFindMember() {
            //given
            List<Member> all = memberRepository.findAll();

            //then
            assertThat(all).isNotEmpty();
            all.forEach(mem -> System.out.println("member_id=" + mem.getId()));
        }

        @DisplayName("1. 자식정보없이 생성할 때 - 출력내용 직접확인 요망")
        @Test
        void testJpaTest() {
            System.out.println("====================================================================================================");
            System.out.println("====================================================================================================");

            //given:자식정보없이 팀객체 생성 후 저장
            Team newTeam = Team.builder().name("개발3팀").build();
            Team savedTeam = teamRepository.save(newTeam);
            Long teamId = savedTeam.getId();
            em.flush();
            em.clear();

            //when
            Team foundTeam = teamRepository.findById(teamId).get();

            //then:3가지 부모 Entity 각각에 자식 Entity 컬렉션이 존재하는지 확인한다.
            System.out.println("==================================================");
            System.out.println("newTeam = " + newTeam);
            System.out.println("savedTeam = " + savedTeam);
            System.out.println("foundTeam = " + foundTeam);

            System.out.println("newTeam.getMemberList() = " + newTeam.getMemberList());     // 조회X. newTeam.getMemberList() = []
            System.out.println("savedTeam.getMemberList() = " + savedTeam.getMemberList()); // 조회X. savedTeam.getMemberList() = []
            System.out.println("foundTeam.getMemberList() = " + foundTeam.getMemberList()); // 조회O.
            // foundTeam.getMemberList() = [Member(id=1, team=Team(id=10, name=홍길동), name=홍길동), Member(id=2, team=Team(id=10, name=홍길동), name=성춘향), Member(id=3, team=Team(id=10, name=홍길동), name=아이샤)]
            System.out.println("==================================================");

            // 결론:팀을 저장하기 전/후에는 member가 영속데이터에 포함되어있지 않다. 반드시 find() 하여 조회쿼리가 실행되어야 한다.
        }

        @Disabled // todo 테스트하려면 @NonNull 어노테이션 제거 후 실행한다
        //@Rollback(value = false)
        @DisplayName("2. 자식데이터에 null을 넣어본다 - 출력내용 직접확인 요망")
        @Test
        void testJpaTest2() {
            System.out.println("====================================================================================================");
            System.out.println("====================================================================================================");

            //given:부모객체생성시 자식데이터에 null을 넣는다 -> 예상: cascade 옵션이 있으니까 자식테이블정보가 다 삭제되나?
            Team newTeam = Team.builder().name("개발3팀").memberList(null).build(); // 결과: ??? 왜 빈 값으로 들어가지 않을까?
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
            System.out.println("foundTeam.getMemberList() = " + foundTeam.getMemberList());
            // 조회O. foundTeam.getMemberList() = [Member(id=1, team=Team(id=10, name=홍길동), name=홍길동), Member(id=2, team=Team(id=10, name=홍길동), name=성춘향), Member(id=3, team=Team(id=10, name=홍길동), name=아이샤)]
            System.out.println("==================================================");

            // 결론: cascade 옵션을 지정했어도, null을 명시적으로 넣고 부모객체 save 해도 기존에 이미 테이블에 있떤 데이터가 지워지지 않는다.(cascade 있든없든 동일)
        }
    }

    @DisplayName("cascade 작동테스트")
    @Test
    void testJpaTest3() {
        System.out.println("====================================================================================================");
        System.out.println("====================================================================================================");

        /* 주의! 시도1.자식(팀원)정보 자동으로 안 들어가는 경우 - 순서가 틀렸음
        Team newTeam = Team.builder().name("개발3팀").build();
        // 자식에 부모객체 넣음 => cascade 작동 안 함.
        Member member1 = Member.builder().name("홍길동1").team(newTeam).build();
        Member member2 = Member.builder().name("홍길동2").team(newTeam).build();
        Team savedTeam = teamRepository.save(newTeam);*/

        // 시도2.부모에 자식들(컬렉션)을 넣음 => 이렇게 해야 cascade 작동함
        Team parent = Team.builder().name("개발3팀").build();
        Member child1 = Member.builder().name("홍길동1").team(parent).build();
        Member child2 = Member.builder().name("홍길동2").team(parent).build();
        parent.setMemberList(List.of(child1, child2)); // 부모에 자식 넣음.
        Team savedTeam = teamRepository.save(parent);
        Long teamId = savedTeam.getId();
        em.flush();
        em.clear();

        //when
        Team foundTeam = teamRepository.findById(teamId).get();

        //then
        System.out.println("==================================================");
        System.out.println("newTeam = " + parent);
        System.out.println("savedTeam = " + savedTeam);
        System.out.println("foundTeam = " + foundTeam);

        System.out.println("newTeam.getMemberList() = " + parent.getMemberList());
        System.out.println("savedTeam.getMemberList() = " + savedTeam.getMemberList());
        System.out.println("foundTeam.getMemberList() = " + foundTeam.getMemberList());

        assertThat(parent.getMemberList()).hasSize(2);
        assertThat(savedTeam.getMemberList()).hasSize(2);
        assertThat(foundTeam.getMemberList()).hasSize(2);
        System.out.println("==================================================");
    }

    @DisplayName("@Builder.Default && @NonNull 확인")
    @Test
    void testBuilder() {
        System.out.println("====================================================================================================");
        System.out.println("====================================================================================================");

        Team notMember = Team.builder().name("개발3팀").build();
        System.out.println("notMember.getMemberList() = " + notMember.getMemberList());

        assertThrows(NullPointerException.class, () -> {
            Team nullMember = Team.builder().name("개발3팀").memberList(null).build();
            System.out.println("nullMember.getMemberList() = " + nullMember.getMemberList());
        });
    }
}
