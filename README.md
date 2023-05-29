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




## Conexión por SSH desde Windows

### Ejecutar permisos
```icacls * /reset /t /c /q´´```


```ssh -i "new-pem.pem" ec2-user@ec2-3-142-197-226.us-east-2.compute.amazonaws.com```

### Instalar Java
```
sudo tee /etc/yum.repos.d/corretto.repo << EOF
[corretto]
name=Corretto
baseurl=https://yum.corretto.aws/corretto/${corretto_version}/amazonlinux/latest/
enabled=1
gpgcheck=1
gpgkey=https://yum.corretto.aws/corretto/${corretto_version}/amazonlinux/latest/jdk11u-latest-x86_64.rpm.sig
EOF
```
```
sudo yum install -y java-17-amazon-corretto-devel
```

### Mover jar a aws
```scp -i "G:\Otros ordenadores\Mi PC\TODO PRISMA\Software\AWS\new-pem.pem" .\chaturbateTrackerApp.jar  ec2-user@ec2-3-142-197-226.us-east-2.compute.amazonaws.com:/home/ec2-user/chaturbate-service```