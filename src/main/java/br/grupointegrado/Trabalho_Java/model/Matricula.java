package br.grupointegrado.Trabalho_Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O campo não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno alunoId;

    @NotNull(message = "O campo não pode ficar vazio")
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turmaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Aluno alunoId) {
        this.alunoId = alunoId;
    }

    public Turma getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Turma turmaId) {
        this.turmaId = turmaId;
    }
}
