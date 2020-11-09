package seleniumexercises.exercises;
import seleniumexercises.helpers.GetDateClass;
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
                .setOriginAndDestination(
                        "American Samoa",
                        "Saudi Arabia")
                .setDate()
                .submit();

        new VisaApplicationPage(driver)
                .getSubmitResults()
                .setPersonalia(
                        "Holly",
                        "Day",
                        "holly_day@badpuns.com",
                        "420-555-6969",
                        "A curious sperm whale and an exasperated bowl of petunias")
                .bookingButton();

        /**
         * setViewInvoice requires a "Yes" or "yes" statement to continue to the invoice
         */
        new VisaConfirmationPage(driver)
                .getVisaSubmittedResult()
                .getReservationCode()
                .setViewInvoice("Yes");

        Assert.assertEquals("Your booking status is waiting",
                new VisaInvoicePage(driver).getInvoiceResult());
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
