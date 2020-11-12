package seleniumexercises.pages;

import org.junit.Assert;
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

    public VisaConfirmationPage Should_Assert_Page_Is_Loaded(){
        Assert.assertEquals("Visa Submitted", selenium.getElementText(displayVisaSubmitted));

        boolean visaSubmitted = selenium.isDisplayed(displayVisaSubmitted);
        if (visaSubmitted){
            System.out.println("Your visa has been submitted.");
        } else {
            System.out.println("Your visa has not been submitted.");
        }

        return this;
    }

    public VisaConfirmationPage Should_Get_Reservation_Code(){
        String reservationCode = selenium.getElementText(displayReservationCode);
        System.out.println("Your reservation code is: " + reservationCode);
        return this;
    }

    public VisaConfirmationPage Should_Press_Button_To_View_Invoice(boolean view){

        if(view){
            selenium.click(buttonViewInvoice);
        } else {
            selenium.click(buttonBackToHome);
        }
        return this;
    }
}
