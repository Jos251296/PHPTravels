package seleniumexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class VisaConfirmationPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;


    private By displayVisaSubmitted = By.xpath("//h4[@class='wow fadeIn']//strong");
    private By displayReservationCode = By.xpath("//h4[text()='Reservation Code : ']//strong");
    private By buttonBackToHome = By.xpath("//a[text()='Go Back to Home']");
    private By buttonViewInvoice = By.xpath("//a[text()='View Invoice']");

    public VisaConfirmationPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public VisaConfirmationPage getVisaSubmittedResult(){
        boolean visaSubmitted = selenium.isDisplayed(displayVisaSubmitted);
        if (visaSubmitted){
            System.out.println("Your visa has been submitted");
        } else {
            System.out.println("Your visa has not been submitted");
        }

        return this;
    }

    public VisaConfirmationPage getReservationCode(){
        String reservationCode = selenium.getElementText(displayReservationCode);
        System.out.println("Your reservation code is: " + reservationCode);
        return this;
    }

    public VisaConfirmationPage setViewInvoice(boolean view){

        if(view){
            selenium.click(buttonViewInvoice);
        } else {
            selenium.click(buttonBackToHome);
        }
        return this;
    }
}
