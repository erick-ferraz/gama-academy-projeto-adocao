package com.academy.gama.projeto.adocao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrefsWithAdopterRequestDTO {

    private String tipoPet;
    private String portePet;
    private String cor;
    private String sexo;
    private Integer idade;
    private String cpfAdotante;

}
