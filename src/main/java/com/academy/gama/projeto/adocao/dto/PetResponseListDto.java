package com.academy.gama.projeto.adocao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseListDto {

    List<PetResponseDto> petResponseDtos;
}
