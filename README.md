# credit_service
### Project Description

Welcome to the Loan Service README! This project aims to tell about how the credit system functions. The project is a rest full application and simulates the behavior of credit applications. It includes the possibility of taking a loan, repayment, calculating a loan, payments by months and monitoring the status of an account, in addition, there is a calculation of the amount with a transfer to another currency.

---------------------------------

## Requirements
* CRUD operations for customers and accounts.
* Support for payments, top-ups and early repayments
* Loan payment preview
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
### Microservices Description

* ##### Authentication and Authorization Service: 
This service is responsible for authenticating users and managing their access to the functionality of the credit service.(not ready)!!!

* ##### Create Account Service: 
Here, managers can create new accounts based on data from clients, an account is a necessary part for all systems to work, a user without an account cannot get credit.

* ##### Create Product Service: 
Here managers can create and add new products and services to the database for which you can get a loan.

* ##### Create Order Service: 
Here, authorized users through the account can apply for a loan by providing the necessary information. This service processes applications, conducts preliminary analysis and sends data to the decision service.

* ##### Decision Order Service: 
Based on the analysis of data from applications, this service determines whether to approve the application or not. Decision on a positive outcome transferred to the Create Agreement service.

* ##### Create Agreement Service:
After approval, the applications will be concluded contracts which will be transferred to the create credit service.

* ##### Create Credit Service:
Here loans and payment schedules are formed depending on the annuity or differentiated types of calculation. The loan is considered repaid if all payments are paid or early repayment is possible for some loans.


### Technology Stack

The project is developed using modern technologies: 

* Microservices Framework: Spring Boot 
* Programming Language: Java 
* Database: MySQL 
* Validation: Spring Boot Validation
* Database Migration: Liquibase
* Site parser: Jsoup
* Auxiliary Libraries: Lombok
* Version Control System: Git
* Development Environment: IntelliJ IDEA
* Test: JUnit, Mockito

### Maven Dependencies
```
spring-boot-starter-data-rest
spring-boot-starter-validation
spring-boot-starter-web
liquibase-core
spring-boot-devtools
mysql-connector-j
lombok
spring-boot-starter-test
spring-boot-starter-data-jpa
jsoup
```

### Database

I use MySQL to store data about clients, their applications, decisions, and so on. The database provides reliable and scalable storage of the necessary information. 

> **To connect, you need to initialize MySQL locally.**

```
jdbc:mysql://localhost:3306/credit_service_database
```

### Links and Project Description

Additional information about the project, its components, installation instructions, and running can be found in the following resources:

[Project Description](https://drive.google.com/drive/folders/1CwP-yQFr2-55s3xf3Yw96rgKyAaC0CZE)

Thank you for choosing my credit service!




