package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.model.entity.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(PetDto pet);

    List<PetResponseDto> list();

    List<PetResponseDto> getPetByType(String tipo);
}
