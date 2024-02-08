# beymenWebAutomation
  -test to run
  
  mvn test -Dpage=https://www.beymen.com/


# Table of Contents
* JUnit
* Maven
* Log4j2
* Java
* IntelliJ

# Structure of Project
- logs
  - app.log
- data
  - testData.xlsx
- testresultdata
- src
  - main
    - java
     - base
       - BaseTest.java
     - baseactions
       - BaseActions.java
     - pageobjects
       - CardPageObjects.java
       - MainPageObjects.java
       - ProductDetailPageObjects.java
       - SearchResultPageObjects.java
     - pages
       - CardPage.java
       - MainPage.java
       - ProductDetailPage.java
       - SearchResultPage.java
     - stringvariables
       - CardPageStrings.java
       - CommonStrings.java
       - MainPageStrings.java
       - ProductDetailPageStrings.java
       - SearchResultPageStrings.java
  - resources
   - log4j2.xml
  - test
   - java
     - tests
       - BeymenTests.java
