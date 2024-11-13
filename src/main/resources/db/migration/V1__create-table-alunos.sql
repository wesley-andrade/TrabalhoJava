create table alunos (
    id int not null primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    matricula varchar(20) not null unique,
    data_nascimento date not null
);