package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.model.PetSize;
import com.academy.gama.projeto.adocao.repository.PetSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSizeService {

    @Autowired
    public PetSizeRepository petSizeRepository;

    public PetSize getPetSize(String petSize){
        PetSize size = petSizeRepository.findByDescription(petSize);
        return size;
    }
}
