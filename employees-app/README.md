# Employees app

## Flyway

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

`src/main/resources/db.migration/V1__employees.sql`

## Docker

```shell
mvnw clean package
docker build -t employees .
docker network create employees-net
docker run -d -e MYSQL_DATABASE=employees -e MYSQL_USER=employees -e MYSQL_PASSWORD=employees -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 --name employees-mariadb --network employees-net mariadb
docker run -d -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mariadb://employees-mariadb/employees --name employees --network employees-net employees
```

## Docker Compose

```shell
docker build -t employees .
cd employees
docker compose up -d
```