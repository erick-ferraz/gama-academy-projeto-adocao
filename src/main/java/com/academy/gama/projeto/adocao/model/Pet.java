package com.academy.gama.projeto.adocao.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "id_type")
    @ManyToOne
    private PetType petType;

    @JoinColumn(name = "id_size")
    @ManyToOne
    private PetSize petSize;

    @JoinColumn(name = "id_color")
    @ManyToOne
    private PetColor petColor;

    @JoinColumn(name = "id_sex")
    @ManyToOne
    private PetSex petSex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "was_adopted")
    private boolean wasAdopted;

}
