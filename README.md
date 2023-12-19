
# Java Selenium Test Framework

## Overview
This repository contains a Selenium test framework for testing a BMI calculator web application.

## Framework Components

The framework includes the following components:
- **BmiTest.java**: Contains parameterized tests for verifying the functionality of the BMI calculator web application.

## Test Execution

To execute the tests, follow these steps:
1. Set up the required dependencies and environment for running Selenium tests.
2. Run the BmiTest.java file to execute the parameterized tests included in the framework.

## Test Scenarios

The BmiTest includes the following test scenarios:
1. **Verify BMI Calculation with Correct Data**: Test method to verify the correct calculation of BMI with valid input data.
   
```

@ParameterizedTest
@CsvSource({
    "50.3,190,13.93,NIEDOWAGA",
    "80,180,24.69,OK",
    "120,200,30.00,NADWAGA"
})
void givenRightData_whenCalculate_thenRightBmi(String weight, String height,
                                               String expectedBmi, String expectedBmiNote) {
    //GIVEN
    //1. wpisać wagę // enter weight
    WebElement weightInput = driver.findElement(By.id("waga"));
    weightInput.clear();
    weightInput.sendKeys(weight);

    //2. wpisać wzrost //enter height
    WebElement heightInput = driver.findElement(By.id("wzrost"));
    heightInput.clear();
    heightInput.sendKeys(height);

    //WHEN
    //3. wcisnąć oblicz // click on 'oblicz'
    WebElement submitBtn = driver.findElement(By.id("submitBtn"));
    submitBtn.click();

    //THEN
    //4. sprawdź BMI // check BMI
    //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
    //with error message
    assertThat(driver.getTitle()).isEqualTo("Kalkulator BMI");

    WebElement bmi = driver.findElement(By.id("bmi"));
    WebElement bmiNote = driver.findElement(By.id("bmiNote"));
    WebElement errorMsg = driver.findElement(By.id("errorMsg"));

    assertThat(bmi.getText()).isEqualTo(expectedBmi);
    assertThat(bmiNote.getText()).isEqualTo(expectedBmiNote);
    assertThat(errorMsg.getText()).isEmpty();
}
   ```

3. **Verify Error Messages with Incorrect Data**: Test method to verify the display of error messages with invalid input data.
```
@ParameterizedTest
@CsvSource({
    ",180",
    "1,180",
    "300,180",
    "80,180",
    "80,",
    "80,320",
    ",",
    ",20",
    ",320",
    "1,",
    "1,20",
    "1,320",
    "300,",
    "300,20",
    "300,320"
})
void givenWrongData_whenCalculate_thenErrorMessage(String weight, String height) {
    //GIVEN
    //1. pole waga pozostawić puste // Leave the weight field empty.
    WebElement weightInput = driver.findElement(By.id("waga"));
    weightInput.clear();
    if(weight!=null) {
        weightInput.sendKeys(weight);
    }
    //2. wpisać wzrost 188.5 //enter height 188.5
    WebElement heightInput = driver.findElement(By.id("wzrost"));
    heightInput.clear();
    if(height!=null) {
        heightInput.sendKeys(height);
    }
    //WHEN
    //3. wcisnąć oblicz // click on 'oblicz'
    WebElement submitBtn = driver.findElement(By.id("submitBtn"));
    submitBtn.click();

    //THEN
    //4. sprawdź BMI // check BMI
    //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
    //with error message

    assertThat(driver.getTitle()).isEqualTo("Kalkulator BMI");

    WebElement bmi = driver.findElement(By.id("bmi"));
    WebElement bmiNote = driver.findElement(By.id("bmiNote"));
    WebElement errorMsg = driver.findElement(By.id("errorMsg"));

    assertThat(bmi.getText()).isEmpty();
    assertThat(bmiNote.getText()).isEmpty();
    assertThat(errorMsg.getText()).isEqualTo("Niepoprawna waga lub wzrost");
}
```
## Dependencies
- WebDriverManager: Library for automating the management of webdrivers.
- JUnit Jupiter: Testing framework for writing and running repeatable tests.

## Run the Example
To run the example BMI tests, you can execute the BmiTest.java file and observe the test results.

---

## Source Code Review - ByClassNameTest.java

The main test class `ByClassNameTest` contains the following methods:
- `beforeAll()`: Initializes the WebDriver and navigates to the web application
- `afterAll()`: Closes the WebDriver after all tests
- `byClassNameTest()`: Test method to find elements by class name
- `givenRightData_whenCalculate_thenRightBmi()`: Parameterized test method to calculate BMI and verify the results
- Other helper methods for finding elements and printing tag information

## ByCssSelectorTest.java - Example Code

Here is an example of how to use CSS selectors to locate elements in a web page using Selenium WebDriver:

```java
package webdriver;

// Import statements

class ByCssSelectorTest {

    // WebDriver setup and teardown

    @BeforeAll
    static void beforeAll() {
        // Set up WebDriver
        // Navigate to the web page
    }

    @AfterAll
    static void afterAll() {
        // Quit WebDriver
    }

    // Various examples of locating elements using CSS selectors
    @Test
    void byCssSelector() {
        // Examples of locating elements using CSS selectors
    }

    @Test
    void autoFromDevToolsExample() {
        // Example of using a CSS selector copied from the browser's dev tools
    }

    @ParameterizedTest
    @CsvSource({
            // Test data for BMI calculation
    })
    void givenRightData_whenCalculate_thenRightBmi(String weight, String height,
                                                   String expectedBmi, String expectedBmiNote) {
        // Test BMI calculation with given data
    }
    
    // Additional methods for locating and interacting with elements

}
```
## `ByLinkNameTest.java`: Contains the test cases and supporting methods.

This is a sample readme for a Selenium WebDriver test project. Feel free to modify it based on your specific project requirements.

## Test Case 3: givenRightData_whenCalculate_thenRightBmi
This parameterized test verifies the BMI calculation functionality using different input data.

```java
@ParameterizedTest
@CsvSource({
    "50.3,190,13.93,NIEDOWAGA",
    "80,180,24.69,OK",
    "120,200,30.00,NADWAGA"
})
void givenRightData_whenCalculate_thenRightBmi(String weight, String height,
                                               String expectedBmi, String expectedBmiNote) {
    //GIVEN
    // ...

    //WHEN
    // ...

    //THEN
    // ...
}
```



#### Disclaimer:
- This is a sample test framework and intended for demonstration purposes only.
- Framework may not be actively maintained.
