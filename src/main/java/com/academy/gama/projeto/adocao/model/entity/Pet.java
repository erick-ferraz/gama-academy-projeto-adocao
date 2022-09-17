package com.academy.gama.projeto.adocao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    @Column(name = "nome")
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

    @Column(name = "idade")
    private Integer age;

}
