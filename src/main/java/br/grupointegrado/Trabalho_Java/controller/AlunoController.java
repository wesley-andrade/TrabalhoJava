package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.AlunoRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Aluno;
import br.grupointegrado.Trabalho_Java.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O aluno não foi encontrado!"));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody @Valid AlunoRequestDTO dto){
        Aluno aluno = new Aluno();

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setDataNascimento(dto.dataNascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody @Valid AlunoRequestDTO dto){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O aluno não foi encontrado!"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setDataNascimento(dto.dataNascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O aluno não foi encontrado!"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}
