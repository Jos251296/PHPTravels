package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookFlightPassengerPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToAssert = By.xpath("//h4[text()='Booking Summary']");

    public BookFlightPassengerPage(WebDriver driver){
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookFlightPassengerPage Should_Assert_That_Page_Is_Loaded(){
        Assert.assertEquals("Booking Summary", selenium.getElementText(textfieldToAssert));

        boolean flightSelected = selenium.isDisplayed(textfieldToAssert);
        if(flightSelected){
            System.out.println("Selected a flight, entering personalia.");
        } else {
            System.out.println("Cannot select a flight.");
        }
        return this;
    }
}
