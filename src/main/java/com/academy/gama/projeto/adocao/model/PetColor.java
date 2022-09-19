package com.academy.gama.projeto.adocao.model;

import com.academy.gama.projeto.adocao.model.enums.PetSize;
import com.academy.gama.projeto.adocao.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "petColor")
@Data
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