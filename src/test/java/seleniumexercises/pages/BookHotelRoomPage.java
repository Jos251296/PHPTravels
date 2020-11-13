package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookHotelRoomPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToAssert = By.xpath("//span[text()='Overview']");

    public BookHotelRoomPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookHotelRoomPage Should_Assert_That_Page_Is_Loaded(){
        Assert.assertEquals("Overview", selenium.getElementText(textfieldToAssert));

        boolean hotelSelected = selenium.isDisplayed(textfieldToAssert);
        if(hotelSelected){
            System.out.println("You've selected a hotel.");
        } else {
            System.out.println("You haven't selected a hotel.");
        }
        return this;
    }

}
