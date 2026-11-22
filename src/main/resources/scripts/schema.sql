create table contatos (
    idContato       int generated always as identity    primary key,
    nome            varchar(150)                        not null,
    email           varchar(100)                        not null,
    telefone        varchar(15)                         not null,
    datacadastro    timestamp                           default current_timestamp,
    ativo           int                                 default 1
);
