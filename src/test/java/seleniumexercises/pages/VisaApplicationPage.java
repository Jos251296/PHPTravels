package seleniumexercises.pages;

import org.openqa.selenium.WebElement;
import restassuredexercises.Booking;
import seleniumexercises.helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VisaApplicationPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By displayBooking = By.className("collapse-link");
    private By textfieldFirstName = By.name("first_name");
    private By textfieldLastName = By.name("last_name");
    private By textfieldEmail = By.name("email");
    private By textfieldConfirmEmail = By.name("confirmemail");
    private By textfieldContactNumber = By.name("phone");
    private By buttonAdditionalRequests = By.className("switch");
    private By textfieldAdditionalrequests = By.name("notes");
    private By buttonBooking = By.id("sub");

    public VisaApplicationPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public VisaApplicationPage getSubmitResults(){

        boolean searchSubmitted = selenium.isDisplayed(displayBooking);
        if(searchSubmitted) {
            System.out.println("Plesae enter your personalia here");
        } else {
            System.out.println("The search was invalid. Please try again.");
        }
        return this;
    }

    public VisaApplicationPage setPersonalia(String firstName, String lastName, String email, String contactNumber, String additionalRequests){

        selenium.sendKeys(textfieldFirstName, firstName);
        selenium.sendKeys(textfieldLastName, lastName);
        selenium.sendKeys(textfieldEmail, email);
        selenium.sendKeys(textfieldConfirmEmail, email);
        selenium.sendKeys(textfieldContactNumber, contactNumber);
        selenium.click(buttonAdditionalRequests);
        selenium.sendKeys(textfieldAdditionalrequests, additionalRequests);
        return this;
    }

    public void bookingButton() {

        selenium.click(buttonBooking);
    }


    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

    /*
    public VisaApplicationPage setFirstName(String firstName) {

        selenium.sendKeys(textfieldFirstName, firstName);
        return this;
    }

    public VisaApplicationPage setLastName(String lastName) {

        selenium.sendKeys(textfieldLastName, lastName);
        return this;
    }

    public VisaApplicationPage setEmail(String email) {

        selenium.sendKeys(textfieldEmail, email);
        selenium.sendKeys(textfieldConfirmEmail, email);
        return this;
    }

    public VisaApplicationPage setContactNumber (String contactNumber) {

        selenium.sendKeys(textfieldContactNumber, contactNumber);
        return this;
    }

    public VisaApplicationPage additionalRequestsButton() {

        selenium.click(buttonAdditionalRequests);
        return this;
    }

    public VisaApplicationPage setAdditionalRequest(String additionRequests){

        selenium.click(textfieldAdditionalrequests);
        selenium.sendKeys(textfieldAdditionalrequests, additionRequests);
        return this;
    }

     */

}