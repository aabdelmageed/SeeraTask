package HttpMethods;

import data.ExcelReader;
import io.restassured.path.xml.XmlPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.PageBase;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class GetFlights extends PageBase {
    private String fromDate;
    private String toDate;
    static String ExcelfileName, sheetname = "flightsDates";
    static int TotalNumberOfCols = 2;
    static ExcelReader ER = new ExcelReader();

    public GetFlights(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Parameterized.Parameters
    public static Object[][] testData() throws IOException {

        ExcelfileName = "flightDates.xlsx";
        return ER.getExcelData(ExcelfileName, sheetname, TotalNumberOfCols);
    }

    @Test
    public void getFlightAPI() {
        String response = given().pathParam("distnation", "DXB-CAI").pathParam("fromDate", fromDate)
                .pathParam("toDate", toDate).log().all().when()
                .get(prob.getProperty("getFlightsResource"))
                .then().statusCode(200).extract().response().asString();
       // System.out.println("SSSS " + response);

        XmlPath doc = new XmlPath(
                XmlPath.CompatibilityMode.HTML, response);
        String title = doc.getString("html.head.title");
        assertEquals("Book Cheap flights from DXB to CAI, Save more with tajawal", title);
    }
}
