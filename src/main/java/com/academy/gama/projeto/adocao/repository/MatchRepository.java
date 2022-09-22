package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
