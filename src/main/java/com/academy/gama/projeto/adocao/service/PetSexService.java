package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.exception.EntityNotFoundException;
import com.academy.gama.projeto.adocao.model.PetSex;
import com.academy.gama.projeto.adocao.repository.PetSexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.academy.gama.projeto.adocao.utils.Constants.CPF_NOT_FOUND;

@Service
public class PetSexService {

    @Autowired
    public PetSexRepository petSexRepository;

    public PetSex getPetSex(String petSex){

            PetSex sex = petSexRepository.findByDescription(petSex)
                    .orElseThrow(() -> new EntityNotFoundException("Sexo do pet não encontrado, escolha entre: femea, macho ou indefinido"));
            return sex;
    }
}
