
# Sinai Tech Challenge - Dennys Barros

Project automation of the Carbon Footprint Calculator: https://www3.epa.gov/carbon-footprint-calculator/


## Installation

To install it, you will have to clone this repo to your own workspace and import it into a IDE (such as IntelliJ). 
After that, click on pom.xml > Maven > Reload Project, so the dependencies are installed.

## Execution
In the IDE, right-click in the tests folder and select "Run Tests in tests". Since the WebdriverManager
was used, there is no need to download the browser drivers. The only thing that needs to be changed is to change
in the "resources/properties.json" file the parameter "browser" to "chrome", "safari", "firefox" or "iexplorer". The 
WebdriverManager will download the respective browser and will run the test against it.

## Classes:

### calculations

- _src/calculations/Calculations.java_ - class created to perform the math of the calculator. Data was based in the spreasheet available in the Calculator website.

### pages

- _src/pages/CalculatorHomePage.java_ - class created to store the web elements of the Home Page of the system and group the methods of the main features of this section.
- _src/pages/HomeEnergy.java_ - class created to store the web elements of the Home Energy section of the system and group the methods of the main features of this section.
- _src/pages/ReportPage.java_ - class created to store the web elements of the Report Page of the system and group the methods of the main features of this section.
- _src/pages/SideTotalBoxPage.java_ - class created to store the web elements of the Side box section of the system and group the methods of the main features of this section.
- _src/pages/TransportationPage.java_ - class created to store the web elements of the Transportation section of the system and group the methods of the main features of this section.
- _src/pages/WastePage.java_ - class created to store the web elements of the Waste section of the system and group the methods of the main features of this section.

### tests 

- _src/tests/HomeEnergyTest.java_ - Tests related to the Home Energy page
- _src/tests/ReportTest.java_ - Tests related to the Report page
- _src/tests/TransportationTest.java_  - Tests related to the Transportation page
- _src/tests/WasteTest.java_ - Tests related to the Waste page

### utils

- _src/utils/BasePage_ - Basic methods to interact with web elements.
- _src/utils/DriverFactory.java_ - basic methods to initialize the webdriver
- _src/utils/ResourcesParser.java_ - methods to manipulate the json resource files
- _src/utils/Utils.java_ - basic utils methods

### resources

- _resources/emissions_by_zipcode.json - property file that stores emission factor values by zipcode
- _resources/resources.json_ - property file that stores carbon emissions values and test properties

## Test Cases 
- The test cases can be found [here](https://docs.google.com/spreadsheets/d/17pnHhwhbbM2kq01n1POZzjgM3yOWqjuWFyJe9WyeZ6s/edit?usp=sharing). 

## Bugs found
- The bug report can be found [here](https://docs.google.com/document/d/1oXfwy7cSgUdiZ8J0ppnNMWgexBjYajRi3CFYmr5fYI8/edit?usp=sharing).

## Approach followed for this challenge
- The document created to describe the approach and decisions can be found [here](https://docs.google.com/document/d/1RlIXygJuHZvUcXc0ZlT9Lvd9DzeoMZE9mpIuTdI5A0U/edit?usp=sharing).

