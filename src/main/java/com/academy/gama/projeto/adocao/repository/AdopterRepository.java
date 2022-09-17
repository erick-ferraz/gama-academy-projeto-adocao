package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {
    Optional<Adopter> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    void deleteByCpf(String cpf);
}
