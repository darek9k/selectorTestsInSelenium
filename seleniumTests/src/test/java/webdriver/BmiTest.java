package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class BmiTest {

    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/bmiFrontend/");
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenRightData_whenCalculate_thenRightBmi() {
        //GIVEN
        //1. wpisać wagę 80 // enter weight 80
        WebElement weightInput = driver.findElement(By.id("waga"));
        weightInput.sendKeys("80");

        //2. wpisać wzrost 188.5 //enter height 188.5
        WebElement heightInput = driver.findElement(By.id("wzrost"));
        heightInput.sendKeys("188.5");

        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        submitBtn.click();

        //THEN
        //4. sprawdź BMI // check BMI
        //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
        //with error message
        Assertions.assertEquals("Kalkulator BMI", driver.getTitle(), "Wrong website title");

        WebElement bmi = driver.findElement(By.id("bmi"));
        WebElement bmiNote = driver.findElement(By.id("bmiNote"));
        WebElement errorMsg = driver.findElement(By.id("errorMsg"));

        Assertions.assertEquals("22.51",bmi.getText());
        Assertions.assertEquals("OK",bmiNote.getText());
        Assertions.assertTrue(errorMsg.getText().isEmpty());
    }

    @Test
    void givenWrongData_whenCalculate_thenErrorMessage() {

        //GIVEN
        //1. pole waga pozostawić puste // Leave the weight field empty.
        WebElement weightInput = driver.findElement(By.id("waga"));
        weightInput.sendKeys("");

        //2. wpisać wzrost 188.5 //enter height 188.5
        WebElement heightInput = driver.findElement(By.id("wzrost"));
        heightInput.sendKeys("188.5");

        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        submitBtn.click();

        //THEN
        //4. sprawdź BMI // check BMI
        //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
        //with error message
        Assertions.assertEquals("Kalkulator BMI", driver.getTitle(), "Wrong website title");

        WebElement bmi = driver.findElement(By.id("bmi"));
        WebElement bmiNote = driver.findElement(By.id("bmiNote"));
        WebElement errorMsg = driver.findElement(By.id("errorMsg"));

        Assertions.assertEquals("",bmi.getText(),"BMI field is not empty");
        Assertions.assertEquals("",bmiNote.getText());
        Assertions.assertEquals("Niepoprawna waga lub wzrost",errorMsg.getText());
    }
}