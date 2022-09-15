package com.academy.gama.projeto.adocao.repository;

import com.academy.gama.projeto.adocao.model.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

}
