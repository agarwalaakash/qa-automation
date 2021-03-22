# qa-automation

Quick Tips
1. main->java - Contains Page Object modal structure with a reusable files folder for common functions
2. main->resources - Contians all locators and common properties
3. test->java - Contains all Steps linking feature file to the page objects and the Test runner file
4. test->resources - Contains all feature files[ any csv, excel etc data file would be placed in this folder]
5. reusable.properties file determines the browser and url to be launched
6. drivers folder contains all drivers for the browsers
7. Results will be generated in target folder under cucumber-reports folder