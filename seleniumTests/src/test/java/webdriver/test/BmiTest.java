package webdriver.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webdriver.page.BmiPage;

import static org.assertj.core.api.Assertions.assertThat;

class BmiTest {

    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

    @ParameterizedTest
    @CsvSource({
            "50.3,190,13.93,NIEDOWAGA",
            "80,180,24.69,OK",
            "120,200,30.00,NADWAGA"
    })
    void givenRightData_whenCalculate_thenRightBmi(String weight, String height,
                                                   String expectedBmi, String expectedBmiNote) {
        //1. Przejdź na stronę / Go to website
        driver.get("http://127.0.0.1:5500/");

        //2. Policz BMI // Calculate BMI
        BmiPage bmiPage = new BmiPage(driver);
        bmiPage.calculateBmi(weight, height);

        //3. Asercje / Assertions
        assertThat(bmiPage.getBmiText()).isEqualTo(expectedBmi);
        assertThat(bmiPage.getBmiNoteText()).isEqualTo(expectedBmiNote);
        assertThat(bmiPage.getErrorMsgText()).isEmpty();
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

        driver.get("http://127.0.0.1:5500/index.html");

        BmiPage bmiPage = new BmiPage(driver);
        bmiPage.calculateBmi(weight, height);

        assertThat(bmiPage.getBmiText()).isEmpty();
        assertThat(bmiPage.getBmiNoteText()).isEmpty();
        assertThat(bmiPage.getErrorMsgText()).isEqualTo("Niepoprawna waga lub wzrost");
    }
}