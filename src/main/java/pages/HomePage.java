package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends PageBase {

    By myAccount = By.xpath("//span[contains(text(),'My account')]");
    By loggedAccount = By.xpath("//p[contains(text(),'@')]");
    By closeMyAccountDetails = By.xpath("//button[contains(text(),'View')]/following-sibling::button");

    // Initializing Page Object
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String getLoggedAccount() {
        driver.findElement(myAccount).click();
        String loggedEmailId = driver.findElement(loggedAccount).getText();
        System.out.println("loggedEmailId : >>>" + loggedEmailId);
        waitForElementPresent(closeMyAccountDetails);
        driver.findElement(closeMyAccountDetails).click();
        return loggedEmailId;
    }
}
