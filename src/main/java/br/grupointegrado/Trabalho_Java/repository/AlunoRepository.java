package br.grupointegrado.Trabalho_Java.repository;

import br.grupointegrado.Trabalho_Java.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
