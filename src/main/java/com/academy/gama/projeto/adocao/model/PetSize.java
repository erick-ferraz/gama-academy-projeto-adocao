package com.academy.gama.projeto.adocao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "petSize")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private Long id;

    @Column(name = "description")
    private String description;

}