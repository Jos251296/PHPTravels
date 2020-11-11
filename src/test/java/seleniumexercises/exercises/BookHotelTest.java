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
                .setTextfieldDestination("Alzer")
                .setDropdownDates(
                        20,
                        24)
                .setAmountOfVisitors(
                        2,
                        0)
                .clickSubmitHotel();

        new BookHotelPage(driver)
                .getSearchConfirmation()
                .selectRadioButton("2")
                .selectFilters("Night Club", "SPA")
                .selectPriceRange(50,1000)
                .setPropertyType("Hotel")
                .setPriceFilter("Low to High")
                .searchButton(true)
                .setModify(false)
                .setNewDestination("Alzer")
                .setDropdownDates(20,24)
                .setModifySearch()
                .buttonDetailsFirstHotel();

        new BookHotelRoomPage(driver)
                .getHotelSelectedConfirmation();

    }

    @After
    public void stopBrowser() {
        driver.quit();
    }

}
