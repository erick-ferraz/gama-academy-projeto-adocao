package com.academy.gama.projeto.adocao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "match")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pet_fk")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "id_adotante_fk")
    private Adopter adopter;
}
