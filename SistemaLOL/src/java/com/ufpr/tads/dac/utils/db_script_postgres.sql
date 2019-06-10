CREATE TABLE tb_estado (
es_id SERIAL PRIMARY KEY,
es_descricao varchar(30),
es_sigla varchar(2));

INSERT INTO tb_estado (es_descricao, es_sigla) VALUES ('Paraná','PR');
INSERT INTO tb_estado (es_descricao, es_sigla) VALUES ('São Paulo','SP');
INSERT INTO tb_estado (es_descricao, es_sigla) VALUES ('Santa Catarina','SC');

CREATE TABLE tb_cidade (
ci_id SERIAL PRIMARY KEY,
ci_descricao varchar(50),
ci_estado int,
CONSTRAINT ci_es_key FOREIGN KEY (ci_estado) REFERENCES tb_estado(es_id));

INSERT INTO tb_cidade (ci_descricao, ci_estado) VALUES ('Curitiba','1');
INSERT INTO tb_cidade (ci_descricao, ci_estado) VALUES ('São Paulo','2');
INSERT INTO tb_cidade (ci_descricao, ci_estado) VALUES ('Araucária','1');
INSERT INTO tb_cidade (ci_descricao, ci_estado) VALUES ('Florianópolis','3');

CREATE TABLE tb_cliente (
cl_id SERIAL PRIMARY KEY,
cl_nome varchar(50) NOT NULL,
cl_email varchar(50) UNIQUE NOT NULL,
cl_senha varchar(50) NOT NULL,
cl_endereco varchar(100),
cl_cidade int,
cl_sexo char (1),
cl_telefone varchar (11),
cl_cpf varchar (11) UNIQUE NOT NULL,
CONSTRAINT cl_ci_key FOREIGN KEY (cl_cidade) REFERENCES tb_cidade(ci_id));

INSERT INTO tb_cliente (cl_nome, cl_email, cl_senha, cl_endereco, cl_cidade, cl_sexo, cl_telefone, cl_cpf)
VALUES ('Marcos','marcos@abc.com','123123','Rua A',1,'M','41984608890','10674027990');
INSERT INTO tb_cliente (cl_nome, cl_email, cl_senha, cl_endereco, cl_cidade, cl_sexo, cl_telefone, cl_cpf)
VALUES ('Yasmin','yasmin@abc.com','234234','Rua B',1,'F','41984689890','11231231333');

CREATE TABLE tb_funcionario (
fu_matricula varchar(8) NOT NULL,
fu_nome varchar(50) NOT NULL,
fu_email varchar(50) UNIQUE NOT NULL,
fu_senha varchar(50) NOT NULL,
fu_datanasc timestamp);
INSERT INTO tb_funcionario (fu_matricula, fu_nome, fu_email, fu_senha, fu_datanasc)
VALUES ('10674','marcos', 'marcos@lol.com', '123', '1996-06-10');

CREATE TABLE tb_roupa (
ro_id SERIAL PRIMARY KEY,
ro_tipo varchar(30) UNIQUE,
ro_prazo time,
ro_valor numeric);
INSERT INTO tb_roupa (ro_tipo, ro_prazo, ro_valor) VALUES ('Meia','00:30:00',1.50);
INSERT INTO tb_roupa (ro_tipo, ro_prazo, ro_valor) VALUES ('Camiseta','01:00:00',5.0);

CREATE TABLE tb_pedido (
pe_numpedido SERIAL PRIMARY KEY,
pe_qtd_itens int,
pe_prazo timestamp,
pe_datapedido timestamp,
pe_preco numeric,
pe_status int,
pe_cliente int,
CONSTRAINT pe_cl_key FOREIGN KEY (pe_cliente) REFERENCES tb_cliente(cl_id));

INSERT INTO tb_pedido (pe_qtd_itens, pe_prazo, pe_datapedido, pe_preco, pe_status, pe_cliente)
VALUES (10,'2019-05-29 12:00:00','2019-05-28 18:32:51',22,1,1);
INSERT INTO tb_pedido (pe_qtd_itens, pe_prazo, pe_datapedido, pe_preco, pe_status, pe_cliente)
VALUES (3,'2019-05-29 09:00:00','2019-05-28 17:00:44',15,1,2);

CREATE TABLE tb_pedido_roupa (
pr_roupa int,
pr_pedido int,
pr_qtd int,
CONSTRAINT pr_ro_key FOREIGN KEY (pr_roupa) REFERENCES tb_roupa(ro_id),
CONSTRAINT pr_pe_key FOREIGN KEY (pr_pedido) REFERENCES tb_pedido(pe_numpedido));

INSERT INTO tb_pedido_roupa VALUES (1,1,8);
INSERT INTO tb_pedido_roupa VALUES (1,1,2);
INSERT INTO tb_pedido_roupa VALUES (2,2,3);