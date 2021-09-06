import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import pages.HomePage;
import pages.LoginPage;
import pages.PageBase;


public class LoginPageTest extends PageBase {

    LoginPage loginPage;
    HomePage homePage;
    String getTokenId;

    @Before
    @Step("Launching The Browser")
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test()
    @Story("Open Tajwal And Login")
    public void openTajawalPage() throws InterruptedException {
        loginPage.logInTajwalPage();
        Thread.sleep(5000);
        Cookie getTokenValue = driver.manage().getCookieNamed("token");
        getTokenId = getTokenValue.getValue();
        String getAccount=homePage.getLoggedAccount();
        Assert.assertEquals(prob.getProperty("userName"),getAccount);
    }

    public String getToken(){
        return getTokenId;
    }

    @After
    public void closeDriver(){
        driver.quit();
    }
}
