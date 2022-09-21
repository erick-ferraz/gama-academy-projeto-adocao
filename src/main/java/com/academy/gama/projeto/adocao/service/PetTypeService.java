package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.exception.EntityNotFoundException;
import com.academy.gama.projeto.adocao.model.PetType;
import com.academy.gama.projeto.adocao.repository.PetTypeRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {

    @Autowired
    public PetTypeRepository petTypeRepository;

    public PetType getPetType(String petType){

        PetType type = petTypeRepository.findByDescription(petType);

        if(type == null){
            throw new EntityNotFoundException("Tipo de pet não encontrado");
        }

        return type;
    }

    public PetType getPetTypeOrCreate(String petType){
        PetType type = petTypeRepository.findByDescription(petType);

        if(type == null){
            PetType newType = petTypeRepository.save(PetType.builder().description(petType).build());
            return newType;
        }

        return type;
    }
}