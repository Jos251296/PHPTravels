package seleniumexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookFlightPassengerPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldFlightSelectedConfirm = By.xpath("//h4[text()='Booking Summary']");

    public BookFlightPassengerPage(WebDriver driver){
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookFlightPassengerPage setFlightSelectedConfirmation(){
        boolean flightSelected = selenium.isDisplayed(textfieldFlightSelectedConfirm);
        if(flightSelected){
            System.out.println("You have selected a flight, entering personalia.");
        } else {
            System.out.println("You haven't selected a flight.");
        }
        return this;
    }
}
