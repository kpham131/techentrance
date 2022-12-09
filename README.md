To run the project locally:

1. Run the "database.sql" script on your mysql server in a schema that you want
2. Go to application.properties file located at src/main/resources
3. Update the last 3 rows with the appropriate values
   1. spring.datasource.url=jdbc:mysql://localhost:3306/<schema_name>
   2. spring.datasource.username=<username for your mysql server>
   3. spring.datasource.password=<password for your mysql server>
4. Open the project in IntelliJ and run the main class inside src/java/com/techentrance/techentrance/TechentranceApplication.java