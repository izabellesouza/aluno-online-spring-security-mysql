# Aluno Online - Spring Security e MySQL

Projeto Back-End desenvolvido em Java com Spring Boot, evoluído a partir do projeto **Aluno Online** do P3.

Nesta versão, o projeto foi adaptado para a avaliação do P4, com implementação de:

* Spring Security
* Autenticação com JWT
* Cadastro de usuários
* Senha criptografada com BCrypt
* Documentação da API com Swagger/SpringDoc
* Migração do banco PostgreSQL para MySQL

---

## Sobre o projeto

O sistema **Aluno Online** é uma API REST para gerenciamento acadêmico.

A aplicação possui funcionalidades relacionadas a:

* Alunos
* Professores
* Disciplinas
* Matrículas
* Cadastro de usuários
* Login com autenticação JWT

O objetivo desta versão foi adicionar uma camada de segurança à API, protegendo as rotas principais e permitindo acesso apenas mediante autenticação com token JWT.

---

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT com Auth0
* MySQL
* Hibernate
* Lombok
* Swagger / SpringDoc
* Maven

---

## Banco de dados

O projeto utiliza o banco de dados **MySQL**.

Banco utilizado:

```sql
aluno_online
```

Configuração principal no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aluno_online?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=aluno_online_user
spring.datasource.password=Aluno@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Usuário criado no MySQL:

```sql
CREATE USER IF NOT EXISTS 'aluno_online_user'@'localhost' IDENTIFIED BY 'Aluno@123';

GRANT ALL PRIVILEGES ON aluno_online.* TO 'aluno_online_user'@'localhost';

FLUSH PRIVILEGES;
```

---

## Segurança da API

A segurança foi implementada com **Spring Security** e autenticação baseada em **JWT**.

Rotas públicas:

* `POST /login`
* `POST /cadastros`
* `/swagger-ui/**`
* `/v3/api-docs/**`

Demais rotas são protegidas e exigem token JWT.

Exemplo de header usado nas requisições protegidas:

```http
Authorization: Bearer token_jwt_aqui
```

---

## Fluxo de autenticação

1. O usuário é cadastrado pela rota `/cadastros`.
2. A senha é criptografada com BCrypt antes de ser salva no banco.
3. O usuário faz login pela rota `/login`.
4. A API valida login e senha.
5. A API gera um token JWT.
6. O token é enviado nas próximas requisições.
7. O filtro de segurança valida o token antes de liberar o acesso às rotas protegidas.

---

## Principais endpoints

### Cadastro de usuário

```http
POST /cadastros
```

Exemplo de JSON:

```json
{
  "login": "iza",
  "senha": "123456"
}
```

---

### Login

```http
POST /login
```

Exemplo de JSON:

```json
{
  "login": "iza",
  "senha": "123456"
}
```

Resposta esperada:

```json
{
  "token": "token_jwt_gerado"
}
```

---

### Listar alunos

```http
GET /alunos
```

Essa rota é protegida e exige token JWT.

---

### Cadastrar aluno

```http
POST /alunos
```

Exemplo de JSON:

```json
{
  "nomeCompleto": "Anne Izabelle",
  "email": "anneizabelle@email.com",
  "cpf": "12345678900"
}
```

---

## Swagger

A documentação da API pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

No Swagger, é possível:

1. Cadastrar usuário.
2. Fazer login.
3. Copiar o token JWT.
4. Clicar em **Authorize**.
5. Inserir o token.
6. Testar as rotas protegidas.

---

## Como rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/izabellesouza/aluno-online-spring-security-mysql.git
```

### 2. Entrar na pasta do projeto

```bash
cd aluno-online-spring-security-mysql
```

### 3. Criar o banco no MySQL

```sql
CREATE DATABASE IF NOT EXISTS aluno_online;
```

### 4. Criar usuário do banco

```sql
CREATE USER IF NOT EXISTS 'aluno_online_user'@'localhost' IDENTIFIED BY 'Aluno@123';

GRANT ALL PRIVILEGES ON aluno_online.* TO 'aluno_online_user'@'localhost';

FLUSH PRIVILEGES;
```

### 5. Rodar a aplicação

Pelo IntelliJ, execute a classe:

```text
AlunoOnlineApplication
```

Ou pelo terminal:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## Testes realizados

Foram realizados os seguintes testes:

* API rodando na porta 8080.
* Conexão com MySQL funcionando.
* Criação das tabelas pelo Hibernate.
* Swagger abrindo corretamente.
* Cadastro de usuário funcionando.
* Senha salva criptografada no banco.
* Login retornando token JWT.
* Rota `/alunos` bloqueada sem token.
* Rota `/alunos` liberada com token.
* Cadastro de aluno funcionando com autenticação.
* Listagem de alunos funcionando com autenticação.

---

## Observação

Este projeto é uma evolução acadêmica do projeto Aluno Online desenvolvido no P3, adaptado para a disciplina de Back-End do P4, com foco em segurança, autenticação, documentação e uso de MySQL.

---
## Informações acadêmicas

Projeto desenvolvido por **Anne Izabelle** para a avaliação A2 da disciplina de Back-End / Spring Avançado, ministrada pelo **Prof. Junio Figueirêdo**, no 4º período do curso de Ciência da Computação.

---
