Prerequisites
Ensure you have the following installed on your machine:

Java Development Kit (JDK) 11 or higher
Maven (for project build and dependency management)
IDE (e.g., IntelliJ IDEA, Eclipse) - Optional but recommended
Setup
Clone the Repository

bash
Copy code
git clone https://github.com/your-username/your-repository.git
cd your-repository
Install Dependencies

Ensure you have Maven installed. Run the following command to download all necessary dependencies:

bash
Copy code
mvn install
Running Tests
Via Maven Command Line

To run the Cucumber tests, use the following Maven command:

bash
Copy code
mvn test
This command will execute all the scenarios defined in the .feature files located in the src/test/resources/features directory.

Via IDE

If you are using an IDE, you can run the tests directly from the IDE. Ensure that the CucumberRunner class is set as a JUnit test runner.
