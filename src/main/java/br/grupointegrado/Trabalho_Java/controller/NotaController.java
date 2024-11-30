package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.NotaRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Disciplina;
import br.grupointegrado.Trabalho_Java.model.Matricula;
import br.grupointegrado.Trabalho_Java.model.Nota;
import br.grupointegrado.Trabalho_Java.repository.DisciplinaRepository;
import br.grupointegrado.Trabalho_Java.repository.MatriculaRepository;
import br.grupointegrado.Trabalho_Java.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/notas")
public class NotaController {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id){
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public  ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto){
        Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));

        Nota nota = new Nota();
        nota.setMatriculaId(matricula);
        nota.setDisciplinaId(disciplina);
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());

        return ResponseEntity.ok(this.repository.save(nota));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Nota> save(@PathVariable Integer id, @RequestBody NotaRequestDTO dto){
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada!"));

        Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));

        nota.setMatriculaId(matricula);
        nota.setDisciplinaId(disciplina);
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());

        return ResponseEntity.ok(this.repository.save(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada!"));

        this.repository.delete(nota);
        return ResponseEntity.noContent().build();
    }
}
