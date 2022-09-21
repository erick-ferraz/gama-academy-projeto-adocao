package com.academy.gama.projeto.adocao.dto;

import java.util.List;

import com.academy.gama.projeto.adocao.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    private Long id;
    private String nomeAdotante;
    private String cpfAdotante;
    private List<Pet> petsQueDeramMatch;
}


