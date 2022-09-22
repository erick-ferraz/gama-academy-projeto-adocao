package com.academy.gama.projeto.adocao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "petColor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private Long id;

    @Column(name = "description")
    private String description;

}