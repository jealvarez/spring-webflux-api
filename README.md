# **Spring Webflux API**

## **Requirements**

* PostgreSQL
* Java 11+

## **Setup**

* Just for the first time, execute

```sh
pgcli -h 127.0.0.1 -u postgres -d postgres < scripts/sql/init.sql
```

* Build the application

```sh
gradle clean
gradle build
```

* Run the application

```sh
java -jar build/libs/spring-webflux-api-0.0.1-SNAPSHOT.jar
```

* Go to `http://127.0.0.1:8080`
