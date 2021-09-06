package HttpMethods;

import data.ExcelReader;
import io.restassured.path.xml.XmlPath;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.PageBase;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class GetHotels extends PageBase {
    private String fromDate;
    private String toDate;
    static String excelFileName, sheetName = "hotelDates";
    static int totalNumberOfCols = 2;
    static ExcelReader ER = new ExcelReader();
    static LoginPage loginPage = new LoginPage();
    static String tokenId;

    public GetHotels(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Parameterized.Parameters
    public static Object[][] testData() throws IOException {
        excelFileName = "flightDates.xlsx";
        return ER.getExcelData(excelFileName, sheetName, totalNumberOfCols);
    }

    @Before
    public  void getTokenAfterLogin() throws InterruptedException {
        initialization();
        loginPage.logInTajwalPage();
        tokenId = loginPage.getTokenId();
    }

    @Test
    public void getFlightAPI() {
        String response = given().header("token", tokenId).pathParam("fromDate", fromDate)
                .pathParam("toDate", toDate).queryParam("city", "Dubai").log().all().when()
                .get(prob.getProperty("getHotelsResource"))
                .then().statusCode(200).extract().response().asString();
        // System.out.println("SSSS " + response);
        XmlPath doc = new XmlPath(
                XmlPath.CompatibilityMode.HTML, response);
        String pageTitle = doc.getString("html.head.title");
        assertEquals("Dubai Hotels: Book Cheap Hotels in Dubai Online - Tajawal", pageTitle);
        int hotelsCount = doc.getInt("html.body.div.div.div.div.section.div.section.div.div.div.a.span.size()");
        assertEquals(Integer.parseInt(prob.getProperty("hotelsCountPerPage")), hotelsCount);
        for (int i = 0; i < doc.getInt("html.body.div.div.div.div.section.div.section.div.div.div.a.span.size()"); i++) {
            String hotelName = doc.get("html.body.div.div.div.div.section.div.section.div.div.div.a.span[" + i + "]");
            log.info("Hotel Number [" + i + "] His Name is " + hotelName + "\n");
            if (hotelName.equals(null)) {
                Assert.fail("Hotel Name Can't be Null");
            }
        }
    }

    @After
    public void closeDriver(){
        driver.quit();
    }
}
