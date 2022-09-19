package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.PetSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSizeRepository extends JpaRepository<PetSize, Long> {

    PetSize findByDescription(String Description);
}
