package br.grupointegrado.Trabalho_Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    @Min(1)
    @Max(2)
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso cursoId;

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
}
