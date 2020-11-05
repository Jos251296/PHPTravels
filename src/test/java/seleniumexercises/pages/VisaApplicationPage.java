package seleniumexercises.pages;

import seleniumexercises.helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VisaApplicationPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldFirstName = By.name("first_name");
    private By textfieldLastName = By.name("last_name");
    private By textfieldEmail = By.name("email");
    private By textfieldConfirmEmail = By.name("confirmemail");
    private By textfieldContactNumber = By.name("phone");
    private By buttonAdditionalRequests = By.xpath("//input[@type='checkbox']");
    private By textfieldAdditionalrequests = By.xpath("//textarea[@name='notes']");
    private By buttonBooking = By.xpath("//button[text()='Booking']");

    public VisaApplicationPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

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
        return this;
    }

    public VisaApplicationPage setConfirmEmail (String confirmEmail) {

        selenium.sendKeys(textfieldConfirmEmail, confirmEmail);
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

    public VisaApplicationPage bookingButton() {

        selenium.click(buttonBooking);
        return this;
    }

}