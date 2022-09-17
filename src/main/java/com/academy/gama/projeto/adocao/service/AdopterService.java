package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.AdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.exception.EntityNotFoundException;
import com.academy.gama.projeto.adocao.model.entity.*;
import com.academy.gama.projeto.adocao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdopterService {

    private AdopterRepository adopterRepository;
    private PreferencesRepository preferencesRepository;

    @Autowired
    public PetSexService petSexService;

    @Autowired
    public PetColorService petColorService;

    @Autowired
    public PetSizeService petSizeService;

    @Autowired
    public PetTypeService petTypeService;

    public AdopterService(AdopterRepository ar, PreferencesRepository pr) {
        this.adopterRepository = ar;
        this.preferencesRepository = pr;
    }

    public List<AdopterResponseDTO> list() {
        List<Preferences> prefs = preferencesRepository.findAll();

        return adopterRepository.findAll().stream()
                .map(adopter -> new AdopterResponseDTO(adopter, prefs))
                .collect(Collectors.toList());
    }

    public AdopterResponseDTO getAdopterByCpf(String cpf) {
        Adopter adopter = adopterRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("O CPF informado não corresponde à nenhum adotante."));

        List<Preferences> prefs = preferencesRepository.findAll();
        return new AdopterResponseDTO(adopter, prefs);
    }

    @Transactional
    public AdopterResponseDTO insert(AdopterRequestDTO dto) {

        //validar cpf
        Adopter adopter = new Adopter();
        adopter.setName(dto.getNome());
        adopter.setAge(dto.getIdade());
        adopter.setCpf(dto.getCpf());
        adopter.setAddress(dto.getEndereco());
        Adopter adopterSaved = adopterRepository.save(adopter);

        adopter.setPreferences(dto.getPreferencias().stream().map(preferencesDTO -> {
            return Preferences.builder()
                    .id(adopterSaved.getId())
                    .size(petSizeService.getPetSize(preferencesDTO.getPortePet()))
                    .type(petTypeService.getPetType(preferencesDTO.getTipoPet()))
                    .sex(petSexService.getPetSex(preferencesDTO.getSexo()))
                    .color(petColorService.getPetColor(preferencesDTO.getCor()))
                    .age(preferencesDTO.getIdade())
                    .build();
        }).collect(Collectors.toList()));

        preferencesRepository.saveAll(adopter.getPreferences());

        return new AdopterResponseDTO(adopter, adopter.getPreferences());
    }

    @Transactional
    public AdopterResponseDTO updateByCpf(String cpf, AdopterRequestDTO dto) {
        if(!adopterRepository.existsByCpf(cpf))
            throw new EntityNotFoundException("O CPF informado não corresponde à nenhum adotante.");

        Adopter adopter = adopterRepository.findByCpf(cpf).get();
        adopter.setId(adopter.getId());
        adopter.setName(dto.getNome());
        adopter.setAge(dto.getIdade());
        adopter.setCpf(dto.getCpf());
        adopter.setAddress(dto.getEndereco());
        Adopter adopterSaved =  adopterRepository.save(adopter);

        Preferences adopterPrefs = preferencesRepository.findAll().stream()
                .filter(pref -> pref.getAdopter().getCpf().equals(adopter.getCpf()))
                .collect(Collectors.toList())
                .get(0);
        adopter.setPreferences(dto.getPreferencias().stream().map(preferencesDTO -> {
            return Preferences.builder()
                    .id(adopterSaved.getId())
                    .size(petSizeService.getPetSize(preferencesDTO.getPortePet()))
                    .type(petTypeService.getPetType(preferencesDTO.getTipoPet()))
                    .sex(petSexService.getPetSex(preferencesDTO.getSexo()))
                    .color(petColorService.getPetColor(preferencesDTO.getCor()))
                    .age(preferencesDTO.getIdade())
                    .build();
        }).collect(Collectors.toList()));

        preferencesRepository.saveAll(adopter.getPreferences());

        return new AdopterResponseDTO(adopter, List.of(adopterPrefs));
    }

    @Transactional
    public void deleteByCpf(String cpf) {
        if(!adopterRepository.existsByCpf(cpf))
            throw new EntityNotFoundException("O CPF informado não corresponde à nenhum adotante.");

        Adopter adopter = adopterRepository.findByCpf(cpf).get();

        Preferences adopterPrefs = preferencesRepository.findAll().stream()
                .filter(pref -> pref.getAdopter().getCpf().equals(adopter.getCpf()))
                .collect(Collectors.toList())
                .get(0);

        preferencesRepository.deleteById(adopterPrefs.getId());
        adopterRepository.deleteByCpf(cpf);
    }
}
