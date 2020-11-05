package seleniumexercises.exercises;
import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchForFlightTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void flightSearch() {

        new HomePage(driver)
                .load()
                .selectMenuItem("Flights");

        new SearchForFlightsPage(driver)
                .setOneWayOrRoundTrip("round trip")
                .setFlightClass("Business")
                .setCountryFrom("Pago Pago")
                .setCountryTo("Medina")
                .setDepartDate()
                .setReturnDate()
                .setNumberOfAdults(4)
                .setNumberOfChildren(2)
                .setNumberOfInfants(1)
                .setSearchButton();

    }


    @After
    public void stopBrowser() {
        driver.quit();
    }

}

