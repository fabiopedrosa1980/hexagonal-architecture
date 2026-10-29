# Hexagonal Architecture

API REST de produtos em Java com Spring Boot, estruturada seguindo a **Arquitetura Hexagonal** (Ports & Adapters). O objetivo do projeto é demonstrar o isolamento do domínio em relação a frameworks e infraestrutura.

## Tecnologias

- Java 27
- Spring Boot 4.2.0 (Web MVC, Data JPA)
- PostgreSQL
- Apache Kafka (dependência incluída para integração de eventos)
- Docker Compose
- Maven (wrapper incluído)

## Arquitetura

O núcleo (`core`) contém o domínio e as regras de negócio e **não depende** de Spring, JPA ou de qualquer tecnologia externa. A comunicação com o mundo externo acontece por meio de portas (interfaces), implementadas pelos adapters.

```
                 ┌──────────────────────────────────────────┐
                 │                  core                    │
 Adapter IN      │                                          │      Adapter OUT
┌────────────┐   │  ┌────────────┐      ┌────────────────┐  │   ┌─────────────────────┐
│ Controller │──▶│  │ port.in    │─────▶│ ProductService │  │   │ PersistenceAdapter  │
│  (REST)    │   │  │ (UseCases) │      │                │──┼──▶│  (JPA / Postgres)   │
└────────────┘   │  └────────────┘      └───────┬────────┘  │   └─────────────────────┘
                 │                              │           │
                 │                       ┌──────▼───────┐   │
                 │                       │  port.out    │   │
                 │                       │ (Repository) │   │
                 │                       └──────────────┘   │
                 └──────────────────────────────────────────┘
```

### Estrutura de pacotes

```
br.com.pedrosa
├── core
│   ├── domain
│   │   └── Product.java                    # Modelo de domínio (sem JPA)
│   ├── port
│   │   ├── in
│   │   │   ├── CreateProductUseCase.java
│   │   │   ├── CreateProductCommand.java
│   │   │   └── ListProductsUseCase.java
│   │   └── out
│   │       └── ProductRepositoryPort.java
│   └── service
│       └── ProductService.java             # Implementa os use cases
├── adapter
│   ├── in.web
│   │   ├── ProductController.java
│   │   ├── ProductRequest.java
│   │   └── ProductResponse.java
│   └── out.persistence
│       ├── ProductJpaEntity.java           # @Entity
│       ├── ProductJpaRepository.java       # Spring Data
│       ├── ProductMapper.java
│       └── ProductPersistenceAdapter.java  # Implementa ProductRepositoryPort
└── config
    └── BeanConfiguration.java              # Registra o ProductService como bean
```

### Fluxo de dados

```
ProductRequest → CreateProductCommand → Product → ProductJpaEntity → Postgres
                                           ↓
                                    ProductResponse
```

Cada camada possui seu próprio tipo, e a conversão acontece na borda de cada adapter.

### Regras de dependência

- `core` não importa nada de `adapter`, `org.springframework..` ou `jakarta.persistence..`.
- Adapters dependem do `core`, nunca o contrário.
- Controllers conhecem apenas as portas de entrada (use cases), nunca a classe `ProductService`.

## Pré-requisitos

- JDK 27
- Docker e Docker Compose

## Como executar

1. Clone o repositório:

```bash
git clone https://github.com/fabiopedrosa1980/hexagonal-architecture.git
cd hexagonal-architecture
```

2. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

O projeto usa o `spring-boot-docker-compose`, então o Spring Boot sobe os containers definidos em `compose.yaml` (Postgres) automaticamente ao iniciar a aplicação.

Se preferir subir a infraestrutura manualmente:

```bash
docker compose up -d
```

## Configuração

Exemplo de `application.properties` para desenvolvimento:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Em produção, prefira `ddl-auto=validate` com migrations (Flyway ou Liquibase).

## Endpoints

### Criar produto

```http
POST /products
Content-Type: application/json
```

```json
{
  "name": "Teclado mecânico",
  "price": 350.00
}
```

Resposta `201 Created`:

```json
{
  "id": 1,
  "name": "Teclado mecânico",
  "price": 350.00
}
```

### Listar produtos (paginado)

```http
GET /products?page=0&size=10
```

Exemplo:

```bash
curl "http://localhost:8080/products?page=0&size=10"
```

## Testes

```bash
./mvnw test
```

## Decisões de design

- **Domínio sem frameworks:** `Product` é um `record` com validações de negócio (nome obrigatório e preço positivo).
- **Entidade JPA separada:** `ProductJpaEntity` existe apenas no adapter de persistência, e o `ProductMapper` faz a conversão.
- **Service sem anotações do Spring:** o `ProductService` é registrado manualmente em `BeanConfiguration`, mantendo o core livre de dependências de framework.
- **Command por use case:** cada ação de escrita recebe um command específico (`CreateProductCommand`), evitando DTOs genéricos reaproveitados.

## Próximos passos

- Adicionar testes de arquitetura com [ArchUnit](https://www.archunit.org/) para garantir as regras de dependência.
- Implementar publicação de eventos via Kafka como adapter de saída (`ProductEventPublisherPort`).
- Adotar migrations com Flyway.
- Adicionar tratamento global de erros (`@RestControllerAdvice`).
- Implementar atualização e remoção de produtos.

## Licença

Defina a licença do projeto (por exemplo, MIT).
