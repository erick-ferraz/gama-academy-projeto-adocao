package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.Pet;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetResponseDto extends RepresentationModel<PetResponseDto> {

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

    public PetResponseDto(Pet pet) {
        this.name = pet.getName();
        this.petType = pet.getPetType().getDescription();
        this.petSize = pet.getPetSize().getDescription();
        this.color = pet.getPetColor().getDescription();
        this.sex = pet.getPetSex().getDescription();
        this.age = pet.getAge();
    }

}
