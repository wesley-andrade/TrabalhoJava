create table turmas (
    id int not null primary key auto_increment,
    ano int not null,
    semestre int not null,
    curso_id int not null,
    constraint fk_curso foreign key (curso_id) references cursos(id)
);