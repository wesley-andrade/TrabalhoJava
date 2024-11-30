package br.grupointegrado.Trabalho_Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O ano não pode ficar vazio")
    @Min(2000)
    @Column(nullable = false)
    private Integer ano;

    @NotNull(message = "O semestre não pode ficar vazio")
    @Min(1)
    @Max(2)
    @Column(nullable = false)
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso cursoId;

    @OneToMany(mappedBy = "turmaId")
    private List<Matricula> matriculas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public @Min(1) @Max(2) Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(@Min(1) @Max(2) Integer semestre) {
        this.semestre = semestre;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
