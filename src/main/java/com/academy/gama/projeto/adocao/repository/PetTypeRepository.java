package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {

    PetType findByDescription(String Description);
}
