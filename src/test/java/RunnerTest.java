import HttpMethods.GetFlights;
import HttpMethods.GetHotels;
import HttpMethods.PostFlights;
import HttpMethods.PutHotels;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({GetFlights.class , PostFlights.class , GetHotels.class, PutHotels.class})
public class RunnerTest {
}
