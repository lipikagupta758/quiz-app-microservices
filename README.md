# Quiz App Microservices

This project is a microservices-based Quiz Application built with Spring Boot. It demonstrates a modular architecture using multiple services for quiz management, question management, service discovery, and API gateway.

# Features
- Create quizzes with random questions from a selected category
- Add and manage quiz questions
- Filter questions by category
- Submit quiz responses and receive a score

## Project Structure

- **api-gateway**: Handles routing and acts as a single entry point for all client requests.
- **quiz-service**: Manages quizzes, quiz logic, and interacts with the question service.
- **question-service**: Manages questions and their CRUD operations.
- **service-registry**: Service discovery using Netflix Eureka.

## Technologies Used

- Java 17+ (or compatible version)
- Spring Boot
- Spring Cloud (Eureka, OpenFeign)
- Maven

## Getting Started

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6+

### Database Configuration (IMPORTANT)

Before running the application, you must update the database connection details in the `application.properties` file for both `quiz-service` and `question-service`:

```
spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

- Replace `<host>`, `<port>`, `<database>`, `<your-db-username>`, and `<your-db-password>` with your actual MySQL database details.
- Ensure the database exists and is accessible.
- You may also need to update Hibernate or JPA properties as needed for your environment.

### Running the Application

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd quiz-app-microservices
   ```

2. **Edit Database Configuration**

   Update the `application.properties` file in each service (`quiz-service`, `question-service`) with your database connection details (URL, username, password, etc.) before running the application.

3. **Start the Service Registry (Eureka)**

   ```bash
   cd service-registry
   mvn spring-boot:run
   ```

4. **Start the Question Service**

   ```bash
   cd ../question-service
   mvn spring-boot:run
   ```

5. **Start the Quiz Service**

   ```bash
   cd ../quiz-service
   mvn spring-boot:run
   ```

6. **Start the API Gateway**

   ```bash
   cd ../api-gateway
   mvn spring-boot:run
   ```

7. **Access the Application**
   - Eureka Dashboard: [http://localhost:8761](http://localhost:8761)
   - API Gateway: [http://localhost:8080](http://localhost:8080) (default)

## API Endpoints

### quiz-service

- **POST /quiz/create**
  - Create a new quiz.
  - Input (JSON body):
    ```json
    {
      "categoryName": "string",
      "numQuestions": integer,
      "title": "string"
    }
    ```

- **GET /quiz/get/{quizid}**
  - Get all questions for a quiz.
  - Input: Path variable `quizid` (integer)

- **POST /quiz/submit/{quizid}**
  - Submit answers for a quiz and get the score.
  - Input:
    - Path variable `quizid` (integer)
    - JSON body: List of responses
      ```json
      [
        {
          "id": integer,
          "response": "string"
        }
      ]
      ```

### question-service

- **GET /question/allQuestions**
  - Get all questions.
  - Input: None

- **GET /question/filterByCategory/{category}**
  - Get questions filtered by category.
  - Input: Path variable `category` (string)

- **POST /question/addQuestion**
  - Add a new question.
  - Input (JSON body):
    ```json
    {
      "id": integer,
      "questionTitle": "string",
      "option1": "string",
      "option2": "string",
      "option3": "string",
      "option4": "string",
      "rightAnswer": "string",
      "difficultyLevel": "string",
      "category": "string"
    }
    ```

- **GET /question/generate?categoryName=string&numQ=integer**
  - Generate a list of question IDs for a quiz.
  - Input: Query parameters `categoryName` (string), `numQ` (integer)

- **POST /question/getQuestions**
  - Get question details for a list of question IDs.
  - Input (JSON body): List of question IDs, e.g. `[1, 2, 3]`

- **POST /question/getScore**
  - Calculate score based on submitted answers.
  - Input (JSON body): List of responses
    ```json
    [
      {
        "id": integer,
        "response": "string"
      }
    ]
    ```

## Configuration

Each service has its own `application.properties` file for configuration. Update ports and service names as needed.

## Features
- Microservices architecture
- Service discovery with Eureka
- Inter-service communication with OpenFeign
- API Gateway for routing

## Contact
For questions, contact the project maintainer.
