package com.academy.gama.projeto.adocao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adotante")
public class Adopter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adotante")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "idade")
    private Integer age;

    @Column(name = "endereco")
    private String address;

    @OneToMany(mappedBy = "adopter", cascade = CascadeType.PERSIST)
    private List<Preferences> preferences;
}