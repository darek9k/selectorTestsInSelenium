package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5500/bmiFrontend/");

        //1. wpisać wagę 80 // enter weight 80
        WebElement weightInput = driver.findElement(By.id("waga"));
        weightInput.sendKeys("80");

        //2. wpisać wzrost 188.5
        WebElement heightInput = driver.findElement(By.id("wzrost"));
        heightInput.sendKeys("188.5");
        //3. wcisnąć oblicz // enter height 188.5
        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        submitBtn.click();
        //4. sprawdź BMI // check BMI
        WebElement bmi = driver.findElement(By.id("bmi"));
        WebElement bmiNote = driver.findElement(By.id("bmiNote"));
        WebElement errorMsg = driver.findElement(By.id("errorMsg"));

        //writing to the console
        System.out.println(bmi.getText());
        System.out.println(bmiNote.getText());
        System.out.println(errorMsg.getText());

        //Thread.sleep(5000);
        driver.quit();
    }
}