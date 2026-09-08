# Turf Management System

A Java-based console application designed to streamline turf facility management, slot bookings, and customer reservations. Built using Java, Apache Maven, JDBC, and MySQL, this project demonstrates clean database connectivity, secure environment variable configuration, and standard relational database integration.

---

## Tech Stack

* **Language:** Java
* **Build & Dependency Tool:** Apache Maven
* **Database Management System:** MySQL
* **Database Connectivity:** JDBC (MySQL Connector/J)
* **IDE:** IntelliJ IDEA

---

## Features

* **Database Integration:** Full CRUD capability using standard JDBC drivers.
* **Environment Security:** Hardcoded database credentials are excluded; runtime authentication uses system environment variables.
* **Maven Architecture:** Clean dependency management and standard directory structures (`src/main/java`, `src/main/resources`).

---

## Database Setup

Before running the application, set up the `turf_management` database schema in MySQL Workbench or via the MySQL CLI:

```sql
-- Create database schema
CREATE DATABASE IF NOT EXISTS turf_management;
USE turf_management;

-- Create Turf Details table (Example structure)
CREATE TABLE IF NOT EXISTS turfs (
    turf_id INT AUTO_INCREMENT PRIMARY KEY,
    turf_name VARCHAR(100) NOT NULL,
    location VARCHAR(150) NOT NULL,
    price_per_hour DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Bookings table (Example structure)
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    turf_id INT,
    booking_date DATE NOT NULL,
    time_slot VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    FOREIGN KEY (turf_id) REFERENCES turfs(turf_id) ON DELETE CASCADE
);

```

---

## Getting Started

### Prerequisites

Ensure you have the following installed on your machine:

* **Java Development Kit (JDK):** Version 11 or higher
* **Apache Maven:** Version 3.6 or higher
* **MySQL Server:** Version 8.0 or higher

### Installation & Configuration

1. **Clone the Repository:**
```bash
git clone [https://github.com/rajivprasaad410-cmyk/TurfManagementSystem.git](https://github.com/rajivprasaad410-cmyk/TurfManagementSystem.git)
cd TurfManagementSystem

```


2. **Configure Database Credentials:**
The project is configured to read your local MySQL password via an environment variable named `DB_PASSWORD`.
* **Option A: Setting environment variables in Terminal/Command Prompt**
* **Windows (CMD):**
```cmd
set DB_PASSWORD=your_mysql_password

```


* **Windows (PowerShell):**
```powershell
$env:DB_PASSWORD="your_mysql_password"

```


* **Linux / macOS:**
```bash
export DB_PASSWORD=your_mysql_password

```




* **Option B: Setting environment variables in IntelliJ IDEA**
1. Open **Run > Edit Configurations...**
2. Select your main application entry point under **Application**.
3. Locate **Environment variables** and enter:
```text
DB_PASSWORD=your_mysql_password

```


4. Click **Apply** and **OK**.




3. **Build and Run:**
Build the project using Maven:
```bash
mvn clean compile

```


Run the main class from your IDE or execute:
```bash
mvn exec:java -Dexec.mainClass="com.turf.Main"

```



---

## Security & Best Practices

* **Sensitive Data Protection:** Database passwords and local environment details are protected using `.gitignore` and system environment variable fallbacks.
* **Build Artifact Exclusion:** Compiled binaries, Maven build outputs (`target/`), and local IDE configurations (`.idea/`) are excluded from repository tracking.

---

## License

This project is open-source and available for educational and portfolio demonstration purposes.

```

```
