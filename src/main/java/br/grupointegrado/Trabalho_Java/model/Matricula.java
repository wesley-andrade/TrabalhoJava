package br.grupointegrado.Trabalho_Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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

    @OneToMany(mappedBy = "matriculaId")
    private List<Nota> notas;

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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
