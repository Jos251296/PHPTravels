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
                .setDropdownCheckIn()
                .setDropdownCheckOut()
                .setAdultNum(2)
                .setChildrenNum(0)
                .clickSubmitHotel();

        new BookHotelPage(driver)
                .selectRadioButton("2")
<<<<<<< HEAD
//                .clickViewMoreButton()
//                .selectFilterButton("Night Club")
//                .selectFilterButton("SPA")
                .selectPriceRange(50, 1000);
//                .clickPropertyType("Hotel")
//                .clickPriceFilter("Low to High")
//                .clickSearchButton("Yes")
//                .clickDetailsButton();
=======
                .clickViewMoreButton()
                .selectFilterButton("Night Club")
                .selectFilterButton("SPA")
                .clickPropertyType("Hotel")
                .clickPriceFilter("Low to High")
                .clickSearchButton("Yes")
                .clickDetailsButton()
                .setDropdownCheckIn()
                .setDropdownCheckOut()
                .setAdultNum(2)
                .setChildrenNum(0)
                .clickModifyButton();
>>>>>>> 2964c2dfe58e17d4d7374be05344e00499ee5a4c
    }

//    @After
//    public void stopBrowser() {
//        driver.quit();
//    }

}
