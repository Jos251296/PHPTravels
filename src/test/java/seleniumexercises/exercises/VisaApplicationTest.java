package seleniumexercises.exercises;
import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VisaApplicationTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void applyForVisa() {

        new HomePage(driver)
                .load()
                .selectMenuItem("Visa");

        new SearchForVisaPage(driver)
                .Should_Assert_Page_Is_Loaded()
                .Should_Set_Origin_And_Destination(
                        "American Samoa",
                        "Saudi Arabia")
                .Should_Set_Date()
                .Should_Submit_Query();

        new VisaApplicationPage(driver)
                .Should_Assert_That_Page_Is_Loaded()
                .Shoudld_Set_Personalia(
                        "Holly",
                        "Day",
                        "holly_day@badpuns.com",
                        "420-555-6969",
                        "A curious sperm whale and an exasperated bowl of petunias")
                .Should_Press_Booking_Button();

        /**
         * setViewInvoice requires a "Yes" or "yes" statement to continue to the invoice
         */
        new VisaConfirmationPage(driver)
                .Should_Assert_Page_Is_Loaded()
                .Should_Get_Reservation_Code()
                .Should_Press_Button_To_View_Invoice(true);

        new VisaInvoicePage(driver)
                .Should_Assert_Page_Is_Loaded();
    }

    @After
        public void stopBrowser() {

        driver.quit();
    }

    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

    /*

                 .setCountryOfOriginTo("American Samoa")
                .setCountryToVisitTo("Saudi Arabia")

                 .setFirstName("Holly")
                .setLastName("Day")
                .setEmail("holly_day@badpuns.com")
                .setContactNumber("420-555-6969")
                .additionalRequestsButton()
                .setAdditionalRequest("A curious sperm whale and an exasperated bowl of petunias")
     */


}
