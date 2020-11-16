package seleniumexercises.exercises;
import org.openqa.selenium.By;
import seleniumexercises.helpers.SeleniumHelpers;
import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VisaApplicationTest {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    @Before
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void should_ApplyForVisa_When_HappyFlow() {

        new HomePage(driver)
                .load()
                .selectMenuItem("Visa");

        Assert.assertEquals("VISA",
                driver.findElement(By.xpath("//a[@data-name='visa']")).getText());

        new SearchForVisaPage(driver)
                .shouldValidatePageLoaded()
                .shouldSetOriginAndDestination(
                        "American Samoa",
                        "Saudi Arabia")
                .shouldSetDate()
                .shouldSubmitQuery();

        Assert.assertEquals("American Samoa",
                driver.findElement(By.xpath("//h3/strong")).getText());

        new VisaApplicationPage(driver)
                .shouldValidatePageLoaded()
                .shouldSetPersonalia(
                        "Holly",
                        "Day",
                        "holly_day@badpuns.com",
                        "420-555-6969",
                        "A curious sperm whale and an exasperated bowl of petunias")
                .shouldPressBookingButton();

        Assert.assertEquals("Visa Submitted",
                driver.findElement(By.xpath("//h4[@class='wow fadeIn']//strong")).getText());

        new VisaConfirmationPage(driver)
                .shouldValidatePageLoaded()
                .shouldGetReservation_Code()
                .shouldPressViewInvoiceButton(true);

        Assert.assertEquals("Your booking status is waiting",
                driver.findElement(By.xpath("//h4")).getText());

        new VisaInvoicePage(driver)
                .shouldValidatePageLoaded();
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
