package com.academy.gama.projeto.adocao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PetDto {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("tipo")
    private String petType;

    @JsonProperty("tamanho")
    private String petSize;

    @JsonProperty("cor")
    private String color;

    @JsonProperty("sexo")
    private String sex;

    @JsonProperty("idade")
    private Integer age;

}
