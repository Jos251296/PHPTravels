package seleniumexercises.exercises;
import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookFlightTest {

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
                .Should_Assert_Page_Is_Loaded()
                .Should_Select_OneWay(true)
                .Should_Select_Flight_Class("Economy")
                .Should_Set_Origin_And_Destination(
                        "Pago Pago",
                        "Madinah")
                .Should_Set_Depart_And_Return_Date()
                .Should_Set_Number_Of_Passengers(
                        4,
                        2,
                        1)
                .Should_Press_Search_Button();

        new BookFlightPage(driver)
                .Should_Assert_That_Page_Is_Loaded()
                .Should_Set_Direct_Flight(false)
                .Should_Set_Lower_And_Upper_PriceRange(1, 300)
                .Should_Set_Airline_Company(false,"")
                .Should_Press_Search_Button_To_Narrow_Down_Options(false)
                .Should_Press_Button_To_Modify_Original_Input(false)
                .Should_Modify_Original_Search_Query(
                        true,
                        "Economy",
                        "Pago Pago",
                        "Madinah",
                        20)
                .Should_Select_First_Option_Available();

        new BookFlightPassengerPage(driver)
                .Should_Assert_That_Page_Is_Loaded();
    }


    @After
    public void stopBrowser() {
        driver.quit();
    }

}

