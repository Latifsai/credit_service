# credit_service
### Project Description

Welcome to the Loan Service README! This project aims to tell about how the credit system functions. The project is a rest full application and simulates the behavior of credit applications. It includes the possibility of taking a loan, repayment, calculating a loan, payments by months and monitoring the status of an account, in addition, there is a calculation of the amount with a transfer to another currency.

---------------------------------

## Requirements
* CRUD operations for all entities.
* Possibility of opening a loan for a given product in the database.
* Current conversion of product currency into account currency.
* Opening bank cards.
* Taking out loans.
* Preliminary review of loan payments.
* Automatic write-off of credit payments on time, taking into account credit holidays.
* Penalties will be charged in case of non-payment.
* Possibility of early repayment.
* Balance replenishment.
------------------------------------

## Getting Started
* Checkout the project from GitHub:
```
git clone https://github.com/Latifsai/credit_service
```
* Open IDE of your choice and Import as existing maven project in your workspace:

```
- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App
```
----------------
### SECURITY

Protection is based on technology jwt token. The life cycle leaves 30 minutes.Two levels of protection are available: "Client" level and "Manager" level. Protection is installed on both standard KRUD operations, as well as for special operations. In addition, it is possible to access without authentication for some GET requests


### Technology Stack

The project is developed using modern technologies: 

* Spring BOOT 
* JAVA
* SPRING SECURITY
* JWT-token AUTHORIZATION
* SPRING MVC
* MySQL 
* Spring Boot Validation
* Liquibase
* Jsoup
* LOMBOK
* GitHub
* JUnit 5 JUPITER
* MOCKITO
* MAVEN
* SWAGGER
* JACOCO
* DOCKER
* DOCKER COMPOSE
### Database

I use MySQL to store data about clients, their applications, decisions, and so on. The database provides reliable and scalable storage of the necessary information. 

> **To connect, you need to initialize MySQL locally.**

```
jdbc:mysql://localhost:3306/credit_service_database
```
#### Database stucture
![erd](https://github.com/Latifsai/credit_service/blob/main/database.png)

####Tests
![](https://github.com/Latifsai/credit_service/blob/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%D0%BE%D1%82%202023-09-17%2013-32-27.png)
![](https://github.com/Latifsai/credit_service/blob/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%D0%BE%D1%82%202023-09-17%2014-47-19.png)

### Links and Project Description

Additional information about the project, its components, installation instructions, and running can be found in the following resources:

[Project Description](https://drive.google.com/drive/folders/1CwP-yQFr2-55s3xf3Yw96rgKyAaC0CZE)

Thank you for choosing my credit service!




