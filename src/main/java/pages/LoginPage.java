package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {

    By loginBtn = By.xpath("//button[contains(text(),'Sign in')]");
    By emailTxtField = By.xpath("//label[contains(text(),'Email')]/following-sibling::input[@name=('email')]");
    By passTxtField = By.xpath("//label[contains(text(),'Password')]/following-sibling::input[@name=('password')]");
    By signIn = By.xpath("//form/button[contains(text(),'Sign In')]");

    // Initializing Page Object
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }



    public void logInTajwalPage() throws InterruptedException {
        driver.findElement(loginBtn).click();
        driver.findElement(emailTxtField).sendKeys(prob.getProperty("userName"));
        driver.findElement(passTxtField).sendKeys(prob.getProperty("password"));
        driver.findElement(signIn).click();

    }

   public  String getTokenId() throws InterruptedException {
        Thread.sleep(5000);
        Cookie getTokenValue = driver.manage().getCookieNamed("token");
        String getTokenId = getTokenValue.getValue();
        return getTokenId;
    }
}
