package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.model.PetSex;
import com.academy.gama.projeto.adocao.repository.PetSexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetSexService {

    @Autowired
    public PetSexRepository petSexRepository;

    public PetSex getPetSex(String petSex){
        PetSex sex = petSexRepository.findByDescription(petSex);
        return sex;
    }
}
