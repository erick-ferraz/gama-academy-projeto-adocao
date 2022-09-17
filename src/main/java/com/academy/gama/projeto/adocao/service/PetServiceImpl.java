package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.model.entity.*;
import com.academy.gama.projeto.adocao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PetResponseDto> list() {
        return petRepository.findAll().stream()
                .map(pet -> new PetResponseDto(pet))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetResponseDto> getPetByType(String tipo) {
        return petRepository.findByPetType(tipo).stream()
                .map(pet -> new PetResponseDto((Pet) pet))
                .collect(Collectors.toList());
    }
}
