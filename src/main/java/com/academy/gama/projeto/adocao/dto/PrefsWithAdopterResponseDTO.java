package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.Preferences;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrefsWithAdopterResponseDTO {

    private Long id;
    private String tipoPet;
    private String portePet;
    private String cor;
    private String sexo;
    private Integer idade;
    private String nomeAdotante;
    private String cpf;

    public PrefsWithAdopterResponseDTO(Preferences prefs) {
        this.id = prefs.getId();
        this.tipoPet = prefs.getPetType().toString().toLowerCase();
        this.portePet = prefs.getPetSize().toString().toLowerCase();
        this.cor = prefs.getColor();
        this.sexo = prefs.getSex().toString().toLowerCase();
        this.idade = prefs.getAge();
        this.nomeAdotante = prefs.getAdopter().getName();
        this.cpf = prefs.getAdopter().getCpf();
    }
}
