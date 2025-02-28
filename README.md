# Booking Information System


# Локальный запуск проекта

Проект состоит из нескольких модулей, включая Spring Boot приложение и PostgreSQL базу данных, запускаемых в отдельных контейнерах.
Все ручки начинаются с `/api/v1`

## Требования
`Docker` и `Docker Compose`
## Настройка

1. **Получите локальную копию репозитория:**

   Склонируйте репозиторий:

```bash
git clone https://github.com/effective-tester/bis.git
```
или скачайте и разархивируйте приложение


2. **Настройте файл `.env`:**

   В корневой директории проекта создайте файл `.env` и добавьте Ваши переменные среды, например, следующие:

```env
# Database Configuration
DB_SERVER=bisserver
DB_HOST_PORT=5434
DB_SERVER_PORT=5432
DB_NAME=bisdb
DB_USERNAME=postgres
DB_PASSWORD=postgres

# Server Configuration
HOST_PORT=8080
SERVER_PORT=8080

# JWT Configuration
JWT_SECRET=3ZbN0C2xVh2x7O1YsZ3qLwKX48eAG6E1+H6QiKlf3LJq4H3YfZIuqpeIDtTsVzT4jk=
JWT_EXPIRATION=86400000
JWT_HEADER=Authorization
JWT_PREFIX="Bearer "
```

## Запуск проекта

Выполните следующие команды для сборки и запуска контейнеров:

```bash
docker-compose build
docker-compose up
```

или используйте аналогичный вариант:

```bash
docker-compose up --build
```
