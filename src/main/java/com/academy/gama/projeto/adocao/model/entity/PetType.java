package com.academy.gama.projeto.adocao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "petType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long id;

    @Column(name = "description")
    private String description;

}
