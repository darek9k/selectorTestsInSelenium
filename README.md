
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
## ByLinkNameTest.java: Contains the test cases and supporting methods.

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

## ByTagTest.java

Source code for the example is located in /src/main/java/webdriver/ByTagTest.java and /src/main/java/webdriver/BMICalculationTest.java.

1. Launch the web browser and navigate to a web page:
   ```java
   WebDriverManager.chromedriver().setup();
   driver = new ChromeDriver();
   driver.get("http://127.0.0.1:5500/");
   ```

2. Find elements by tag name and perform actions:
   ```java
   WebElement tag = driver.findElement(By.tagName(tagName));
   ```

3. Parameterized testing for BMI calculation and result verification:
   ```java
   @ParameterizedTest
   @CsvSource({ "50.3,190,13.93,NIEDOWAGA", "80,180,24.69,OK", "120,200,30.00,NADWAGA" })
   void givenRightData_whenCalculate_thenRightBmi(String weight, String height, String expectedBmi, String expectedBmiNote) {
       // ... Test logic ...
   }
   ```

4. Assertion of expected results using AssertJ library:
   ```java
   assertThat(bmi.getText()).isEqualTo(expectedBmi);
   assertThat(bmiNote.getText()).isEqualTo(expectedBmiNote);
   assertThat(errorMsg.getText()).isEmpty();
   ```

## Run the example of web UI automation testing using Java Selenium WebDriver

1. Clone this repository

2. Configure the project in your preferred Java IDE

3. Set up the project with appropriate dependencies such as JUnit, AssertJ, and Selenium WebDriver

4. Run the individual test classes or the entire test suite

## ByXPathSelectorTest.java

This repository contains a Java test class for demonstrating the usage of ByXPathSelector in Selenium WebDriver. The test class covers various scenarios of selecting elements using XPath selectors.

### Test Cases
The ByXPathSelectorTest class includes multiple test cases for selecting elements using different XPath expressions. The tests cover various scenarios such as selecting elements by ID, class name, attributes, tags, nesting, and more.

## Source Code Review

1. Example of using XPath to find an element by ID:

```java
findElementsByXPath("//*[@id='pierwszyDiv']");
```

2. Example of using XPath to find an element by class name:

```java
findElementsByXPath("//*[@class='px-4']");
```

3. Example of using XPath to find an element by attribute:

```java
findElementsByXPath("//*[starts-with(@name,'w')]");
```

4. Example of using XPath to find nested elements:

```java
findElementsByXPath("//div//p");
```


## ExplicitWaitingTest.java - Explicit Waiting Test

This test case validates the behavior of the BMI Calculator when given the right data.

1. It enters the weight and height.
2. Clicks on the calculate button.
3. Verifies the BMI and BMI note.

### Error Message Test

This test case validates the behavior of the BMI Calculator when given wrong data.

1. It enters the weight and height (if provided).
2. Clicks on the calculate button.
3. Verifies the error message.

## Source Code Review

Source code for the test cases is located in the /src/main/java/webdriver/ExplicitWaitingTest.java. The tests use JUnit 5 and Selenium WebDriver for automation.
```
private static WebElement findElementWithWaiting(String id) {
    System.out.println("Wyszkuje: " + id);
    long start = System.currentTimeMillis();
    WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    long end = System.currentTimeMillis();
    System.out.println("Trwało: " + (end - start));
    return webElement;
}
```

### Utility Methods

The test cases utilize the following utility methods for finding web elements:

1. `findElement(String id)`: Finds a web element by id with basic WebDriver find operation.
2. `findElementWithWaiting(String id)`: Finds a web element with explicit wait using WebDriverWait.


## FluentWaitingTest.java - Selenium Fluent Waiting Test

This repository contains an example of Selenium test using Fluent Waiting.

## Source Code Review
```
// Example 1: Setting up WebDriver using WebDriverManager
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
```
```
// Example 2: Using FluentWait to handle dynamic elements
FluentWait<WebDriver> wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(3))
        .pollingEvery(Duration.ofMillis(300))
        .ignoring(NotFoundException.class);
```
```
// Example 3: Entering weight and height data using Selenium
WebElement weightInput = driver.findElement(By.id("waga"));
weightInput.clear();
weightInput.sendKeys("80");

WebElement heightInput = driver.findElement(By.id("wzrost"));
heightInput.clear();
heightInput.sendKeys("180");

WebElement submitBtn = driver.findElement(By.id("submitBtn"));
submitBtn.click();
```
```
// Example 4: Verifying expected BMI and BMI note
WebElement bmi = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmi")));
WebElement bmiNote = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmiNote")));
WebElement errorMsg = driver.findElement(By.id("errorMsg"));

assertThat(bmi.getText()).isEqualTo("24.69");
assertThat(bmiNote.getText()).isEqualTo("OK");
assertThat(errorMsg.getText()).isEmpty();
```

#### Disclaimer:
- This is a sample test framework and intended for demonstration purposes only.
- Framework may not be actively maintained.
