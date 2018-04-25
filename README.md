## coding-assignment
 
 ### PROBLEM STATEMENT
 Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice).  The service is meant to check a text string for compliance to any number of password validation rules.  The rules currently known are:
 
 * Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
 * Must be between 5 and 12 characters in length.
 * Must not contain any sequence of characters immediately followed by the same sequence.
 
 ### ASSUMPTIONS

* We continue to validate password with other rules when one of the rules is failed. 
* No need to consider order of rules
* Internationalization is not required for this application
 
 
 ### IMPLEMENTATION
 * using Spring IOC container for Dependency Injection 
 * using Maven as a build tool.<br>
   `mvn clean install` will generate a jar file in application target folder.
   `mvn clean test` will run all unit tests and Jacoco output report will be generated in target directory under jacoco-ut folder 
 * `ValidationRule` is the interface we need to implement to add a new validation rule.
 * `PasswordValidationService` is the service where we inject all the validation rules.
 * All the validation rules are driven from a validation-rules.properties file which can be used to configure the rule name and rule regex.
 