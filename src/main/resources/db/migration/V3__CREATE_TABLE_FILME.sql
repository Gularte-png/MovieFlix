CREATE TABLE filme
(
    id serial PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao text,
    data_de_lançamento DATE,
    avaliacao NUMERIC,
    criado TIMESTAMP,
    atualizado TIMESTAMP
);