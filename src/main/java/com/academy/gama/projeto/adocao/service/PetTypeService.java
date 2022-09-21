package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.model.PetType;
import com.academy.gama.projeto.adocao.repository.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {

    @Autowired
    public PetTypeRepository petTypeRepository;

    public PetType getPetType(String petType){
        PetType type = petTypeRepository.findByDescription(petType);
        return type;
    }
}