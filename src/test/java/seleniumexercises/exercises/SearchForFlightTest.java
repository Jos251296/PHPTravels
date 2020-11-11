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
                .setOneWayOrRoundTrip("one way")
                .setFlightClass("Economy")
                .setOriginAndDestination(
                        "Pago Pago",
                        "Madinah")
                .setDepartDate()
                .setReturnDate()
                .setNumberOfPassengers(
                        4,
                        2,
                        1)
                .setSearchButton();

        new BookFlightPage(driver)
                .setSearchValidated()
                .setDirect(true)
                .setPriceRange(1, 300)
                .setSelectAirlines("")
                .setSpecifySearch(false)
                .setModifySearch(false)
                .setOneWay(true)
                .setNewFlightClass("Economy")
                .setNewOriginAndDestination("Pago Pago", "Madinah")
                .setNewDepartDate(20)
                .buttonSearchModified()
                .buttonBookFirstFlight();

        new BookFlightPassengerPage(driver)
                .setFlightSelectedConfirmation();
    }


    @After
    public void stopBrowser() {
        driver.quit();
    }

}

