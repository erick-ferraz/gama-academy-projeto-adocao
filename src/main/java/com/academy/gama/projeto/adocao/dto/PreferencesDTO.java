package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.Preferences;
import com.academy.gama.projeto.adocao.model.enums.PetSize;
import com.academy.gama.projeto.adocao.model.enums.PetType;
import com.academy.gama.projeto.adocao.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesDTO {

    private PetType tipoPet;
    private PetSize portePet;
    private String cor;
    private Sex sexo;
    private Integer idade;

    public PreferencesDTO(Preferences prefs) {
        this.tipoPet = prefs.getPetType();
        this.portePet = prefs.getPetSize();
        this.cor = prefs.getColor();
        this.sexo = prefs.getSex();
        this.idade = prefs.getAge();
    }
}
