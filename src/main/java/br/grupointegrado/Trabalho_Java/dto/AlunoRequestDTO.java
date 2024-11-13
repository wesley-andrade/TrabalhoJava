package br.grupointegrado.Trabalho_Java.dto;

import java.time.LocalDate;

public record AlunoRequestDTO(String nome, String email, String matricula, LocalDate dataNascimento) {
}
