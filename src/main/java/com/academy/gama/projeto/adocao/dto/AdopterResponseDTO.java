package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.Adopter;
import com.academy.gama.projeto.adocao.model.Preferences;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdopterResponseDTO extends RepresentationModel<AdopterResponseDTO> {

    private String nome;
    private String cpf;
    private Integer idade;
    private String endereco;
    private List<PreferencesDTO> preferencias;

    public AdopterResponseDTO(Adopter adopter, List<Preferences> prefs) {
        this.nome = adopter.getName();
        this.cpf = adopter.getCpf();
        this.idade = adopter.getAge();
        this.endereco = adopter.getAddress();
        this.preferencias = prefs != null ? prefs.stream()
                .filter(pref -> pref.getAdopter().getCpf().equals(this.cpf))
                .map(PreferencesDTO::new)
                .collect(Collectors.toList()) : null;
    }
}
