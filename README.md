# credit_service
### Project Description

Welcome to the credit service README file! This project is designed to provide about how the credit system functions. It includes the possibility of taking a loan, repayment, loan calculation, payments by months and monitoring the status of the account, in addition, there is a calculation of the amount with a transfer to another currency.

### Microservices Description

##### Authentication and Authorization Service: 
This service is responsible for authenticating users and managing their access to the functionality of the credit service.(not ready)!!!

##### Create Account Service: 
Here, managers can create new accounts based on data from clients, an account is a necessary part for all systems to work, a user without an account cannot get credit.

##### Create Product Service: 
Here managers can create and add new products and services to the database for which you can get a loan.

##### Create Order Service: 
Here, authorized users through the account can apply for a loan by providing the necessary information. This service processes applications, conducts preliminary analysis and sends data to the decision service.

##### Decision Order Service: 
Based on the analysis of data from applications, this service determines whether to approve the application or not. Decision on a positive outcome transferred to the Create Agreement service.

##### Create Agreement Service:
After approval, the applications will be concluded contracts which will be transferred to the create credit service.

##### Create Credit Service:
Here loans and payment schedules are formed depending on the annuity or differentiated types of calculation. The loan is considered repaid if all payments are paid or early repayment is possible for some loans.


### Technology Stack

The project is developed using modern technologies: 

Microservices Framework: Spring Boot 

Programming Language: Java 

Database: MySQL 

Validation:Spring Boot Validation

Database Migration: Liquibase

Site parser: Jsoup

Auxiliary Libraries: Lombok

Version Control System: Git

Development Environment: IntelliJ IDEA

### Database

I use MySQL to store data about clients, their applications, decisions, and so on. The database provides reliable and scalable storage of the necessary information. To connect, you need to initialize MySQL locally.

### Links and Project Description

Additional information about the project, its components, installation instructions, and running can be found in the following resources:

[Project Description](https://drive.google.com/drive/folders/1CwP-yQFr2-55s3xf3Yw96rgKyAaC0CZE)

Thank you for choosing my credit service!




