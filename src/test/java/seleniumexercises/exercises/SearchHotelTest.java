package seleniumexercises.exercises;

import seleniumexercises.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchHotelTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void flightSearch() throws InterruptedException {

        new HomePage(driver)
                .load()
                .selectMenuItem("Hotels");

        new SearchHotelPage(driver)
                .setTextfieldDestination("Madinah")
                .setDropdownCheckIn()
                .setDropdownCheckOut()
                .setAdultNum(2)
                .setChildrenNum(3)
                .clickSubmitHotel();
    }

    @After
    public void stopBrowser() {
        driver.quit();
    }

}
