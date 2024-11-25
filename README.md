# E-Commerce Project

This project is a simple e-commerce platform built using Java and Spring Boot. 
It handles customers, shopping carts, and products, providing basic functionality 
for managing user data and cart items. It integrates with a PostgreSQL database.


## Features
- Customer management (Create, View)
- Shopping Cart management (Create, Update, Remove items)
- Product management (Basic CRUD)
- Item management in carts
- Order management

## Technologies Used
- **Java** (Spring Boot)
- **PostgreSQL** (Database)
- **JPA/Hibernate** (ORM)

## How to Run
1. Clone the repository
   ```bash
   git clone https://github.com/cisagl/E-Commerce
   ```
2. Setup the database (PostgreSQL)
3. Update the database connection settings with your PostgreSQL credentials from the **resources** folder
4. Run the project using your IDE or with Maven/Gradle
5. Access the API endpoints to test the functionality

# Endpoints


| **HTTP Method** | **Endpoint**                              | **Description**            |
|-----------------|-------------------------------------------|----------------------------|
| **POST**        | `/customer/save`                          | Create a new customer      |
| **GET**         | `/customer/all`                           | Get all customers          |
| **GET**         | `/cart/{id}`                              | Get a cart by cart ID      |
| **POST**        | `/cart/update`                            | Add a product to cart      |
| **POST**        | `/cart/remove?cartId={id}&productId={id}` | Remove a product from cart |
| **Delete**      | `/cart/clear?cartId={id}`                 | Clear a cart               |
| **GET**         | `/product/all`                            | Get all products           |
| **POST**        | `/product/save`                           | Add a new product          |
| **PUT**         | `/product/{id}`                           | Update a product by ID     |
| **DELETE**      | `/product/{id}`                           | Delete a product           |
| **GET**         | `/order/{code}`                           | Get a order by ID          |
| **POST**        | `/order/place`                            | Create an order            |

