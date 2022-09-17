package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.entity.PetSex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSexRepository extends JpaRepository<PetSex, Long> {

    PetSex findByDescription(String Description);
}
