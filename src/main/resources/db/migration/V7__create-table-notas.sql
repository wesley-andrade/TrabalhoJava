create table notas (
    id int not null primary key auto_increment,
    matricula_id int not null,
    disciplina_id int not null,
    nota decimal(5, 2) not null,
    data_lancamento date not null,
    constraint fk_matricula foreign key (matricula_id) references matriculas(id),
    constraint fk_disciplina foreign key (disciplina_id) references disciplinas(id)
);