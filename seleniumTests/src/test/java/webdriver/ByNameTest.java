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

class ByNameTest {

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
    void byNameTest() {
        WebElement myform = driver.findElement(By.id("myForm"));
        WebElement waga = myform.findElement(By.name("waga"));
        tagInfo(waga);

        WebElement wzrost = myform.findElement(By.name("wzrost"));
        tagInfo(wzrost);

        findByName("waga");
        findByName("wzrost");
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

        List<WebElement> forms = driver.findElements(By.tagName("form"));
        assertThat(forms).hasSize(2);

        WebElement form = forms.get(1);
        //1. wpisać wagę // enter weight
        WebElement weightInput = form.findElement(By.name("waga"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        //2. wpisać wzrost //enter height
        WebElement heightInput = form.findElement(By.name("wzrost"));
        heightInput.clear();
        heightInput.sendKeys(height);

        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement submitBtn = driver.findElement(By.tagName("button"));
        submitBtn.click();

        //THEN
        //4. sprawdź BMI // check BMI
        //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
        //with error message
        List<WebElement> h1Tags = driver.findElements(By.tagName("h1"));
        assertThat(h1Tags).hasSize(5);

        WebElement bmi = h1Tags.get(2);
        WebElement bmiNote = h1Tags.get(3);
        WebElement errorMsg = h1Tags.get(4);

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

    private static void findByName(String name) {
        WebElement tag = driver.findElement(By.name(name));
        tagInfo(tag);
    }

    private static void tagInfo(WebElement tag) {
        System.out.println(tag.getTagName() + ", id: " + tag.getAttribute("id") + ", text: " + tag.getText());
    }
}