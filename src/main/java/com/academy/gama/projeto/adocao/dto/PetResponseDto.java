package com.academy.gama.projeto.adocao.dto;

import com.academy.gama.projeto.adocao.model.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PetResponseDto {

    private String name;
    private String petType;
    private String petSize;
    private String color;
    private String sex;
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