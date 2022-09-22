package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.PetSex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetSexRepository extends JpaRepository<PetSex, Long> {

    Optional<PetSex> findByDescription(String Description);
}
