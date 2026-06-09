# Gerenciador de Produtos — Spring Boot + Angular

Sistema CRUD para gerenciamento de produtos e tipos de produto, desenvolvido com Spring Boot no backend e Angular no frontend.

---

## Requisitos

- Java 17+
- Maven 3.8+
- Node.js LTS (18 ou 20)
- Angular CLI (`npm install -g @angular/cli`)

---

## Como Rodar o Backend

```bash
cd backend
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

Console do banco H2 (para visualizar os dados): `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:produtosdb`
- User: `sa` | Senha: (vazio)

---

## Como Rodar o Frontend

```bash
cd frontend
npm install
ng serve
```

A aplicação estará disponível em: `http://localhost:4200`

---

## Endpoints da API

### Tipos de Produto
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/tipos-produto` | Lista todos os tipos |
| POST | `/api/tipos-produto` | Cria um novo tipo |
| DELETE | `/api/tipos-produto/{id}` | Remove um tipo |

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/produtos` | Lista todos os produtos |
| POST | `/api/produtos` | Cria um novo produto |
| DELETE | `/api/produtos/{id}` | Remove um produto |

---

## Padrões de Projeto Aplicados

- **MVC** — separação entre Controller, Service e Repository
- **Repository Pattern** — acesso a dados via JpaRepository
- **Service Layer** — regras de negócio isoladas nos Services
- **DTO Pattern** — desacoplamento entre entidades e API
- **Observer (RxJS)** — Observables para chamadas HTTP no Angular
