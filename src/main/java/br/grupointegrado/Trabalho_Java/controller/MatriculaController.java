package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.MatriculaRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Aluno;
import br.grupointegrado.Trabalho_Java.model.Matricula;
import br.grupointegrado.Trabalho_Java.model.Turma;
import br.grupointegrado.Trabalho_Java.repository.AlunoRepository;
import br.grupointegrado.Trabalho_Java.repository.MatriculaRepository;
import br.grupointegrado.Trabalho_Java.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> save (@RequestBody @Valid MatriculaRequestDTO dto){
        Aluno aluno = this.alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado!"));

        Turma turma = this.turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado!"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        return ResponseEntity.ok(this.repository.save(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update (@PathVariable Integer id, @RequestBody @Valid MatriculaRequestDTO dto){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        Aluno aluno = this.alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado!"));

        Turma turma = this.turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado!"));

        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        return ResponseEntity.ok(this.repository.save(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada!"));

        this.repository.delete(matricula);
        return ResponseEntity.noContent().build();
    }
}
