# Project Title
Compare Weather Information from UI and API
## Overview
The Main purpose of this project is to search for the required Product from the Amazon website and get the corresponding information in it.
The search has been done based on Filtering the category option and search for the result on Search box and then choosing any of the List from the search Result
## Pre Requisities
Following are the Pre-Requisities which needs to be installed in the Environment
- Eclipse - Eclipse is an IDE for development Environment - [click here for download](https://www.eclipse.org/downloads/)
- Java JDK - Java JDK-15 is for configuring and accessing Java class - [click here for download](http://www.oracle.com/technetwork/java/javase/downloads)
- Maven - Maven is a Build tool which can be download from eclipse marketplace
- ChromeDriver - Chromedriver is needed to work with Chrome browser for testing - [click here to download](https://sites.google.com/a/chromium.org/chromedriver/)
- Dependencies:
  - Selenium Jar file <version> 3.141.59 </version>
  - TestNG Jar file <version> 7.3.0 </version>
  - JSON-Simple <version> 1.1.1 </version>
  - Rest Assured <version> 4.3.2 </version>
  - Extend Report Testng <version> 2.14.2 </version>
  
- Plugins:
  - Maven Surefire
## Design Description
This project has been designed with the help of BDD framework in Cucumber, The Design language is carried out through Java. Here are the list of items that has been used in this project
  - Framework used - BDD with Cucumber and junit
  - Build Tool - Maven
Here the implementation is done for two test scripts with same scenario and different Test data.
  - 1st Test data with valid Item number - Same scenario
  - 2nd Test data with invalid Item number - Same scenario
For implementing this feature, i included Scenario Outline, which is used to execute multiple test for same scenario with different input parameter.
## Code level Description
The Input parameter for this Project is carried out from Data table in Feature file and the Output has been send to Microsoft Excel document
  - Framework Components:
    - Packages: (src/test/java)
      - com.assignment.amazon - Main page
        - TestRunner.java - Main class file
      - pageObjects - Used Page Object Model approach for identifying and storing in Object repository
        - Homepage.java - This file has Object of Search category option and Search input field
        - ResultPage.java - Thie class file has the Objects for most of information about the particular Product choosen
      - stepDefinitions:
        - Test_Script.java - This class file has the actual methods of the given scenarios
      - utils: Utilities used for this project
        - Constants.java - This class file has the configuration values like file path and file names from the system.
        - ExcelData.java - This class file has an ability to access the Excel file from host machine(both read and write operation).
      - resources: (This is not belongs to packages)
        - Chromedriver.exe - This Application helps to interact with the Chrome browser for testing
        - testdata.xlsx - This Application helps to store the Output of this project after execution.
   - Feature: (Main cucumber file)
      - product_search.feature: This file is used to define the actual scenario of which we are going to test, the phrase will be Natural and Non-programmer readable format
## Execution Description
After setting up the Environment, The execution has to be done with the help of Build Tool : Maven.
The user has to execute by using the pom.xml file under the Project directory.
Right click on pom.xml file and choose Run as -> Maven Test or go to command prompt and navigate to project directory and enter mvn test.
## Reporting
After the successful execution, the report will be generated in Interative and more readable format.
In Cucumber we have default reporting pluings, here i implemented the Report type as "Pretty" along with HTML format.
##### Cucumber JUnit Report
![Extended TestNG report](https://github.com/ps442277/NewAssignment/blob/master/Sample%20Report/Junit%20Report.PNG)
## Author
Praveen Selvaraj - [About me](https://github.com/ps442277)
