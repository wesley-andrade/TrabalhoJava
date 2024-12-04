package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.TurmaRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Curso;
import br.grupointegrado.Trabalho_Java.model.Turma;
import br.grupointegrado.Trabalho_Java.repository.CursoRepository;
import br.grupointegrado.Trabalho_Java.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody @Valid TurmaRequestDTO dto){
        Curso curso = this.cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        Turma turma = new Turma();

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCursoId(curso);

        return ResponseEntity.ok(this.repository.save(turma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody @Valid TurmaRequestDTO dto){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        Curso curso = this.cursoRepository.findById(dto.cursoId())
                        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCursoId(curso);

        return ResponseEntity.ok(this.repository.save(turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada!"));

        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
