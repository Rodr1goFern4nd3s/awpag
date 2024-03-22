create table clientes (
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(255) not null unique,
    telefone varchar(20) not null,

    primary key(id)
);