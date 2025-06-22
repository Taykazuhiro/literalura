<p align="center">
<img src = "ONE_logo_rgb.png" width="300" height="150">
</p>
  <p align="center">
   <img src="https://img.shields.io/badge/Framework-Spring-blue"> <img src="https://img.shields.io/badge/Language-Java%2021.07-orange"> <img src="https://img.shields.io/badge/Database-Postgree-green"> 
  </p>

<p><a href="#english-version"> English Version </a></p>

<a id="readme-top"></a>
# 📚 Literalura

Literalura é uma aplicação Java baseada em Spring Boot que permite buscar livros gratuitos via API pública do [Gutendex](https://gutendex.com/), salvando os dados de livros e autores em um banco de dados relacional.

A aplicação também permite consultar, listar e filtrar os livros e autores já registrados.

---

## 🚀 Funcionalidades principais:

- ✅ Buscar livros por título na API Gutendex.
- ✅ Salvar informações de livros e autores no banco de dados.
- ✅ Listar todos os livros salvos.
- ✅ Listar todos os autores salvos.
- ✅ Filtrar autores vivos em um determinado ano.
- ✅ Filtrar livros por idioma.

---

## 🛠️ Tecnologias e frameworks utilizados:

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **Jackson (para leitura de JSON)**
- **API Gutendex** (https://gutendex.com/)

---

## ✅ Requisitos para executar a aplicação:

- Java 17+
- Maven
- PostgreSQL (instalado e rodando)

---

## 🎯 Configuração do Banco de Dados:

Antes de rodar a aplicação, configure o arquivo:

```
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Obs: Certifique-se de ter criado um banco chamado `literalura` no PostgreSQL.

---

## 📦 Como rodar a aplicação:

1. Clone o projeto:

```bash
git clone https://github.com/Taykazuhiro/literalura.git
```

2. Navegue até o diretório:

```bash
cd literalura
```

3. Rode a aplicação:

```bash
mvn spring-boot:run
```

4. O menu interativo será exibido no terminal. Basta seguir as opções.

---

## 👩‍💻 Desenvolvedora:

**Tayná Silva**  
[LinkedIn](https://www.linkedin.com/in/tay-silva/)

Este projeto faz parte do desafio **Literalura** do programa **Oracle Next Generation (ONE) em parceria com a Alura**.

_____________________________

<p align="left"><a id="english-version"> English Version </a></p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Literalura 📚

## About the Project

Literalura is a Java console application that allows users to search, retrieve, and save books and authors data from the **Gutendex API**, an open API that provides information on public domain books.

With Literalura, you can:

- Search for books by title.
- View a list of all saved books.
- View a list of all saved authors.
- Search for authors alive in a specific year.
- Filter books by language.

The project uses Spring Boot and JPA with Hibernate for database interaction and Jackson for JSON data conversion.

---

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Jackson (for JSON parsing)**
- **HttpClient (Java 11+ built-in HTTP client)**

---

## Requirements

Before running the project, make sure you have the following installed:

- Java 17 or higher
- PostgreSQL Database
- Maven
- (Optional) IntelliJ IDEA, Eclipse, or any Java IDE

---

## Configuration

1. **Database Configuration**

In your `application.properties`, configure your database connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

2. **API Endpoint**

The application uses the **Gutendex API**:

```
https://gutendex.com/books/
```

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/Taykazuhiro/literalura.git
```

2. Navigate to the project folder:

```bash
cd literalura
```

3. Build and run the project with Maven:

```bash
mvn spring-boot:run
```

4. Follow the console menu instructions to interact with the application.

---

## Developer

Developed by **Tayná Silva**  
[LinkedIn](https://www.linkedin.com/in/tay-silva/)

This project is part of the **Literalura** challenge of the **Oracle Next Generation (ONE) program in partnership with Alura**.
