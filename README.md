# Тестовое задание в компанию X на позицию java developer'а

## Цель

Разработать HTTP API сервис для менеджера задач.

## База данных

1. Таблица "Теги"
    - УИД тега.
    - Заголовок тега.
2. Таблица "Задачи"
    - УИД задачи.
    - Наименование задачи.
    - Описание задачи.
    - Дата окончания задачи.
    - УИД тега задачи (из таблицы тегов).

Таблицы должны быть связаны между собой.

## API

| Метод    | Ресурс        | Описание                                                     |
|----------|---------------|--------------------------------------------------------------|
| `POST`   | `/tasks`      | добавить/_изменить_ задачу                                   |
| `GET`    | `/tasks`      | получить список всех задач                                   |
| `DELETE` | `/tasks/{id}` | удалить задачу                                               |
| `POST`   | `/tags`       | добавить/_изменить_ тег                                      |
| `GET`    | `/tags/{id}`  | Получить один тег по УИД и все его задачи                    |
| `DELETE` | `/tags/{id}`  | каскадно удалить тег со всеми прикрепленными к нему задачами |

## Стек

- Java 17.
- Spring Boot 2.7.4.
- Gradle.
- Git.
- Hibernate.
- PostgreSQL.
