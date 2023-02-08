package com.example.hellospring.repository;

import com.example.hellospring.domain.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findFirstByOrderByIdDesc();

}
