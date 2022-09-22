package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.AdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.AdopterWithPreferencesDTO;
import com.academy.gama.projeto.adocao.exception.DuplicateException;
import com.academy.gama.projeto.adocao.exception.EntityNotFoundException;
import com.academy.gama.projeto.adocao.exception.InvalidDataException;
import com.academy.gama.projeto.adocao.model.Adopter;
import com.academy.gama.projeto.adocao.model.Preferences;
import com.academy.gama.projeto.adocao.model.enums.PetSize;
import com.academy.gama.projeto.adocao.model.enums.PetType;
import com.academy.gama.projeto.adocao.model.enums.Sex;
import com.academy.gama.projeto.adocao.repository.AdopterRepository;
import com.academy.gama.projeto.adocao.repository.PreferencesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.academy.gama.projeto.adocao.utils.Constants.*;
import static com.academy.gama.projeto.adocao.utils.Validations.*;

@Service
public class AdopterService {

    AdopterRepository adopterRepository;
    PreferencesRepository preferencesRepository;

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
                .orElseThrow(() -> new EntityNotFoundException(CPF_NOT_FOUND));

        List<Preferences> prefs = preferencesRepository.findAll();
        return new AdopterResponseDTO(adopter, prefs);
    }

    @Transactional
    public AdopterResponseDTO insert(AdopterWithPreferencesDTO dto) {
        validateAdopterWithPreferencesDto(dto);
        String cpf = dto.getCpf().replaceAll("\\D", "");

        if(adopterRepository.existsByCpf(cpf))
            throw new DuplicateException(DUPLICATED_ADOPTER);

        Adopter adopter = Adopter.builder()
                .name(dto.getNome())
                .age(dto.getIdade())
                .cpf(cpf)
                .address(dto.getEndereco())
                .build();

        adopterRepository.save(adopter);

        Preferences preferences = new Preferences();
        if(dto.getPreferencias() != null) {
            preferences.setPetType(PetType.valueOf(dto.getPreferencias().getTipoPet().toUpperCase()));
            preferences.setPetSize(PetSize.valueOf(dto.getPreferencias().getPortePet().toUpperCase()));
            preferences.setSex(Sex.valueOf(dto.getPreferencias().getSexo().toUpperCase()));
            preferences.setColor(dto.getPreferencias().getCor());
            preferences.setAge(dto.getPreferencias().getIdade());
        }
        preferences.setAdopter(adopter);

        preferencesRepository.save(preferences);

        return new AdopterResponseDTO(adopter, List.of(preferences));
    }

    @Transactional
    public AdopterResponseDTO updateByCpf(String cpf, AdopterRequestDTO dto) {
        validateString(cpf, INVALID_CPF);

        if(!adopterRepository.existsByCpf(cpf))
            throw new EntityNotFoundException(CPF_NOT_FOUND);

        if(dto == null)
            throw new InvalidDataException(NULL_ADOPTER);

        validateAdopterDto(dto.getNome(), dto.getCpf(), dto.getIdade(), dto.getEndereco());
        String cleanedCpf = dto.getCpf().replaceAll("\\D", "");

        Adopter adopter = adopterRepository.findByCpf(cpf).get();
        adopter.setId(adopter.getId());
        adopter.setName(dto.getNome());
        adopter.setAge(dto.getIdade());
        adopter.setCpf(cleanedCpf);
        adopter.setAddress(dto.getEndereco());
        adopterRepository.save(adopter);

        return new AdopterResponseDTO(adopter, null);
    }

    @Transactional
    public void deleteByCpf(String cpf) {
        validateString(cpf, INVALID_CPF);

        if(!adopterRepository.existsByCpf(cpf))
            throw new EntityNotFoundException(CPF_NOT_FOUND);

        Adopter adopter = adopterRepository.findByCpf(cpf).get();
        List<Preferences> adopterPrefs = preferencesRepository.findAll().stream()
                .filter(pref -> pref.getAdopter().getCpf().equals(adopter.getCpf()))
                .collect(Collectors.toList());

        adopterPrefs.forEach(pref -> preferencesRepository.deleteById(pref.getId()));
        adopterRepository.deleteByCpf(cpf);
    }
}
