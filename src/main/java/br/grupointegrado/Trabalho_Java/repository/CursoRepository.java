package br.grupointegrado.Trabalho_Java.repository;

import br.grupointegrado.Trabalho_Java.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
