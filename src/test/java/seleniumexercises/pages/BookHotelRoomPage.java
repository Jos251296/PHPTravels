package seleniumexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookHotelRoomPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By displayHotelBooked = By.xpath("//h2[@id='detail-content-sticky-nav-00']");

    public BookHotelRoomPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookHotelRoomPage getHotelSelectedConfirmation(){
        boolean hotelSelected = selenium.isDisplayed(displayHotelBooked);
        if(hotelSelected){
            System.out.println("You've selected a hotel.");
        } else {
            System.out.println("You haven't selected a hotel.");
        }
        return this;
    }

}
