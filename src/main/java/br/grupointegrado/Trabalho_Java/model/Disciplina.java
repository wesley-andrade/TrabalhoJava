package br.grupointegrado.Trabalho_Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O nome não pode ficar vazio")
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O codigo não pode ficar vazio")
    @Size(max = 20)
    @Column(nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso cursoId;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    public Professor getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Professor professorId) {
        this.professorId = professorId;
    }
}
