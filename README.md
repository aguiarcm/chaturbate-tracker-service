## Project Setup
- Java JDK 17
- MySQL Server
- Docker
- Docker-Compose

## Iniciar Base de Datos
``docker-compose up -d``

## Crear Tablas
```
CREATE TABLE chaturbate_account (
uid INT PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(255),
url VARCHAR(255)
);

CREATE TABLE chaturbate_account_history (
uid INT PRIMARY KEY AUTO_INCREMENT,
user_name  VARCHAR(255),
token_balance INT,
tips_in_last_hour INT,
last_broadcast datetime
);
```

## Guardar una cuenta nueva
```
curl --location 'localhost:8080/chaturbate-accounts' \
--header 'Content-Type: application/json' \
--data '{
"userName" : "USER_NAME",
"url" : "URL_FOR_GETTING_TOKENS"
}'
```

curl --location 'localhost:8080/chaturbate-accounts' \
--header 'Content-Type: application/json' \
--data '{
"userName" : "iwillgetyou1",
"url" : "https://chaturbate.com/statsapi/?username=iwillgetyou1&token=KRe2q05pKmv163Dw3GjVH8qp"
}'

