package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.selenium.dev/documentation/webdriver/");
        WebElement seleniumLogo = driver.findElement(By.id("Layer_1"));
        seleniumLogo.click();

        System.out.println(driver.getCurrentUrl());

        Thread.sleep(2000);
        driver.quit();
    }
}