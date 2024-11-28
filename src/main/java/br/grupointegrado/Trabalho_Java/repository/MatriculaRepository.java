package br.grupointegrado.Trabalho_Java.repository;

import br.grupointegrado.Trabalho_Java.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
