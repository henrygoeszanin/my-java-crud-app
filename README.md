# My Java CRUD Application

This is a simple Java CRUD application built using Spring Boot. The application demonstrates how to perform basic Create, Read, Update, and Delete operations using a RESTful API.

## Project Structure

```
my-java-crud-app
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── myjavacrudapp
│   │   │   │       ├── controller
│   │   │   │       │   └── UserController.java
│   │   │   │       ├── exceptions
│   │   │   │       │   ├── CustomException.java
│   │   │   │       │   └── GlobalExceptionHandler.java
│   │   │   │       ├── model
│   │   │   │       │   └── user
│   │   │   │       │       ├── User.java
│   │   │   │       │       └── UserDto.java
│   │   │   │       ├── repository
│   │   │   │       │   └── UserRepository.java
│   │   │   │       ├── service
│   │   │   │       │   └── UserService.java
│   │   │   │       └── Main.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       ├── java
│       │   └── com
│       │       └── myjavacrudapp
│       │           └── MyJavaCrudAppApplicationTests.java
│       └── resources
├── pom.xml
└── README.md
```

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Maven

## Setup Instructions

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd my-java-crud-app
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

4. **Access the API:**
   The application will be running at `http://localhost:8080`. You can use tools like Postman or curl to interact with the API.

## Usage

- **Create a new user:** `POST /api/usuarios`
- **Get all users:** `GET /api/usuarios`
- **Get a user by ID:** `GET /api/usuarios/{id}`
- **Update a user:** `PUT /api/usuarios/{id}`
- **Delete a user:** `DELETE /api/usuarios/{id}`

## License

This project is licensed under the MIT License. See the LICENSE file for details.