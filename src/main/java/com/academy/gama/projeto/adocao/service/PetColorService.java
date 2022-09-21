package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.model.PetColor;
import com.academy.gama.projeto.adocao.repository.PetColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetColorService {

    @Autowired
    public PetColorRepository petColorRepository;

    public PetColor getPetColor(String petColor){
        PetColor color = petColorRepository.findByDescription(petColor);
        return color;
    }
}