package com.academy.gama.projeto.adocao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdopterRequestDTO {

    private String nome;
    private String cpf;
    private Integer idade;
    private String endereco;

}
