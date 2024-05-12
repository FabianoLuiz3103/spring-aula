-- DEPARTAMENTO
INSERT INTO tbl_departamento(nome) VALUES ('Desenvolvimento de Software');
INSERT INTO tbl_departamento(nome) VALUES ('Marketing e Publicidade');
INSERT INTO tbl_departamento(nome) VALUES ('Suporte Técnico e Atendimento ao Cliente');
INSERT INTO tbl_departamento(nome) VALUES ('Recursos Humanos e Administração de Pessoal');
INSERT INTO tbl_departamento(nome) VALUES ('Pesquisa e Desenvolvimento');
INSERT INTO tbl_departamento(nome) VALUES ('Operações e Logística');

-- PROJETO
INSERT INTO tbl_projeto(nome) VALUES ('Desenvolvimento do Novo Software de Gestão Interna');
INSERT INTO tbl_projeto(nome) VALUES ('Campanha de Lançamento do Novo Produto');
INSERT INTO tbl_projeto(nome) VALUES ('Melhoria da Experiência do Cliente');
INSERT INTO tbl_projeto(nome) VALUES ('Desenvolvimento de Novos Recursos do Produto');
INSERT INTO tbl_projeto(nome) VALUES ('Implementação de um Sistema de Gestão de Talentos');
INSERT INTO tbl_projeto(nome) VALUES ('Desenvolvimento de uma Nova Estratégia de Marketing Digital');
INSERT INTO tbl_projeto(nome) VALUES ('Implementação de um Programa de Capacitação e Desenvolvimento de Funcionários');
INSERT INTO tbl_projeto(nome) VALUES ('Atualização da Infraestrutura Tecnológica');

-- EMPREGADO
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('João Silva', 'joao.silva@nextechsolutions.com', 5000.0, 1);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Maria Oliveira', 'maria.oliveira@nextechsolutions.com', 4800.0, 2);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Pedro Santos', 'pedro.santos@nextechsolutions.com', 5500.0, 3);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Ana Pereira', 'ana.pereira@nextechsolutions.com', 5200.0, 4);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Carlos Ferreira', 'carlos.ferreira@nextechsolutions.com', 5300.0, 5);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Sofia Almeida', 'sofia.almeida@nextechsolutions.com', 5100.0, 6);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Rafael Oliveira', 'rafael.oliveira@nextechsolutions.com', 4100.0, 1);
INSERT INTO tbl_empregado(nome, email, salario, departamento_id) VALUES('Juliana Costa', 'juliana.costa@nextechsolutions.com', 3100.0, 4);

-- EMPREGADO_PROJETO
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (1,1);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (1,2);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (1,8);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (2,3);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (2,4);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (3,1);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (3,5);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (3,6);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (4,7);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (4,8);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (5,1);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (5,5);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (6,2);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (6,6);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (7,3);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (7,8);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (8,1);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (8,8);
INSERT INTO tbl_empregado_projeto (empregado_id, projeto_id) VALUES (8,4);

