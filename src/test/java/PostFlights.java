import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import pages.PageBase;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostFlights extends PageBase {

    @Test
    public void modifySearchFlightsAPI() {
        String response = given()
                .header("Content-Type", "application/json").log().all().when()
                .body(apiPayLoads.modifySearch())
                .post(prob.getProperty("postFlightsResource"))
                .then().statusCode(200).extract().response().asString();
        // System.out.println("SSSS " + response);
        JsonPath jsonResponse = new JsonPath(response);
        int originsCount = jsonResponse.getInt("origins.size()");
        int destinationsCount = jsonResponse.getInt("default_destinations.size()");

        assertEquals(Integer.parseInt(prob.getProperty("originsCount")), originsCount);
        assertEquals(Integer.parseInt(prob.getProperty("destinationsCount")), destinationsCount);

        for (int i = 0; i < jsonResponse.getInt("origins.id_airport.size()"); i++) {
            String airportId = jsonResponse.get("origins.id_airport[" + i + "]");
            if (airportId.equals(null)) {

                Assert.fail("Airport Id Can't be Null");
            }
        }

    }
}
