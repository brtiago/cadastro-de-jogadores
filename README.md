## 1. Estrutura do Projeto (MVC)
   

``` plaintext
src/
├── main/
│   ├── com/example/cadastro_de_jogadores
│   │   ├── config/            → Configurações globais (Security, RabbitMQ, Swagger)
│   │   ├── controller/        → Endpoints REST (@RestController)
│   │   ├── model/             → Entidades JPA, DTOs, Enums
│   │   ├── repository/        → Interfaces Spring Data JPA
│   │   ├── service/           → Lógica de negócio (@Service)
│   │   └── exception/         → Tratamento de erros (@ControllerAdvice)
│   └── resources/
│       ├── db/migration/      → Scripts Flyway (V1__Create_tables.sql)
│       ├── application.yml    → Configs do Spring
│       └── swagger.yaml       → Documentação OpenAPI
```

## 2. Divisão de Responsabilidades
| Camada | Exemplo | Framework|
| ------ | ------- | ---------|
| Model	| Jogador.java (JPA) | 	Spring Data JPA|
| Repository | JogadorRepository.java |	Spring Data JPA|
| Service | JogadorService.java	Spring | (@Service)|
| Controller |	JogadorController.java	| Spring (@RestController)|