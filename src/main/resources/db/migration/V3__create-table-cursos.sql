create table cursos (
    id int not null primary key auto_increment,
    nome varchar(100) not null,
    codigo varchar(20) not null unique,
    carga_horaria int not null
);