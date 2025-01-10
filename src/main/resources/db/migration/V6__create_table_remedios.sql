CREATE TABLE remedios(
id bigint AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
via varchar (100) NOT NULL,
lote varchar (100),
quantidade int(100) NOT NULL,
validade varchar (100) NOT NULL,
laboratorio varchar (100) NOT NULL
)