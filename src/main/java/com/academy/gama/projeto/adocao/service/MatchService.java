package com.academy.gama.projeto.adocao.service;

import static com.academy.gama.projeto.adocao.utils.Constants.CPF_NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.gama.projeto.adocao.dto.AdopterResponseDTO;
import com.academy.gama.projeto.adocao.dto.PrefsWithAdopterResponseDTO;
import com.academy.gama.projeto.adocao.exception.EntityNotFoundException;
import com.academy.gama.projeto.adocao.model.Adopter;
import com.academy.gama.projeto.adocao.model.Preferences;
import com.academy.gama.projeto.adocao.repository.AdopterRepository;
import com.academy.gama.projeto.adocao.repository.PreferencesRepository;

@Service
public class MatchService {
	
	AdopterRepository adopterRepository;
    PreferencesRepository preferencesRepository;
    
    

	public AdopterResponseDTO getAdopterByCpf(String cpf) {
        Adopter adopter = adopterRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(CPF_NOT_FOUND));

        List<Preferences> prefs = preferencesRepository.findAll();
        return new AdopterResponseDTO(adopter, prefs);
    }
	

	
	
	
	

}
