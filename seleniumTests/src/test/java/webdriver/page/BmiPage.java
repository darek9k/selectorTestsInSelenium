package webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BmiPage {

    @FindBy(id = "waga")
    private WebElement weight;

    @FindBy(id = "wzrost")
    private WebElement height;

    @FindBy(id = "submitBtn")
    private WebElement btn;

    @FindBy(id = "bmi")
    private WebElement bmi;

    @FindBy(id = "bmiNote")
    private WebElement bmiNote;

    @FindBy(id = "errorMsg")
    private WebElement errorMsg;

    public BmiPage(WebDriver driver) {

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void calculateBmi(String weight, String height) {
        //1. Wpisac wage
        this.weight.clear();
        if (weight != null) {
            this.weight.sendKeys(weight);
        }
        //2. wpisac wzrost
        this.height.clear();
        if (height != null) {
            this.height.sendKeys(height);
        }

        //3. wcisnac oblicz
        btn.click();
    }

    public String getBmiText() {
        return bmi.getText();
    }

    public String getBmiNoteText() {
        return bmiNote.getText();
    }

    public String getErrorMsgText() {
        return errorMsg.getText();
    }
}