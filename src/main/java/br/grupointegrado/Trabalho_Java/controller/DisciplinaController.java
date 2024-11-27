package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.DisciplinaRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Curso;
import br.grupointegrado.Trabalho_Java.model.Disciplina;
import br.grupointegrado.Trabalho_Java.model.Professor;
import br.grupointegrado.Trabalho_Java.repository.CursoRepository;
import br.grupointegrado.Trabalho_Java.repository.DisciplinaRepository;
import br.grupointegrado.Trabalho_Java.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public Disciplina save (@RequestBody DisciplinaRequestDTO dto){
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado!"));

        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCursoId(curso);
        disciplina.setProfessorId(professor);

        return this.repository.save(disciplina);
    }

    @PutMapping("/{id}")
    public Disciplina update (@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado!"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCursoId(curso);
        disciplina.setProfessorId(professor);

        return this.repository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada!"));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
