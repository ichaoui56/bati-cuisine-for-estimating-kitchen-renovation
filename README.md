# Bati-Cuisine

## Project Overview

**Bati-Cuisine** is a Java application designed for estimating kitchen renovation and construction costs. It manages client information, generates quotes, and calculates total project costs based on materials and labor. The application is organized into various functional modules to enhance usability and maintainability.

### Features

- Client management
- Project management
- Component management
- Materials and labor cost estimation
- Generation of custom quotes

## Technologies Used

- Java 8
- PostgreSQL
- JDBC
- Java Time API
- Streams
- Optionals

## Design Patterns

- Singleton
- Repository
- Service Layer

## Project Structure

```
Bati-Cuisine
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── BatiCuisine
│   │   │   │   │   ├── config
│   │   │   │   │   │   ├── DatabaseConnection.java
│   │   │   │   │   ├── repository
│   │   │   │   │   ├── ├──Implumentations
│   │   │   │   │   │   │   ├── ClientRepositoryImpl.java
│   │   │   │   │   │   │   ├── MainOeuvreRepositoryImpl.java
│   │   │   │   │   │   │   ├── MaterialRepositoryImpl.java
│   │   │   │   │   │   │   ├── ProjetRepositoryImpl.java
│   │   │   │   │   │   │   ├── DevisRepositoryImpl.java
│   │   │   │   │   ├── ├──Interfaces
│   │   │   │   │   │   │   ├── ClientRepository.java
│   │   │   │   │   │   │   ├── MainOeuvreRepository.java
│   │   │   │   │   │   │   ├── MaterialRepository.java
│   │   │   │   │   │   │   ├── ProjetRepository.java
│   │   │   │   │   │   │   ├── DevisRepository.java
│   │   │   │   │   ├── service
│   │   │   │   │   ├── ├──Implumentations
│   │   │   │   │   │   │   ├── ClientServiceImpl.java
│   │   │   │   │   │   │   ├── MainOeuvreServiceImpl.java
│   │   │   │   │   │   │   ├── MaterialServiceImpl.java
│   │   │   │   │   │   │   ├── ProjetServiceImpl.java
│   │   │   │   │   │   │   ├── DevisServiceImpl.java
│   │   │   │   │   ├── ├──Interfaces
│   │   │   │   │   │   │   ├── ClientService.java
│   │   │   │   │   │   │   ├── MainOeuvreService.java
│   │   │   │   │   │   │   ├── MaterialService.java
│   │   │   │   │   │   │   ├── ProjetService.java
│   │   │   │   │   │   │   ├── DevisService.java
│   │   │   │   │   ├── consoleUI
│   │   │   │   │   │   ├── ClientUI.java
│   │   │   │   │   │   ├── MainOeuvreUI.java
│   │   │   │   │   │   ├── MaterialUI.java
│   │   │   │   │   │   ├── ProjetUI.java
│   │   │   │   │   │   ├── DevisUI.java
│   │   │   │   │   ├── model
│   │   │   │   │   ├── ├──entities
│   │   │   │   │   │   │   ├── Client.java
│   │   │   │   │   │   │   ├── MainOeuvre.java
│   │   │   │   │   │   │   ├── Material.java
│   │   │   │   │   │   │   ├── Projet.java
│   │   │   │   │   │   │   ├── Devis.java
│   │   │   │   │   │   │   ├── Composant.java
│   │   │   │   │   ├── ├──enums
│   │   │   │   │   │   │   ├── EtatProjet.java
│   │   │   │   │   ├── utils
│   │   │   │   │   │   ├── DevisCalculation.java
│   │   │   │   │   │   ├── Validator.java
│   │   │   │   │   └── Main.java
│   │   │   │   └── resources
│   │   │   └── ...
│   └── test
│       └── java
│           ├── ...
└── README.md
```

## How to Run the Project

1. Ensure you have Java 8 and PostgreSQL installed.
2. Clone the repository:  
   ```bash
   git clone https://github.com/username/Bati-Cuisine.git
   ```
3. Navigate to the project directory:  
   ```bash
   cd Bati-Cuisine
   ```
4. Update the database connection details in the configuration file if needed.
5. Compile and run the application.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
