package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.PetDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.dto.PetResponseListDto;
import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterResponseDTO;
import com.academy.gama.projeto.adocao.model.Pet;
import com.academy.gama.projeto.adocao.model.PetType;
import com.academy.gama.projeto.adocao.repository.PetRepository;
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
    public PetResponseDto createPet(PetDto pet){
        Pet petEntity = Pet.builder()
                .name(pet.getName())
                .petType(petTypeService.getPetType(pet.getPetType()))
                .petSize(petSizeService.getPetSize(pet.getPetSize()))
                .petColor(petColorService.getPetColor(pet.getColor()))
                .petSex(petSexService.getPetSex(pet.getSex()))
                .age(pet.getAge())
                .build();
        petRepository.save(petEntity);
        return new PetResponseDto(petEntity);
    }

    public List<PetResponseDto> list() {
        return petRepository.findAll().stream()
                .map(PetResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetResponseDto> getPetByType(String tipo) {
        PetType petType = petTypeService.getPetType(tipo);
        return petRepository.findByPetType(petType).stream()
                .map(PetResponseDto::new)
                .collect(Collectors.toList());
    }
}
