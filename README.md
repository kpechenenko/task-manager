# Тестовое задание в компанию X

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
| `POST`   | `/tasks`      | Добавить/_изменить_ задачу                                   |
| `GET`    | `/tasks`      | Получить список всех задач                                   |
| `DELETE` | `/tasks/{id}` | Удалить задачу                                               |
| `POST`   | `/tags`       | Добавить/_изменить_ тег                                      |
| `GET`    | `/tags/{id}`  | Получить один тег по УИД и все его задачи                    |
| `DELETE` | `/tags/{id}`  | Каскадно удалить тег со всеми прикрепленными к нему задачами |

## Стек

- Java 17.
- Spring Boot 2.7.4.
- Gradle.
- Git.
- Hibernate.
- PostgreSQL.
