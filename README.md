# Project Title
Compare Weather Information from UI and API
## Overview
The Main purpose of this project is to Compare the Weather information from both Web UI using NDTV Official website and NDTV API and generate the result.
## Pre Requisities
Following are the Pre-Requisities which needs to be installed in the Environment
- Eclipse - Eclipse is an IDE for development Environment - [click here for download](https://www.eclipse.org/downloads/)
- Java JDK - Java JDK-15 is for configuring and accessing Java class - [click here for download](http://www.oracle.com/technetwork/java/javase/downloads)
- Maven - Maven is a Build tool which can be download from eclipse marketplace
- TestNG - TestNG is a Framework used to build the Project
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
This project has been designed with the help of TestNG framework, The Design language is carried out through Java. Here are the list of items that has been used in this project
  - Framework used - TestNG
  - Build Tool - Maven
Here the implementation is done for two phase(UI and API) with same Test class and different Test data.
  - For Web UI - Using Java lauguage with proper re-usable methods and reporting
  - For API - Using Java lauguage in BDD style to automate the API response in http protocol
  - JSON File - using JSON file as a Datasheet to read and write the inputs and parse into class file
## Code level Description
The Input and Output parameter for this Project is carried out from JSON file.
  - Framework Components:
    - Packages: (src/test/java)
      - automationFramework.Test01 - Main page
        - searchq() - Main method for Automating Web UI
        - build_api() - Main method for Automating API
      - Reusables - Using both Modular and Functional reuse class for iterating the repeating methods and fields
        - ModularReuse.java - This class has Module-wise methods to reuse and automate the fields in Webpage
        - RestReuse.java - Thie class file has the methods which will help to do Mathematical operations
        - FuncReuse.java - This class file has field level reuse functions (click_event, Page_load) which helps to reuse in ModularReuse class
        - JSONParsing.java - This class file has methods to read and write the inputs as JSON parameters using JSONObject and JSONParser class.
      - utils: Utilities used for this project
        - Constants.java - This class file has the configuration values like file path and file names from the system.
        - Testdata.json - This JSON file has the inputs to use for our project, This is the actual Datasheet.
      - resources: (This is not belongs to packages)
        - Chromedriver.exe - This Application helps to interact with the Chrome browser for testing
## Execution Description
After setting up the Environment, The execution can to be done either with the Build Tool-Maven or directly invoking the Test Suite.
The user has to execute either by using the pom.xml file under the Project directory or by using Testng.xml file under the Project directory
Right click on Testng.xml file and choose Run as -> Testsuite.
Right click on pom.xml file and choose Run as -> Maven Test or go to command prompt and navigate to project directory and enter mvn test.
## Reporting
After the successful execution, the report will be generated in Interative and more readable format as here we using Extend Report which helps generate the Report by giving Screenshot for each step for clear understanding.
##### TestNG Report
![TestNG Default report](https://github.com/ps442277/TestAutomation/blob/master/test-output/Suite/Test.html)
![TestNG Extend report](https://github.com/ps442277/TestAutomation/blob/master/src/test/java/Utility/ExtentReportResults.html)
## Author
Praveen Selvaraj - [About me](https://github.com/ps442277)
