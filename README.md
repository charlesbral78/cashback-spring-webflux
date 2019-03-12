# Cashback Spring Webflux (Web on Reactive Stack)

Api Rest para calculo de cashback* em um e-commerce usando Programação Reativa em Java com Spring Boot, WebFlux, MongoDB e Gradle

Foi utilizado o Gradle para acelerar o processo de build.

Foi utilizado o Feign como client para consumir a API do Spotify. Na inicialização, os dados sao consumidos da API do Spotify e gravado no banco NoSQL MongoDB.

Foi utilizado o Designer Pattern Strategy' para calcular o valor do cashback

*cashback para um programa de fidelidade com o objetivo de aumentar o volume de vendas e conquistar novos clientes, baseado em uma tabela de porcentagem de cashback por gênero musical e dia da semana.


## Como rodar o programa?

1) Baixe o codigo fonte ou faça um fork (aconselhável)

2) Instale o Java 8 ou superior

3) Instale o Gradle (https://gradle.org/install/)

4) Executar na raiz da aplicação
   $ ./gradlew clean build && java -jar build/libs/cashback-0.0.1.jar


##Documentação dos serviços disponíveis

## Create new products

Request:

```
POST localhost:8080/api/v1/products
```

Body: JSON(application/json)

```json
{
	"name" : "Rock Music",
	"price" : 12.50,
	"category" : "ROCK"
}
```

Response: JSON

```json
{
    "id": "5c8686b89cb58427d19775a1",
    "name": "Rock Music",
    "price": 12.5,
    "category": "ROCK"
}
```

## Find one product by id

Request:

```
GET localhost:8080/api/v1/products/{id}
```

Response: JSON

```json
{
    "id": "5c8686b89cb58427d19775a1",
    "name": "Rock Music",
    "price": 12.5,
    "category": "ROCK"
}
```

## Find products by category pageable orderby name asc

Request:

```
GET localhost:8080/api/v1/products/by-category-pageable-orderby-name-asc?category=ROCK&pageNumber=0&pageSize=2
```

Response: JSON

```json
[
    {
        "id": "5c8686b89cb58427d19775a1",
        "name": "Rock Music",
        "price": 12.5,
        "category": "ROCK"
    },
    {
        "id": "5c868e629cb58427d19775a5",
        "name": "ROCK 60",
        "price": 45.67,
        "category": "ROCK"
    }
]
```

## Create new orders

Request:

```
POST localhost:8080/api/v1/orders
```

Body: JSON(application/json)

```json
{
	"entries" : [
		{
			"product" : {
						    "id": "5c8686b89cb58427d19775a1",
						    "name": "Rock Music",
						    "price": 12.50,
						    "category": "ROCK"
						},
			"quantity" : 3
		},

		{
			"product" : {
						    "id": "5c8687f39cb58427d19775a3",
						    "name": "Pop Music",
						    "price": 35.40,
						    "category": "POP"
						},
			"quantity" :5
		}
	]
}
```

Response: JSON

```json
{
    "id": "5c8688599cb58427d19775a4",
    "saleDate": "2019-03-11T16:10:01.146+0000",
    "entries": [
        {
            "product": {
                "id": "5c8686b89cb58427d19775a1",
                "name": "Rock Music",
                "price": 12.5,
                "category": "ROCK"
            },
            "quantity": 3,
            "cashback": 3.75
        },
        {
            "product": {
                "id": "5c8687f39cb58427d19775a3",
                "name": "Pop Music",
                "price": 35.4,
                "category": "POP"
            },
            "quantity": 5,
            "cashback": 12.39
        }
    ],
    "totalCashback": 16.14
}
```

## Find one order by id

Request:

```
GET localhost:8080/api/v1/orders/{id}
```

Response: JSON

```json
{
    "id": "5c8688599cb58427d19775a4",
    "saleDate": "2019-03-11T16:10:01.146+0000",
    "entries": [
        {
            "product": {
                "id": "5c8686b89cb58427d19775a1",
                "name": "Rock Music",
                "price": 12.5,
                "category": "ROCK"
            },
            "quantity": 3,
            "cashback": 3.75
        },
        {
            "product": {
                "id": "5c8687f39cb58427d19775a3",
                "name": "Pop Music",
                "price": 35.4,
                "category": "POP"
            },
            "quantity": 5,
            "cashback": 12.39
        }
    ],
    "totalCashback": 16.14
}
```

## Find orders by saleStartDate and saleEndDate pageable orderBy saleDate desc

Request:

Use DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

```
GET localhost:8080/api/v1/orders/by-salestartdate-and-saleenddate-pageable-orderby-saledate-desc?saleStartDate=2019-03-08 01:00:00&saleEndDate=2019-03-11 18:00:00&pageNumber=0&pageSize=2
```

Response: JSON

```json
[
    {
        "id": "5c8688599cb58427d19775a4",
        "saleDate": "2019-03-11T16:10:01.146+0000",
        "entries": [
            {
                "product": {
                    "id": "5c8686b89cb58427d19775a1",
                    "name": "Rock Music",
                    "price": 12.5,
                    "category": "ROCK"
                },
                "quantity": 3,
                "cashback": 3.75
            },
            {
                "product": {
                    "id": "5c8687f39cb58427d19775a3",
                    "name": "Pop Music",
                    "price": 35.4,
                    "category": "POP"
                },
                "quantity": 5,
                "cashback": 12.39
            }
        ],
        "totalCashback": 16.14
    },
    {
        "id": "5c85f7dc25951251ec49def8",
        "saleDate": "2019-03-11T05:53:32.688+0000",
        "entries": [
            {
                "product": {
                    "id": "069dd216-ab2d-4f51-a91b-ede71cf2dc0f",
                    "name": "Product xxx",
                    "price": 46.99,
                    "category": "POP"
                },
                "quantity": 3,
                "cashback": 9.8679
            },
            {
                "product": {
                    "id": "7d6ca7bd-2961-4010-86a0-415371a49598",
                    "name": "Product yyy",
                    "price": 12,
                    "category": "ROCK"
                },
                "quantity": 5,
                "cashback": 6
            }
        ],
        "totalCashback": 15.8679
    }
]
```