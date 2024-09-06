# Sistema de Comunicação do Bairro Vila Nova

## Descrição
Este projeto é um sistema de comunicação comunitária desenvolvido para o Bairro Vila Nova. Ele permite que os moradores se registrem, façam login, postem eventos, demandas e informações relevantes para a comunidade.

## Tecnologias Utilizadas
- Java 11
- Spring Boot 2.7.14
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL 8.0.33 (driver)
- Maven
- HTML/CSS
- Spring Boot DevTools

## Funcionalidades
- Registro e autenticação de usuários
- Criação e visualização de eventos comunitários
- Registro e acompanhamento de demandas do bairro
- Compartilhamento de informações importantes
- Dashboard personalizado para usuários autenticados

## Configuração do Projeto
1. Clone o repositório:
```bash
git clone git@github.com:jorginhodev/trabalho-de-extensao-java-oo.git
```

2. Navegue até o diretório do projeto:
```bash
cd trabalho-de-extensao-java-oo
```

3. Configure o banco de dados MySQL no arquivo `src/main/resources/application.properties`.
4. Execute o projeto usando Maven:
```
mvn spring-boot:run
```

> Nota: O projeto utiliza Spring Boot DevTools, que permite hot-reloading para uma experiência de desenvolvimento mais ágil.

## Estrutura do Projeto
- `.idea`: Configurações do IntelliJ IDEA
- `db`: Scripts e arquivos relacionados ao banco de dados
- `src/main`: Código-fonte principal
- `java/com/comunidade`: Classes Java do projeto
- `resources`: Recursos da aplicação
 - `static`: Arquivos estáticos (CSS, JS)
 - `templates`: Templates Thymeleaf
- `.gitignore`: Especifica arquivos e diretórios ignorados pelo Git
- `docker-compose.yml`: Configuração do Docker Compose
- `pom.xml`: Arquivo de configuração do Maven

## Uso do Docker
Este projeto utiliza Docker. Para iniciar os serviços Docker:
```bash
docker-compose up -d
```

> Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema antes de executar este comando.
