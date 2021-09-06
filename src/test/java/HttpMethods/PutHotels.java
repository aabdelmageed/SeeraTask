package HttpMethods;

import data.ExcelReader;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.PageBase;
import java.io.IOException;
import static io.restassured.RestAssured.given;

@RunWith(value = Parameterized.class)
public class PutHotels extends PageBase {
    private String fromDate;
    private String toDate;
    private String hotelId;
    static String excelFileName, sheetName = "adjustHotelsId&Dates";
    static int totalNumberOfCols = 3;
    static ExcelReader ER = new ExcelReader();


    public PutHotels(String fromDate, String toDate, String hotelId) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.hotelId = hotelId;
    }

    @Parameterized.Parameters
    public static Object[][] testData() throws IOException {
        excelFileName = "flightDates.xlsx";
        return ER.getExcelData(excelFileName, sheetName, totalNumberOfCols);
    }

    @Test
    public void putHotelsAPI() {

        Response response=given().header("token", prob.getProperty("token"))
                .header("Content-Type","application/json").log().all()
                .body(apiPayLoads.modifySearchHotels(fromDate,toDate,hotelId))
                .when()
                .put(prob.getProperty("putHotelResource"))
                .then().header("Content-Type","application/json").extract().response();

        if (response.getStatusCode()!=200){
            Assert.fail("No Rooms With Hotel Id In The Mentioned Dates Are Available");
            log.info("No Rooms With Hotel Id In The Mentioned Dates Are Available");
        }
        else {
            Assert.assertEquals(200,response.getStatusCode());
            log.info("Rooms With Hotel Id In The Mentioned Dates Are Available");
        }
    }


}
