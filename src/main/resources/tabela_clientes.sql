-- public.clientes definition

-- Drop table

-- DROP TABLE public.clientes;

CREATE TABLE public.clientes (
	id serial4 NOT NULL,
	nome varchar NOT NULL,
	documento varchar NULL,
	CONSTRAINT clientes_pk PRIMARY KEY (id)
);