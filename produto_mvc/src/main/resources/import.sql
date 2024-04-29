-- CATEGORIA
INSERT INTO tb_categoria(nome) VALUES ('Smartphone');
INSERT INTO tb_categoria(nome) VALUES ('Smart TV');
INSERT INTO tb_categoria(nome) VALUES ('Notebook');
INSERT INTO tb_categoria(nome) VALUES ('Tablet');
INSERT INTO tb_categoria(nome) VALUES ('Mouse');
INSERT INTO tb_categoria(nome) VALUES ('Teclado');

-- LOJA
INSERT INTO tb_loja (nome) VALUES ('Americanas');
INSERT INTO tb_loja (nome) VALUES ('Fast Shop');
INSERT INTO tb_loja (nome) VALUES ('Magazine Luiza');
INSERT INTO tb_loja (nome) VALUES ('Submarino');

-- PRODUTO
INSERT INTO tb_produto(nome, descricao, valor, categoria_id) VALUES('Mouse Microsoft', 'Mouse sem fio', 250.0, 5);
INSERT INTO tb_produto(nome, descricao, valor, categoria_id) VALUES('Smartphone Samsung Galaxy A54 5G', 'Samsung Galaxy A54 5G', 1799.0, 2);
INSERT INTO tb_produto(nome, descricao, valor, categoria_id) VALUES('Mouse Dell 231', 'Mouse sem fio dell', 399, 5);
INSERT INTO tb_produto(nome, descricao, valor, categoria_id) VALUES('Teclado Lenovo ', 'Teclado lenovo', 750, 6);
INSERT INTO tb_produto(nome, descricao, valor, categoria_id) VALUES('Samsung book 3', 'Tablet Samsung book 3', 3999, 4);

-- PRODUTO_LOJA
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (1,1);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (1,3);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (2,1);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (2,4);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (3,2);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (4,2);
INSERT INTO tb_produto_loja (produto_id, loja_id) VALUES (5,2);

