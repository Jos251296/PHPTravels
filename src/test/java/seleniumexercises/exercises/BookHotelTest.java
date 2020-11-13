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
                .Should_Assert_That_Page_Is_Loaded()
                .Should_Set_Destination("Alzer")
                .Should_Set_Checkin_And_Checkout_Dates(
                        20,
                        24)
                .Should_Set_Amount_Of_Visitors(
                        2,
                        0)
                .Should_Submit_Search_Query();

        new BookHotelPage(driver)
                .Should_Assert_That_Page_Is_Loaded()
                .Should_Filter_Search_Result(
                        false,
                        "2",
                        50,
                        1000,
                        "Night Club",
                        "SPA",
                        "Hotel",
                        true)
                .Should_Modify_Original_Search_Query(
                        false,
                        "Alzer",
                        20,
                        24)
                .Should_Select_First_Hotel();

        new BookHotelRoomPage(driver)
                .Should_Assert_That_Page_Is_Loaded();

    }

    @After
    public void stopBrowser() {
        driver.quit();
    }

}
