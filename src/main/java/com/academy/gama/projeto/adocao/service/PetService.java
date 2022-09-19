package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.model.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet createPet(PetDto pet);

    Optional<List<Pet>> list();

    Optional<List<Pet>>  getPetByType(String tipo);
}
