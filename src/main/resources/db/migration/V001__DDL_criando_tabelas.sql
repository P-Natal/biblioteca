CREATE TABLE "public"."autor"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    primeiro_nome   VARCHAR(20) NOT NULL,
    nome_do_meio    VARCHAR(20) NOT NULL,
    ultimo_nome     VARCHAR(20) NOT NULL,
    afiliacao       VARCHAR(20) NOT NULL,
    email           VARCHAR(30) NOT NULL,
    pais            VARCHAR(20) NOT NULL
);

CREATE TABLE "public"."publicacao"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    titulo          VARCHAR(20) NOT NULL,
    data_publicacao TIMESTAMP NOT NULL,
    acesso_livre    boolean NOT NULL,
    afiliacao       VARCHAR(20) NOT NULL,
    doi             VARCHAR(30) NOT NULL
);

CREATE TABLE "public"."publicacao_autor"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    publicacao_id   BIGSERIAL NOT NULL REFERENCES publicacao(id),
    autor_id        BIGSERIAL NOT NULL REFERENCES autor(id)
);

CREATE TABLE "public"."conferencia"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    acronimo        VARCHAR(10) NOT NULL,
    edicao          int NOT NULL,
    cidade          VARCHAR(30) NOT NULL,
    pais            VARCHAR(20) NOT NULL,
    data_inicio     TIMESTAMP NOT NULL,
    data_fim        TIMESTAMP NOT NULL
);

CREATE TABLE "public"."artigo_de_conferencia"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    publicacao_id   BIGSERIAL NOT NULL REFERENCES publicacao(id),
    conferencia_id  BIGSERIAL NOT NULL REFERENCES conferencia(id),
    tipo            VARCHAR(10) NOT NULL
);

CREATE TABLE "public"."editora"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    nome            VARCHAR(20) NOT NULL,
    pais            VARCHAR(20) NOT NULL
);

CREATE TABLE "public"."periodico"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    editora_id      BIGSERIAL NOT NULL REFERENCES editora(id),
    titulo          VARCHAR(50) NOT NULL,
    acronimo        VARCHAR(20) NOT NULL,
    issn            int NOT NULL
);

CREATE TABLE "public"."artigo_periodico"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    publicacao_id   BIGSERIAL NOT NULL REFERENCES publicacao(id),
    periodico_id    BIGSERIAL NOT NULL REFERENCES periodico(id),
    edicao          int NOT NULL,
    volume          int NOT NULL
);

CREATE TABLE "public"."livro"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    publicacao_id   BIGSERIAL NOT NULL REFERENCES publicacao(id),
    editora_id      BIGSERIAL NOT NULL REFERENCES editora(id),
    isbn            int NOT NULL
);

CREATE TABLE "public"."capitulo"
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    livro_id        BIGSERIAL NOT NULL REFERENCES livro(id)
);
