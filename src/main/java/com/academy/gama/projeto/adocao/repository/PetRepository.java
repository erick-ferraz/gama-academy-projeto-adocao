package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.Pet;
import com.academy.gama.projeto.adocao.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetType(PetType tipo);

    List<Pet> findByPetTypeDescriptionAndPetColorDescriptionAndPetSexDescriptionAndPetSizeDescription(String type, String Color, String Sex, String size);

}
