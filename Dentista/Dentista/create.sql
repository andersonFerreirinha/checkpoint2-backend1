CREATE TABLE IF NOT EXISTS DENTISTA (
id int auto_increment primary key,
nome VARCHAR(50),
sobrenome VARCHAR(50),
matricula CHAR(3));


CREATE TABLE IF NOT EXISTS PACIENTE (
id int auto_increment primary key,
nome VARCHAR(50),
sobrenome VARCHAR(50),
endereco int foreign key,
rg VARCHAR(14),
dataCadastro DATE);


CREATE TABLE IF NOT EXISTS ENDERECO (
id int auto_increment primary key,
rua VARCHAR(50),
numero VARCHAR(50),
complemento VARCHAR(50),
cep VARCHAR(12),
cidade VARCHAR(25),
estado VARCHAR(25));