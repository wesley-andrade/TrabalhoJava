package br.grupointegrado.Trabalho_Java.repository;

import br.grupointegrado.Trabalho_Java.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
