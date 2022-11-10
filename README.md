# Introduction

 This is a Rest API test solution for endpoints available in https://testapi.io/api/ottplatform/. The published APIs represent a blog application where users can get the existing music tracks.


# Tools / libraries used
- Java
- Rest Assured
- Cucumber
- TestNG
- Maven
- IDE(e.g. IntelliJ)

# Run Tests

Tests can be run using Command line as well as IDE (e.g. InelliJ)

## Using Command line
- Clone / Download the project into your local
- Open the Command prompt and navigate to project location
- Execute the following Maven command's
  - mvn clean : To clean the maven repo
  - mvn install : To install the maven requirements
  - mvn test : To execute the test scenarios - The default cucumber report url will be generated when the test run complete.

#### To run scenario using a tag
- Navigate to the project location in CLI
- Run the following command mvn clean -Dcucumber.filter.tags="@tag" test

#### Additional configurations
- Additional command line parameters can be passed for switching the application environment.
- e.g. mvn clean test -Denvironment="test"

## Using IDE 
#### To run all feature files in a directory﻿
- In the Project Tool Window, right-click the directory where the feature files are stored.
- On the context menu of the directory, choose Run all features in \<directory name\>.

#### To run a feature﻿
- In the Project Tool Window, right-click the desired feature file, or open it in the editor.
- On the context menu of the feature file, choose Run Feature \<name\>.

#### To run a scenario﻿
- In the Project Tool Window, right-click the desired feature file, or open it in the editor.
- On the context menu of the desired scenario, point to Run, and then choose Run Scenario \<name\>.

# View HTML Report
- The default cucumber report url will be generated when the test run complete.


   