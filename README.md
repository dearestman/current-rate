# Получение курса валюты с официального сайта ЦБ России

## Как запустить

Для запуска необходимо, чтобы был установлен docker, docker-compose, а также docker был включен.

Для загрузки проекта с docker hub необходимо создать файл docker-compose.yml (файл находится в корне проекта):

```
version: '3'

services:

  app:
    image: "dearestman/currency-rate"
    container_name: 'currency-rate'
    ports:
      - 8003:8003
```

При необходимости, можно выбрать свой порт, откорректировав файл docker-compose.yml:

```
 app:
    image: "dearestman/currency-rate"
    container_name: 'currency-rate'
    ports:
      - <Свой порт>:8003
```

Далее при помощи командной строки перейти в директорию, где находится данный файл и выполнить команду:

```
     docker-compose up -d   
```

## Общее описание

Приложение это Api для подключения к сайту ЦБ России для получения отношения рубля к другим валютам

## Описание endpoints


### POST /currency

 Post запрос, который берет информацию с официального сайт центробанка рф по соотношению
 рубля к конкретной валюте на конкретную дату: https://www.cbr.ru/scripts/XML_daily.asp
 Тип запроса: POST
 Url: /currency
 @param request
 тело запроса:
```
 {
     "date" : "02/02/2020",
     "charName" : "EUR"
 }
```
 Формат ответа:
```
{
     "charCode": "EUR",
     "nominal": 1,
     "name": "Евро",
    "value": 69.5976
}
```

