package com.academy.gama.projeto.adocao.service;

import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterRequestDTO;
import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterResponseDTO;
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
public class PreferencesService {

    AdopterRepository adopterRepository;
    PreferencesRepository preferencesRepository;

    public PreferencesService(PreferencesRepository pr, AdopterRepository ar) {
        this.preferencesRepository = pr;
        this.adopterRepository = ar;
    }

    public List<PrefsWithAdopterResponseDTO> list() {
        return preferencesRepository.findAll().stream()
                .map(PrefsWithAdopterResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PrefsWithAdopterResponseDTO getById(String id) {
        validateString(id, INVALID_ID);
        if(!isIdConvertable(id))
            throw new InvalidDataException(INVALID_ID);

        Long longId = Long.valueOf(id);
        Preferences prefs = preferencesRepository.findById(longId)
                .orElseThrow(() -> new EntityNotFoundException(ID_NOT_FOUND));

        return new PrefsWithAdopterResponseDTO(prefs);
    }

    public List<PrefsWithAdopterResponseDTO> getByAdopterCpf(String cpf) {
        if(!adopterRepository.existsByCpf(cpf))
            throw new EntityNotFoundException(CPF_NOT_FOUND);

        return preferencesRepository.findAll().stream()
                .filter(prefs -> prefs.getAdopter().getCpf().equals(cpf))
                .map(PrefsWithAdopterResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrefsWithAdopterResponseDTO insert(PrefsWithAdopterRequestDTO dto) {
        if(!adopterRepository.existsByCpf(dto.getCpfAdotante()))
            throw new EntityNotFoundException(CPF_NOT_FOUND);

        validateAdopterPreferencesDto(dto);
        Adopter adopter = adopterRepository.findByCpf(dto.getCpfAdotante()).get();

        Preferences prefs = Preferences.builder()
                .petType(PetType.valueOf(dto.getTipoPet().toUpperCase()))
                .petSize(PetSize.valueOf(dto.getPortePet().toUpperCase()))
                .sex(Sex.valueOf(dto.getSexo().toUpperCase()))
                .color(dto.getCor())
                .age(dto.getIdade())
                .adopter(adopter)
                .build();

        preferencesRepository.save(prefs);
        return new PrefsWithAdopterResponseDTO(prefs);
    }

    @Transactional
    public PrefsWithAdopterResponseDTO updateById(String id, PrefsWithAdopterRequestDTO dto) {
        validateString(id, INVALID_ID);
        if(!isIdConvertable(id))
            throw new InvalidDataException(INVALID_ID);

        Long longId = Long.valueOf(id);
        if(!preferencesRepository.existsById(longId))
            throw new EntityNotFoundException(ID_NOT_FOUND);

        if(!adopterRepository.existsByCpf(dto.getCpfAdotante()))
            throw new EntityNotFoundException(CPF_NOT_FOUND);

        validateAdopterPreferencesDto(dto);
        Adopter adopter = adopterRepository.findByCpf(dto.getCpfAdotante()).get();
        Preferences prefs = preferencesRepository.findById(longId).get();

        prefs.setId(longId);
        prefs.setPetType(PetType.valueOf(dto.getTipoPet().toUpperCase()));
        prefs.setPetSize(PetSize.valueOf(dto.getPortePet().toUpperCase()));
        prefs.setSex(Sex.valueOf(dto.getSexo().toUpperCase()));
        prefs.setColor(dto.getCor());
        prefs.setAge(dto.getIdade());
        prefs.setAdopter(adopter);
        preferencesRepository.save(prefs);

        return new PrefsWithAdopterResponseDTO(prefs);
    }

    @Transactional
    public void deleteById(String id) {
        validateString(id, INVALID_ID);
        if(!isIdConvertable(id))
            throw new InvalidDataException(INVALID_ID);

        Long longId = Long.valueOf(id);
        if(!preferencesRepository.existsById(longId))
            throw new EntityNotFoundException(ID_NOT_FOUND);

        preferencesRepository.deleteById(longId);
    }
}
