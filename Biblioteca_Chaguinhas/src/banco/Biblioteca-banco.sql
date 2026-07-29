CREATE DATABASE Biblioteca_NickChaguinhas;

USE Biblioteca_NickChaguinhas;

CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) UNIQUE,
    genero VARCHAR(100) NOT NULL,
    idioma VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);