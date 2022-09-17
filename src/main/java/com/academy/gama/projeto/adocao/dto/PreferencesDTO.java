package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.entity.Preferences;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesDTO {

    private String tipoPet;
    private String portePet;
    private String cor;
    private String sexo;
    private Integer idade;

    public PreferencesDTO(Preferences preferences) {
    }
}
