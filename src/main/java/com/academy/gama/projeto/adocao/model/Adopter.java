package com.academy.gama.projeto.adocao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "adotante")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
