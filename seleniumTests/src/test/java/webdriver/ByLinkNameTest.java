package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ByLinkNameTest {

    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/");
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @Test
    void byLinkTextTest() {
        findByLinkText("Kliknij");
        findByLinkText("Kliknij A3");
    }

    @Test
    void byPartialLinkTextTest() {
        findByPartialLinkText("Kliknij");
        findByPartialLinkText("Kliknij A3");
        findByPartialLinkText("Oblicz");
        findByPartialLinkText("Oblicz BMI");
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

        WebElement div = driver.findElement(By.className("px-4"));

        List<WebElement> inputs = div.findElements(By.className("form-control"));
        assertThat(inputs).hasSize(2);

        //1. wpisać wagę // enter weight
        WebElement weightInput = inputs.get(0);
        weightInput.clear();
        weightInput.sendKeys(weight);

        //2. wpisać wzrost //enter height
        WebElement heightInput = inputs.get(1);
        heightInput.clear();
        heightInput.sendKeys(height);

        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement button = div.findElement(By.partialLinkText("Kliknij"));
        button.click();

        //THEN
        //4. sprawdź BMI // check BMI
        //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
        //with error message
        List<WebElement> h1Tags = driver.findElements(By.className("display-5"));
        assertThat(h1Tags).hasSize(4);

        WebElement bmi = h1Tags.get(1);
        WebElement bmiNote = h1Tags.get(2);
        WebElement errorMsg = h1Tags.get(3);

        assertThat(bmi.getText()).isEqualTo(expectedBmi);
        assertThat(bmiNote.getText()).isEqualTo(expectedBmiNote);
        assertThat(errorMsg.getText()).isEmpty();
    }

    private static void listTags(List<WebElement> tags) {
        System.out.println("Liczba tagów: " + tags.size());
        tags.forEach(tag -> System.out.println(tag.getTagName() +
                ", id: " + tag.getAttribute("id") +
                ": " + tag.getText())
        );
    }

    private static void findByLinkText(String linkText) {
        WebElement tag = driver.findElement(By.linkText(linkText));
        tagInfo(tag);
    }

    private static void findByPartialLinkText(String partialLinkText) {
        WebElement tag = driver.findElement(By.partialLinkText(partialLinkText));
        tagInfo(tag);
    }

    private static void tagInfo(WebElement tag) {
        System.out.println(tag.getTagName() + ", id: " + tag.getAttribute("id") + ", text: " + tag.getText());
    }
}