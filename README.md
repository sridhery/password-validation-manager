## coding-assignment
 
 ### PROBLEM STATEMENT
 Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice).  The service is meant to check a text string for compliance to any number of password validation rules.  The rules currently known are:
 
 * Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
 * Must be between 5 and 12 characters in length.
 * Must not contain any sequence of characters immediately followed by the same sequence.
 
 ### ASSUMPTIONS

* We continue to validate password with other rules when one of the rules is failed. 
* No need to consider order of rules
* Internationalization is not considered for this application
* Problem statements ambiguous in some areas like "Must be between 5 and 12 characters in length." I am not sure here whether to 5 and 12 or included or not. 
 
 
 ### IMPLEMENTATION
 * using Spring IOC container for Dependency Injection 
 * using Maven as a build tool.<br>
   `mvn clean install` will generate a jar file in application target folder.
   `mvn clean test` will run all unit tests and Jacoco output report will be generated in target directory under jacoco-ut folder 
 * `ValidationRule` is the interface we need to implement to add a new validation rule.
 * `PasswordValidationService` is the service where we inject all the validation rules.
 * All the validation rules are driven from a validation-rules.properties.
 * validation-rules.properties used for configuring the Rule Regex, Rule Name, Rule Message, Rule Enable.
 * Rules can be and enabled disabled using the respective properties.
 * To meet the first problem statement 3 Individual Regex combinations were used so disable all or enable all of them.
 * Change the respective regex values in the validation-rules.properties for any future validation changes.
 
  
 
 
 
 