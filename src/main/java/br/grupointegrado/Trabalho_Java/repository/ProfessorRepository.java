package br.grupointegrado.Trabalho_Java.repository;

import br.grupointegrado.Trabalho_Java.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
