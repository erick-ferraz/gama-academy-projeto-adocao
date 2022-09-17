package com.academy.gama.projeto.adocao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "preferencias")

public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "id_type")
    @ManyToOne
    private PetType type;

    @JoinColumn(name = "id_size")
    @ManyToOne
    private PetSize size;

    @JoinColumn(name = "id_color")
    @ManyToOne
    private PetColor color;

    @JoinColumn(name = "id_sex")
    @ManyToOne
    private PetSex sex;

    @Column(name = "idade")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "id_adotante", insertable = false, updatable = false)
    private Adopter adopter;
}