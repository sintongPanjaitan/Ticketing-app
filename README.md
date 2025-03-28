# Ticketing App

## Prerequisites
Before running the application, ensure you have the following installed:
- **Java 17** or later
- **Maven 3.8+**
- **PostgreSQL** (if using a database)
- **Git** (optional, for version control)

## Setup Instructions
### 1. Clone the Repository
```sh
git clone git@github.com:sintongPanjaitan/Ticketing-app.git
cd Ticketing-app
```

### 2. Configure the Database
If you're using **PostgreSQL**, update the `application.properties` or `application.yml` file with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build the Project
Run the following command to install dependencies and build the project:
```sh
mvn clean install
```

### 4. Run the Application
Use this command to start the Spring Boot application:
```sh
mvn spring-boot:run
```

### 5. Test the API
You can test the API using **Postman** or **cURL**:
```sh
curl -X GET http://localhost:8080/api/users etc
```

### 6. Running Tests
Run unit tests with:
```sh
mvn test
```

## Contributing
Feel free to submit issues or pull requests.

## License
This project is licensed under the MIT License.

