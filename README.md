# Car dealer REST API

## Instalace

### Preconditions

- Java 21 (pro lokální build)
- Docker Engine 27


- Výchozím terminálem pro spouštění konfigurací v IntelliJ pro Windows musí být CMD (File | Settings | Tools |
  Terminal | Application Settings |
  Shell path -> select path to CMD)

### Konfigurace

Konfiguraci (porty, cílové prostředí apod.) lze přizpůsobit v [./.docker/.env](./.docker/.env)

### Postup

#### 1. Čistý Maven build

Build projektu lze provést lokálně nebo přes Docker - vyberte jednu z možností níže.

- IntelliJ konfigurace pro lokální build (vyžaduje Javu):  
  `[LOCAL] maven clean package`

- IntelliJ konfigurace pro build v Dockeru (pomalejší, nevyžaduje Javu, přenositelný):  
  `[DOCKER] maven clean package`

- Přímý CLI skript pro build v Dockeru (+ nezávislý na IDE):

```shell
docker compose --env-file .docker/.env.versions -f .docker/compose.builder.yml pull && docker compose --env-file .docker/.env.versions -f .docker/compose.builder.yml run --rm clean-package
```

#### 2. Spuštění

Runtime prostředí projektu v Dockeru - vyberte jednu z možností níže.

- IntelliJ konfigurace:  
  `[DOCKER] compose up`

- Přímý CLI skript

```shell
docker compose --env-file .docker/.env --env-file .docker/.env.versions -f .docker/compose.dev.yml -p car-dealer_dev up --pull=always --build -d
```

### Autentizace na DB

**Uživatel:** api  
**Heslo:** admin

### Autentizace na REST API (HTTP Basic)

**Uživatel:** admin  
**Heslo:** admin

### HTTP requesty

viz [./docs/endpoints.http](./docs/endpoints.http)

### OpenAPI + Swagger

http://localhost:8080/swagger-ui/index.html

### Debug

Kontejner je přichystán pro debug:

`[DOCKER] debug`
