package br.grupointegrado.Trabalho_Java.controller;

import br.grupointegrado.Trabalho_Java.dto.BoletimResponseDTO;
import br.grupointegrado.Trabalho_Java.dto.NotaRequestDTO;
import br.grupointegrado.Trabalho_Java.dto.NotaResponseDTO;
import br.grupointegrado.Trabalho_Java.model.*;
import br.grupointegrado.Trabalho_Java.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

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

    @GetMapping("/desempenho/aluno/{id}")
    public ResponseEntity<BoletimResponseDTO> findNotasByAlunoId(@PathVariable Integer id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        List<Matricula> matriculas = aluno.getMatriculas();

        List<NotaResponseDTO> notas = matriculas.stream()
                .flatMap(matricula -> matricula.getNotas().stream())
                .map(nota -> new NotaResponseDTO(
                        nota.getDisciplinaId().getNome(),
                        nota.getNota()))
                .collect(Collectors.toList());

        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        BoletimResponseDTO boletim = new BoletimResponseDTO(aluno.getNome(), notas);

        return ResponseEntity.ok(boletim);
    }

    @GetMapping("/desempenho/turma/{id}")
    public ResponseEntity<List<BoletimResponseDTO>> findNotasByTurmaId(@PathVariable Integer id) {
        // Buscar a turma pelo ID
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        List<Matricula> matriculas = turma.getMatriculas();

        List<BoletimResponseDTO> boletins = matriculas.stream()
                .map(matricula -> {
                    Aluno aluno = matricula.getAlunoId();
                    List<NotaResponseDTO> notas = matricula.getNotas().stream()
                            .map(nota -> new NotaResponseDTO(
                                    nota.getDisciplinaId().getNome(),
                                    nota.getNota()))
                            .collect(Collectors.toList());

                    return new BoletimResponseDTO(aluno.getNome(), notas);
                })
                .collect(Collectors.toList());

        if (boletins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(boletins);
    }

    @GetMapping("/desempenho/disciplina/{id}")
    public ResponseEntity<List<BoletimResponseDTO>> findNotasByDisciplinaId(@PathVariable Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        List<Nota> notas = disciplina.getNotas();

        List<BoletimResponseDTO> boletins = notas.stream()
                .map(nota -> {
                    Aluno aluno = nota.getMatriculaId().getAlunoId();
                    List<NotaResponseDTO> notaResponseDTOs = List.of(new NotaResponseDTO(
                            nota.getDisciplinaId().getNome(),
                            nota.getNota()));

                    return new BoletimResponseDTO(aluno.getNome(), notaResponseDTOs);
                })
                .collect(Collectors.toList());

        if (boletins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(boletins);
    }
}
