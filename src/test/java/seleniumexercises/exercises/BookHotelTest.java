package seleniumexercises.exercises;
import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BookHotelTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void bookHotel(){

        new HomePage(driver)
                .load();

        new SearchHotelPage(driver)
                .setTextfieldDestination("Istanbul")
                .setDropdownCheckIn()
                .setDropdownCheckOut()
                .setAdultNum(2)
                .setChildrenNum(0)
                .clickSubmitHotel();

        new BookHotelPage(driver)
                .selectRadioButton("2")
                .selectFilterButton("Night Club");
    }

    @After
    public void stopBrowser() {
        driver.quit();
    }

}
