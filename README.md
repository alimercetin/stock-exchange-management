# **STOCK EXCHANGE MANAGEMENT APPLICATION**

Stock Exchange Management Application provide endpoints to manage Stocks and Stock Exchanges. H2, in memory database, is used for storing data. ingredients. Jakarta validations and annotations are used for validation. Errors are caught at the controller level by controller advice.

Security part is not implemented. For simplest solution, we can use PreAuthorize annotation with a custom security logic provided by Spring Security. If we will run this application on kubernetes environment, we can utilize service mesh to define network rules. If we aim for security as platform, we can use Keycloak or similar solutions as Identity Access Management tool and on a gateway like NGINX we can filter the incoming request with an infrastructure checking authorization and authentication with JWT token based solution.  

## **Technology Stack**

* Java 17
* Gradle
* Spring Boot 3.1.0
* Lombok
* H2 database
* Junit
* Hibernate
* JPA
* Docker

## **Getting Started**

1. Clone the repository from https://github.com/alimercetin/stock-exchange-management.git
2. Import project in IDE
3. Choose the SpringBootApplication in this project StockExchangeManagementApplication.java
4. Right click on the file and select Run application

## **Initial Data**
* Schema for database are defined in schema.sql file
* Initial data load is provided in data.sql file

## **Build and run project**

```
    ./gradlew clean test
    java -jar build/libs/stock-exchange-management-0.0.1-SNAPSHOT.jar
```

## **Run with Docker**

```
    ./gradlew clean test
    docker-compose up --build
 ```   


## **API Endpoints**

*  POST `/api/v1/stocks`

The POST **stock** API creates a stock. It saves the stock to the database.

Sample request body

```
{
    "name": "STOCK",
    "description": "Stock",
    "currentPrice": "1121"
}
```

*  PUT `/api/v1/stocks/{id}/price`

It updates price of the stock.

Sample request body

```
{
    "newPrice": "1121"
}
```

*  DELETE `/api/v1/stocks/{id}`

It deletes stock and related stock exchange relations.

*  GET `/api/v1/stock-exchanges/{name}`

It returns stock exchange information by stock exchange name.

Sample response

```
{
    "id": 2,
    "name": "XSHG",
    "description": "Shangai",
    "liveInMarket": false,
    "stocks": [
        {
            "id": 6,
            "name": "ASELS",
            "description": "Aselsan",
            "currentPrice": 40,
            "lastUpdate": "2023-10-18T02:23:39.213988Z"
        },
        {
            "id": 7,
            "name": "KHC",
            "description": "Kraft Heinz",
            "currentPrice": 12,
            "lastUpdate": "2023-10-18T02:23:39.214115Z"
        },
        {
            "id": 3,
            "name": "ANET",
            "description": "Arista Networks",
            "currentPrice": 196,
            "lastUpdate": "2023-10-18T02:23:39.213571Z"
        },
        {
            "id": 8,
            "name": "AAPL",
            "description": "Apple",
            "currentPrice": 179,
            "lastUpdate": "2023-10-18T02:23:39.214238Z"
        }
    ]
}
```

*  POST `/api/v1/stock-exchanges/{name}/stock`

It adds a stock to a stock exchange.

Sample request body

```
{
    "stockId": 10
}
```

*  DELETE `/api/v1/stock-exchanges/{name}/stock/{stockId}`

It removes a stock from a stock exchange.
