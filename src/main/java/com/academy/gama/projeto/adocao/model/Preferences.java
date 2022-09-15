package com.academy.gama.projeto.adocao.model;

import com.academy.gama.projeto.adocao.model.enums.PetSize;
import com.academy.gama.projeto.adocao.model.enums.PetType;
import com.academy.gama.projeto.adocao.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "preferencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preferencias")
    private Long id;

    @Column(name = "tipo")
    private PetType petType;

    @Column(name = "porte")
    private PetSize petSize;

    @Column(name = "cor")
    private String color;

    @Column(name = "sexo")
    private Sex sex;

    @Column(name = "idade")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "id_adotante")
    private Adopter adopter;
}
