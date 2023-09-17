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

### Database

I use MySQL to store data about clients, their applications, decisions, and so on. The database provides reliable and scalable storage of the necessary information. 

> **To connect, you need to initialize MySQL locally.**

```
jdbc:mysql://localhost:3306/credit_service_database
```
#### Database stucture
![] ()

### Links and Project Description

Additional information about the project, its components, installation instructions, and running can be found in the following resources:

[Project Description](https://drive.google.com/drive/folders/1CwP-yQFr2-55s3xf3Yw96rgKyAaC0CZE)

Thank you for choosing my credit service!




