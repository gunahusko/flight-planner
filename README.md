# Flight planner ✈

Application which can store flights between different airports and allows you to search them.

---

Flight planner REST API project is made by:
 - Java 11
 - Spring Boot 2.6.6
   - Spring Web (Build web, including RESTful)
   - Spring Boot Security
   - Spring Validation
   - Spring Data JPA
   - Spring Boot Test
   - Mockito (Integration test)
 - Gradle library
 - Liquibase - core

---

## Spring Security

To access administrator rights:
- User name: codelex-admin
- Password: Password123

---

## Endpoints

- /admin-api/flights [PUT]
- /admin-api/flights/{id} [GET]
- /admin-api/flights/{id} [DELETE]
- /api/airports [GET]
- /api/flights/search [POST]
- /api/flights/{id} [GET]
- /testing-api/clear [POST]

---

## Databases
- in-memory (project inner database)
- H2 (lightweight Java database)
- PostrgeSQL (object-relational database)

## Change Database

- Application is made so, you can change database.
- Do it in >> flight-planner / src / main / resources / application.properties
- Change:
  - For H2:
    - flight-planner.store-type=database
    - flight-planner.db-typee=H2
  - For PostrgeSQL:
    - flight-planner.store-type=database
    - flight-planner.db-typee=postgres
  - For project inner database:
     - flight-planner.store-type=in-memory

- In flight-planner / src / main / java / configuration / DatasourceConfiguration:
```java
@Configuration
public class DatasourceConfiguration {
    
    @Bean
    @ConditionalOnExpression("'${flight-planner.store-type}'=='database' and '${flight-planner.db-typee}'=='postgres'")
    public DataSource getPostgresDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql:<db-info>")
                .username("codelex")
                .password("codelex")
                .build();
    }
}
```
- Change username, password, and enter info about Database in <db-info> place.

---

## Run project

- Download Git Bash: https://git-scm.com/downloads (you can use also PowerShell terminal)
- Download Flight planner project: https://github.com/gunahusko/flight-planner
- Go inside folder, right click on mouse >> choose Git Bash Here >> terminal will pop-up
- In terminal write: 
```bash
./gradlew bootRun
```
- After hit Enter project will be running
- Ctrl+C to stop the project.

---

## Test API

- Download test project: https://github.com/codelex-io/flight-planner
- Run the same as Flight planner API (Flight planner API project must be running)

---

## Author

Guna Husko