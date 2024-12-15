# Binary Search Tree Application

This project is a Spring Boot application that allows users to create and visualize binary search trees. The application provides a web interface for entering numbers, creating trees, and viewing previously created trees.

## Features

- Create a binary search tree from a list of numbers
- Visualize the created binary search tree
- View previously created trees
- REST API endpoints for creating trees and fetching entries

## Technologies Used

- Java
- Spring Boot
- Maven
- MySQL
- Thymeleaf
- JavaScript

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/binary-search-tree-app.git
    cd binary-search-tree-app
    ```

2. Configure the database:
    - Create a MySQL database named `binary`.
    - Update the database configuration in `src/main/resources/application.properties`:
        ```ini
        spring.datasource.url=jdbc:mysql://localhost:3306/binary
        spring.datasource.username=root
        spring.datasource.password=2580
        spring.jpa.hibernate.ddl-auto=update
        server.port=8080
        ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

5. Open your browser and navigate to `http://localhost:8080/`.

## API Endpoints

- `POST /api/tree`: Create a binary search tree from a list of numbers.
- `GET /api/entries`: Get all previously created binary search trees.
- `GET /trees`: Get all previously created binary search trees.

## Usage

1. Enter a list of numbers separated by commas on the input page.
2. Submit the form to create and visualize the binary search tree.
3. View the tree structure on the visualization page.
4. Access the list of previously created trees from the "Previous Trees" page.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
