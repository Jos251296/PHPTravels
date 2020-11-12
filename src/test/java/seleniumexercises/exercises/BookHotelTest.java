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
                .Should_Select_Star_Grade("2")
                .Should_Select_Lower_And_Upper_PriceRange(50,1000)
                .Should_Select_Hotel_Amenities("Night Club", "SPA")
                .Should_Set_Property_Type("Hotel")
                .Should_Set_Price_Filter("Low to High")
                .Should_Press_Search_Button(true)
                .Should_Press_Button_To_Modify_Original_Input(false)
                .Should_Modify_Original_Search_Query(
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
