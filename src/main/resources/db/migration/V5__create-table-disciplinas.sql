create table disciplinas (
    id int not null primary key auto_increment,
    nome varchar(100) not null,
    codigo varchar(20) not null,
    curso_id int not null,
    professor_id int not null,
    foreign key (curso_id) references cursos(id),
    constraint fk_professor foreign key (professor_id) references professores(id)
);