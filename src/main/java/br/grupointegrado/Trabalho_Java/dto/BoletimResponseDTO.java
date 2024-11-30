package br.grupointegrado.Trabalho_Java.dto;

import java.util.List;

public record BoletimResponseDTO(String alunoNome, List<NotaResponseDTO> notas) {
}
