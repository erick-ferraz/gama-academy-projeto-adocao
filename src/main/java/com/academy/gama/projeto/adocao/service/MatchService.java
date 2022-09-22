package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.MatchDto;
import com.academy.gama.projeto.adocao.dto.PetResponseDto;
import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterResponseDTO;
import com.academy.gama.projeto.adocao.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.academy.gama.projeto.adocao.utils.Constants.INVALID_CPF;
import static com.academy.gama.projeto.adocao.utils.Validations.*;

@Service
public class MatchService {
	
    AdopterService adopterService;
    PreferencesService preferencesService;
    PetServiceImpl petService;
    PetRepository petRepository;

    public MatchService(AdopterService adopterService, PreferencesService preferencesService,
                        PetServiceImpl petService, PetRepository petRepository) {
        this.adopterService = adopterService;
        this.preferencesService = preferencesService;
        this.petService = petService;
        this.petRepository = petRepository;
    }

    public MatchDto performMatch(String cpf) {
        validateString(cpf, INVALID_CPF);

        AdopterResponseDTO adopterDto = adopterService.getAdopterByCpf(cpf);
        List<PrefsWithAdopterResponseDTO> adopterPrefs = preferencesService.getByAdopterCpf(cpf);
        List<PetResponseDto> petsDto = petService.list();
        Set<PetResponseDto> matchedPets = new HashSet<>();

        for (PrefsWithAdopterResponseDTO pref : adopterPrefs) {
            matchedPets.addAll(petsDto.stream().filter(pet -> pet.getPetSize().equals(pref.getPortePet()) &&
                pet.getPetType().equals(pref.getTipoPet()) && pet.getAge().equals(pref.getIdade()))
                    .collect(Collectors.toSet()));
        }

        return MatchDto.builder()
                .cpfAdotante(cpf)
                .nomeAdotante(adopterDto.getNome())
                .petsQueDeramMatch(new ArrayList<>(matchedPets))
                .build();
    }
}
