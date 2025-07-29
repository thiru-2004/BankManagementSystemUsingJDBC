# 🏦 Bank Management System (Java + JDBC + MySQL)

A console-based **Bank Management System** project that simulates basic banking operations such as customer registration, login, balance enquiry, and transaction management. Built using **Java**, **JDBC**, and **MySQL** as part of an academic project.

---

## 🚀 Features

- 📝 **Customer Registration** (with unique Account Number & PIN)
- 🔐 **Customer Login** (via Email ID or Account Number)
- 🔐 **CAPTCHA Validation** for added security
- 💳 **Balance Enquiry**
- 💸 **Deposit & Withdrawal**
- 📄 **Transaction History**
- 🔐 **Admin Login** for account control
- 🛡️ **Input Validation** (PIN, email format, etc.)

---

## 📂 Project Structure

BankManagementSystem/
│
├── dao/ # Data Access Layer (JDBC Logic)
│ └── CustomerDAO.java
│
├── model/ # POJOs for Customer Info
│ └── Customer.java
│
├── service/ # Business Logic Layer
│ └── CustomerServices.java
│
├── validation/ # Input validation methods
│ └── InputValidator.java
│
└── main/ # Application Entry Point
└── BankMainClass.java

yaml
Copy
Edit

---

## 💻 Technologies Used

- **Java (Core Java)**
- **JDBC** (Java Database Connectivity)
- **MySQL** (Database)
- **SQL** (CRUD operations)
- **Java Collections & Exception Handling**
- **Object-Oriented Programming Principles**

---

## ⚙️ How to Run the Project

1. **Clone the Repository**  
git clone  'https://github.com/thiru-2004/BankManagementSystemUsingJDBC.git/'



2. **Setup MySQL Database**  
- Create a database (e.g., `bankdb`)
- Create necessary tables (`customer_details`, `transactions`, etc.)
- Insert initial admin credentials if needed

3. **Update Database Config in Code**  
Modify JDBC URL, username, and password in your `CustomerDAO.java`.

4. **Compile and Run the App**  
Use any Java IDE (like Eclipse/IntelliJ) or run from terminal:
javac BankMainClass.java
java BankMainClass



---

## 📚 Learning Outcomes

- End-to-end implementation of **JDBC**
- Modular structure using ** MonoLithic  Architecture**
- Strong command over **Java OOPs**, exception handling & I/O
- Real-world simulation of a basic **banking workflow**

---



## 🙌 Acknowledgements

- Guided by: Sai Sir
- Built with ❤️ by Thirupathi Rao Pavuluri

---



This project is for educational purposes only.
