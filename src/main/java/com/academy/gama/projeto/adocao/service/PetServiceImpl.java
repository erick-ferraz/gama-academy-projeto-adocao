package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.model.entity.Pet;
import com.academy.gama.projeto.adocao.model.entity.PetType;
import com.academy.gama.projeto.adocao.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    public PetRepository petRepository;

    @Autowired
    public PetSexService petSexService;

    @Autowired
    public PetColorService petColorService;

    @Autowired
    public PetSizeService petSizeService;

    @Autowired
    public PetTypeService petTypeService;

    @Override
    public Pet createPet(PetDto pet){

        Pet petEntity = Pet.builder()
                .name(pet.getName())
                .petType(petTypeService.getPetType(pet.getPetType()))
                .petSize(petSizeService.getPetSize(pet.getPetSize()))
                .petColor(petColorService.getPetColor(pet.getColor()))
                .petSex(petSexService.getPetSex(pet.getSex()))
                .age(pet.getAge())
                .build();

        return petRepository.save(petEntity);
    }

    public Optional<List<Pet>> list() {
        Optional<List<Pet>> petList = Optional.of(petRepository.findAll());
        return petList;
    }

    @Override
    public Optional<List<Pet>>  getPetByType(String tipo) {
        PetType petType = petTypeService.getPetType(tipo);
        return petRepository.findByPetType(petType);
    }
}
