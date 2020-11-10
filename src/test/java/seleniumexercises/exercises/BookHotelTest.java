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
                .selectRadioButton("2")
                .viewMoreButton()
                .selectFilters("Night Club", "SPA")
                .selectPriceRange(50,1000)
                .setPropertyType("Hotel")
                .setPriceFilter("Low to High")
                .searchButton("Yes")
                .setModifyYesOrNo("No")
                .setNewDestination("Alzer")
                .setDropdownDates(20,24)
                .buttonDetailsFirstHotel();

    }

    @After
    public void stopBrowser() {
        driver.quit();
    }

}
