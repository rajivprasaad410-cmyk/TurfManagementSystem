# Turf Management System

A Java-based Turf Management System designed to manage turf details and customer bookings using JDBC and MySQL. The application provides a simple console-based interface for performing turf and booking operations while maintaining data in a relational database.

## Project Overview

The Turf Management System is a database-driven application developed using Java, JDBC, and MySQL. It allows users to manage available turfs and handle customer bookings through a structured CRUD-based system.

The project demonstrates the practical implementation of:

* Java programming
* Object-Oriented Programming
* JDBC database connectivity
* MySQL relational database management
* CRUD operations
* SQL queries
* Prepared statements
* Exception handling
* Maven project management

## Features

### Turf Management

The system allows the administrator or user to:

* Add new turf details
* View available turf details
* Update turf information
* Delete turf records
* Manage turf-related information stored in the database

### Booking Management

The system provides functionality to:

* Create new turf bookings
* View booking details
* Update booking information
* Delete booking records
* Maintain relationships between turfs and bookings

## Technology Stack

| Technology    | Purpose                           |
| ------------- | --------------------------------- |
| Java          | Application development           |
| JDBC          | Database connectivity             |
| MySQL         | Data storage and management       |
| Maven         | Dependency and project management |
| IntelliJ IDEA | Development environment           |

## Project Structure

```text
TurfManagementSystem/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── turf/
│                   ├── ...
│                   └── ...
│
├── pom.xml
├── .gitignore
└── README.md
```

The Java source files are organized under the `com.turf` package.

The application follows a structured approach where Java classes handle application logic and JDBC is used to communicate with the MySQL database.

## Database Design

The project uses MySQL as the backend database.

The primary entities used in the system are:

### Turfs

The `turfs` table stores information related to the available turfs.

Typical information includes:

* Turf ID
* Turf name
* Location
* Price
* Availability

### Bookings

The `bookings` table stores information about turf reservations.

Typical information includes:

* Booking ID
* Turf ID
* Customer details
* Booking date
* Booking time
* Booking-related information

The `turf_id` can be used to establish a relationship between bookings and the corresponding turf.

## Application Flow

```text
User
  |
  v
Console Application
  |
  v
Java Application Logic
  |
  v
JDBC
  |
  v
MySQL Database
  |
  v
Turfs / Bookings
```

The user interacts with the console application. The Java application processes the requested operation and uses JDBC to execute SQL queries against the MySQL database.

## JDBC Workflow

The application follows the standard JDBC workflow:

```text
1. Establish database connection
          |
          v
2. Prepare SQL query
          |
          v
3. Execute query
          |
          v
4. Process ResultSet
          |
          v
5. Close database resources
```

JDBC provides the connection between the Java application and MySQL.

## CRUD Operations

The application demonstrates the four fundamental database operations.

### Create

Used to insert new turf or booking records.

```sql
INSERT INTO ...
```

### Read

Used to retrieve turf and booking information.

```sql
SELECT * FROM ...
```

### Update

Used to modify existing records.

```sql
UPDATE ...
```

### Delete

Used to remove records.

```sql
DELETE FROM ...
```

These operations allow the application to maintain and manage the database dynamically.

## PreparedStatement

The application uses JDBC statements to execute SQL queries.

`PreparedStatement` is useful because it allows values to be passed into SQL queries using parameters rather than constructing SQL strings manually.

Example:

```java
PreparedStatement ps =
    connection.prepareStatement(
        "SELECT * FROM turfs WHERE turf_id = ?"
    );

ps.setInt(1, turfId);

ResultSet rs = ps.executeQuery();
```

Using parameterized queries also helps protect applications against SQL injection.

## Requirements

Before running the project, make sure the following are installed:

* Java JDK
* MySQL Server
* MySQL Workbench or another MySQL client
* Maven
* IntelliJ IDEA or another Java IDE

## Database Setup

### 1. Create the Database

Create a MySQL database for the application.

```sql
CREATE DATABASE turf_management;
```

### 2. Select the Database

```sql
USE turf_management;
```

### 3. Create the Required Tables

Create the `turfs` and `bookings` tables according to the schema used by the application.

Make sure the column names and data types match the SQL queries used in the Java source code.

### 4. Configure Database Credentials

Update the database connection configuration in the Java application with your local MySQL credentials.

Example:

```java
String url = "jdbc:mysql://localhost:3306/turf_management";
String username = "root";
String password = "your_password";
```

Do not commit real database passwords or other credentials to GitHub.

## Running the Project

### Clone the Repository

```bash
git clone https://github.com/rajivprasaad410-cmyk/TurfManagementSystem.git
```

### Navigate to the Project

```bash
cd TurfManagementSystem
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

Open the project in IntelliJ IDEA and run the main Java class.

Make sure the MySQL server is running before starting the application.

## Maven

The project uses Maven for project and dependency management.

The `pom.xml` file contains the project's configuration and dependencies.

Maven simplifies:

* Dependency management
* Project compilation
* Build management
* Standard project structure

## Exception Handling

Database operations can generate exceptions such as connection failures, SQL syntax errors, and invalid database operations.

The application handles these exceptions using Java exception-handling mechanisms to prevent database errors from terminating the application unexpectedly.

## Key Concepts Demonstrated

This project provides practical experience with:

### Java

* Classes and objects
* Methods
* Constructors
* Encapsulation
* Conditional statements
* Loops
* Exception handling
* Collections and basic Java programming concepts

### JDBC

* `Connection`
* `PreparedStatement`
* `Statement`
* `ResultSet`
* SQL execution
* Database connectivity

### MySQL

* Database creation
* Table creation
* Primary keys
* Foreign keys
* SQL queries
* CRUD operations
* Relational data management

### Software Development

* Maven project structure
* Separation of application logic and database operations
* Database-driven application development

## Advantages

* Simple and easy-to-use console interface
* Centralized database storage
* Supports CRUD operations
* Demonstrates Java-to-MySQL integration
* Uses JDBC for database communication
* Provides a foundation for developing a larger turf booking platform

## Future Enhancements

The current console-based application can be extended with additional features such as:

* Graphical user interface or web interface
* User authentication and authorization
* Admin and customer roles
* Turf availability checking
* Automatic booking conflict detection
* Payment integration
* Booking cancellation
* Search and filtering
* Booking history
* Email or SMS notifications
* REST API integration
* Spring Boot backend
* Frontend using HTML, CSS, JavaScript, or React

## Learning Outcomes

Through this project, the following concepts can be practically understood:

1. How Java applications communicate with relational databases.
2. How JDBC establishes and manages database connections.
3. How SQL queries are executed from Java.
4. How CRUD operations are implemented.
5. How relational tables can be used to represent real-world entities.
6. How Maven manages a Java project and its dependencies.
7. How database-driven applications are structured.

## Author

**Rajiv Prasaad**

GitHub:
https://github.com/rajivprasaad410-cmyk

## License

This project is intended for educational and academic purposes.
