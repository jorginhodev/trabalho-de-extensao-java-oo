CREATE TABLE moradores (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           endereco VARCHAR(200) NOT NULL,
                           telefone VARCHAR(20),
                           email VARCHAR(100)
);

CREATE TABLE eventos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(100) NOT NULL,
                         data DATETIME NOT NULL,
                         local VARCHAR(200) NOT NULL,
                         descricao TEXT
);

CREATE TABLE demandas (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          titulo VARCHAR(100) NOT NULL,
                          descricao TEXT,
                          status VARCHAR(20) NOT NULL,
                          dataCriacao DATETIME NOT NULL,
                          moradorId INT,
                          FOREIGN KEY (moradorId) REFERENCES moradores(id)
);

CREATE TABLE informacoes (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             titulo VARCHAR(100) NOT NULL,
                             conteudo TEXT,
                             dataPublicacao DATETIME NOT NULL,
                             autor VARCHAR(100),
                             categoria VARCHAR(50)
);
