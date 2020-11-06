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
                .setCountryOfOriginTo("American Samoa")
                .setCountryToVisitTo("Saudi Arabia")
                .setDate()
                .submit();

        new VisaApplicationPage(driver)
                .setFirstName("Holly")
                .setLastName("Day")
                .setEmail("holly_day@badpuns.com")
                .setConfirmEmail("holly_day@badpuns.com")
                .setContactNumber("465-555-8378")
                .additionalRequestsButton()
                .setAdditionalRequest("A pot of tea")
                .bookingButton();
    }

    @After
        public void stopBrowser() {

        driver.quit();
    }
}
