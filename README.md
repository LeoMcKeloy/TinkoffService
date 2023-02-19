#TinkoffService

 Сервис для получения информации по акциям с Tinkoff API

## Запуск на локальной машине

Для запуска потребуется установленный Docker for Desktop.
Dockerfile для создания образа и запуска контейнера сервиса находится в корне.

### build image
>docker build -t tinkoff .

### run container
>docker run -p 8004:8004 --name tinkoff-service -t tinkoff

## Доступ к OpenAPI

[Open api](http://localhost:8004/swagger-ui.html)

## Token

Для получения доступа к Tinkoff API необходимо получить токен и добавить в environment variables:

> Run -> Edit Configurations -> environment variables

[ссылка на сайт](https://tinkoff.github.io/investAPI/token/)