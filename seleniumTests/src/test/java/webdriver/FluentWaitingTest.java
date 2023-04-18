package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class FluentWaitingTest {

    static WebDriver driver;
    static FluentWait<WebDriver> wait;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class);
        driver.get("http://127.0.0.1:5500/");
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

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

        //WebElement bmi = driver.findElement(By.id("bmi"));
        WebElement bmi = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmi")));

        //WebElement bmiNote = driver.findElement(By.id("bmiNote"));
        WebElement bmiNote = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmiNote")));

        WebElement errorMsg = driver.findElement(By.id("errorMsg"));

        assertThat(bmi.getText()).isEqualTo(expectedBmi);
        assertThat(bmiNote.getText()).isEqualTo(expectedBmiNote);
        assertThat(errorMsg.getText()).isEmpty();
    }

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
        if (weight != null) {
            weightInput.sendKeys(weight);
        }
        //2. wpisać wzrost 188.5 //enter height 188.5
        WebElement heightInput = driver.findElement(By.id("wzrost"));
        heightInput.clear();
        if (height != null) {
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

        //WebElement bmi = driver.findElement(By.id("bmi"));
        WebElement bmi = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmi")));
        //WebElement bmiNote = driver.findElement(By.id("bmiNote"));
        WebElement bmiNote = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bmiNote")));
        WebElement errorMsg = driver.findElement(By.id("errorMsg"));

        assertThat(bmi.getText()).isEmpty();
        assertThat(bmiNote.getText()).isEmpty();
        assertThat(errorMsg.getText()).isEqualTo("Niepoprawna waga lub wzrost");
    }
}