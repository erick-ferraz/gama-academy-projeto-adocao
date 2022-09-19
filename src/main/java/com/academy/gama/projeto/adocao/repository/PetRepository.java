package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.entity.Pet;
import com.academy.gama.projeto.adocao.model.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findByPetType(PetType tipo);

}
