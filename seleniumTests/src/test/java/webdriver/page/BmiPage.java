package webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BmiPage {
    private final WebDriver driver;

    private final WebDriverWait wait;
    private final By weightBy = By.id("waga");

    private final By heightBy = By.id("wzrost");

    private final By btnBy = By.id("submitBtn");

    private final By bmiBy = By.id("bmi");

    private final By bmiNoteBy = By.id("bmiNote");

    private final By errorMsgBy = By.id("errorMsg");


    public BmiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(3000));
    }

    public void calculateBmi(String weight, String height){
        //1. wpisać wagę z zabezpieczeniem na null// enter weight
        WebElement weightInput = driver.findElement(weightBy);
        weightInput.clear();
        if(weight!=null) {
            weightInput.sendKeys(weight);
        }
        //2. wpisać wzrost z zabezpieczeniem na null// enter height
        WebElement heightInput = driver.findElement(heightBy);
        heightInput.clear();
        if(height!=null) {
            heightInput.sendKeys(height);
        }
        //WHEN
        //3. wcisnąć oblicz // click on 'oblicz'
        WebElement submitBtn = driver.findElement(btnBy);
        submitBtn.click();
    }
    public String getBmiText(){
        return findElementWithWaiting(bmiBy).getText();
    }
    public String getBmiNoteText(){
        return findElementWithWaiting(bmiNoteBy).getText();
    }
    public String getErrorMsgText(){
        return driver.findElement(errorMsgBy).getText();
    }
    private WebElement findElementWithWaiting(By by){
        System.out.println("Wyszkuje po By: " + by.toString());
        long start = System.currentTimeMillis();
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        long end = System.currentTimeMillis();
        System.out.println("Trwało: " + (end-start));
        return webElement;
    }
}
