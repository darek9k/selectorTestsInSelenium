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

class ByCssSelectorTest {

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
    void byCssSelector() {
        //po id - #id
        //By.id
        findElementsByCssSelector("#pierwszyDiv");

        // po nazwie klasy // by class name
        findElementsByCssSelector(".px-4");
        findElementsByCssSelector(".btn");

        //tylko obie pasujące klasy, kolejnośc bez znaczenia // //only both matching classes, order doesn't matter.
        findElementsByCssSelector(".btn.btn-primary");

        //po atrybucie //by attribute
        findElementsByCssSelector("[id]");
        findElementsByCssSelector("[name=wzrost]");

        //elementy ktore posiadaja atrybut name
        // a ich wartosc w atrybucie zaczyna sie na 'w'
        // value on attribute starts with 'w'
        findElementsByCssSelector("[name^=w]");

        //elementy ktore posiadaja atrybut name
        // a ich wartosc konczy się na 'st'
        // ends with 'st'
        findElementsByCssSelector("[name$=st");

        //po tagach // by tags
        findElementsByCssSelector("div");
        findElementsByCssSelector("h1.visually-hidden");
        findElementsByCssSelector("h1[class^=d]");

        //wiele tagów // multi tags
        findElementsByCssSelector("div,p");

        //zgnieżdżanie //nesting
        findElementsByCssSelector("div p");
        findElementsByCssSelector("form label");
        findElementsByCssSelector("#pierwszyDiv label");
        findElementsByCssSelector("div.px-4 input[name^='w']");

        findElementsByCssSelector(".mb-3 > label");
        findElementsByCssSelector("div > input[name^='w']");

        findElementsByCssSelector("#myForm *:first-child");
        findElementsByCssSelector("#myForm *:last-child");

        findElementsByCssSelector("#myForm button:first-child");
        findElementsByCssSelector("#myForm button:last-child");

        findElementsByCssSelector("#myForm *:nth-child(2)");
        findElementsByCssSelector("#myForm *:nth-child(3)");
    }
    // auto from dev tools //right click and copy
    @Test
    void autoFromDevToolsExample(){
    findElementsByCssSelector("#myForm > div:nth-child(1) > label");
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
        //List<WebElement> inputs = driver.findElements(By.cssSelector(".px-4 input.form-control"));
        //assertThat(inputs).hasSize(2);

        //1. wpisać wagę // enter weight
        WebElement weightInput = driver.findElement(By.cssSelector(".px-4 input[name=waga]"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        //2. wpisać wzrost //enter height
        WebElement heightInput = driver.findElement(By.cssSelector(".px-4 input.form-control[name=wzrost]"));
        heightInput.clear();
        heightInput.sendKeys(height);

        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement button = driver.findElement(By.cssSelector("#pierwszyDiv form > a:last-child"));
        button.click();

        //THEN
        //4. sprawdź BMI // check BMI
        //diver.getTitle() jest równy "Kalkulator BMI" // diver.getTitle() equals "BMI Calculator"
        //with error message

        WebElement bmi = driver.findElement(By.cssSelector("main > div > h1:nth-child(3)"));
        WebElement bmiNote = driver.findElement(By.cssSelector("main > div > h1:nth-child(4)"));
        WebElement errorMsg = driver.findElement(By.cssSelector("main > div > h1:nth-child(5)"));

        assertThat(bmi.getText()).isEqualTo(expectedBmi);
        assertThat(bmiNote.getText()).isEqualTo(expectedBmiNote);
        assertThat(errorMsg.getText()).isEmpty();
    }

    private static void listTags(List<WebElement> tags) {
        System.out.println("Liczba tagów: " + tags.size());
        tags.forEach(ByCssSelectorTest::tagInfo);
    }

    private static void findByCssSelector(String cssSelector) {
        WebElement tag = driver.findElement(By.cssSelector(cssSelector));
        tagInfo(tag);
    }
    private static void findElementsByCssSelector(String cssSelector) {
        System.out.println("Css selector:" + cssSelector);
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
        listTags(elements);
        System.out.println("======================================");
        System.out.println();
    }
    private static void tagInfo(WebElement tag) {
        System.out.println("tag:" + tag.getTagName() +
                ", id: " + tag.getAttribute("id") +
                ", name " +tag.getAttribute("name") +
                ", class: " +tag.getAttribute("class") +
                ", text: " + tag.getText());
    }
}