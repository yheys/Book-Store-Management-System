# Bookstore Management System

A console-based bookstore management system built in Java for an Object-Oriented Programming (OOP) class project.

---

## Project Overview

This system simulates a real bookstore where users can create accounts, browse books, and access multiple services such as reading, renting, buying, and saving favorites.

---

## Features

- **User Authentication** — login or create an account (username, password, email, national ID)
- **Book Catalog** — browse available book categories then display the books based on their categories with name, author, type, and year
- **Read Service** — choose a reading session (30 min, 2 hr, 6 hr+) with optional coffee
- **Rent Service** — rent a book by the day with maximum rental period validation
- **Buy Service** — purchase copies with optional delivery
- **Favorites** — save and view your favorite books per account

---

## OOP Concepts Used

| Concept | Where Applied |
|---|---|
| **Encapsulation** | Private fields + getters in `Book` and `User` |
| **Abstraction** | Abstract `Service` class with `performService()` |
| **Inheritance** | `Read`, `Rent`, `Buy` all extend `Service` |
| **Polymorphism** | `main` calls `service.performService()` on any service type |

---

## Project Structure

```
BookstoreMVP/
│
├── Book.java             # Book class with all book data and getters
├── User.java             # User class with favorites management
├── AccountManager.java   # Handles login and account creation
├── Service.java          # Abstract base class for all services
├── Read.java             # Read service — extends Service
├── Rent.java             # Rent service — extends Service
├── Buy.java              # Buy service — extends Service
└── Bookstore.java        # Main class — entry point


