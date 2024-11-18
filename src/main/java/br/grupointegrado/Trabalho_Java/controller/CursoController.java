package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.CursoRequestDTO;
import br.grupointegrado.Trabalho_Java.model.Curso;
import br.grupointegrado.Trabalho_Java.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public Curso save(@RequestBody CursoRequestDTO dto){
        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        return this.repository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        return this.repository.save(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
