CREATE TABLE IF NOT EXISTS usuario(
    matricula VARCHAR(30) PRIMARY KEY,
    senha VARCHAR(80) NOT NULL,
    email VARCHAR(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS ponto (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(30) NOT NULL,
    hora_data_registro TIMESTAMP  NOT NULL,
    tipo_registro VARCHAR(30)  NOT NULL,
    FOREIGN KEY (matricula) REFERENCES usuario(matricula)
);

insert into usuario (matricula, senha, email) values ('657849', '$2a$10$py8KAxJhU7nk1lxDnF9yMutOBbu1i8ylXJx5pEoInz3g1o8VMLS1u', 'user@email.com');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-01T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-01T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-01T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-01T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-02T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-02T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-02T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-02T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-05T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-05T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-05T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-05T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-06T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-06T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-06T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-06T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-07T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-07T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-07T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-07T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-08T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-08T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-08T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-08T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-09T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-09T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-09T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-09T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-12T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-12T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-12T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-12T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-13T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-13T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-13T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-13T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-14T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-14T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-14T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-14T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-15T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-15T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-15T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-15T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-16T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-16T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-16T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-16T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-19T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-19T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-19T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-19T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-20T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-20T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-20T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-20T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-21T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-21T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-21T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-21T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-22T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-22T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-22T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-22T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-23T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-23T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-23T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-23T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-26T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-26T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-26T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-26T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-27T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-27T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-27T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-27T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-28T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-28T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-28T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-28T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-29T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-29T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-29T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-02-29T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-01T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-01T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-01T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-01T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-04T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-04T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-04T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-04T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-05T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-05T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-05T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-05T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-06T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-06T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-06T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-06T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-07T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-07T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-07T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-07T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-08T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-08T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-08T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-08T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-11T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-11T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-11T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-11T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-12T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-12T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-12T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-12T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-13T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-13T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-13T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-13T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-15T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-15T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-15T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-15T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-18T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-18T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-18T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-18T17:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-19T08:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-19T12:00:00', 'SAIDA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-19T13:00:00', 'ENTRADA');
insert into ponto (matricula, hora_data_registro, tipo_registro) values ('657849', '2024-03-19T17:00:00', 'SAIDA');
