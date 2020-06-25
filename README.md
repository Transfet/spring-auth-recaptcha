# spring-auth-recaptcha

### Project description
Simple spring boot application that has a login form which supports Google reCAPTCHA
after a few login attempts.
The project shows us how can we secure our views/pages using spring boot.

### Technologies used
Technologies that I used in this project are the following:
* Java 11
* Gradle 6.5
* H2 database (embedded)
* Spring boot 2.3.1
    * Web
    * Data
    * security
    * thymeleaf
* reCAPTCHA (Google integration)
* lombok

### How to start the project

##### Prerequisites:
To use the application java 11 has to be installed.
If the gradle 6.5 is not installed on the computer, 
gradlew (linux machine) or gradlew.bat (windows machine) can be used.

notes:
do not forget to check the gradlew's permissions. If it has no executable permission type the following:
* chmod +x gradlew

##### Run the application:
1. Open your command prompt/terminal
2. Go to the root directory
3. gradlew test (optional if you would like to run the tests)
4. gradle bootRun

##### Test the application
By default, the application runs on the 80 port:
http://localhost/

##### Available routes
*   /
*   /login
*   /admin
*   /editor
*   /profile

##### Access to the embedded database
* http://localhost/h2-console
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* Password: (leave it empty)


##### Stop the application:
1. Press control+c on the command prompt/terminal