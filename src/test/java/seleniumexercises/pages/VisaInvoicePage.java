package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class VisaInvoicePage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By displayStatus = By.xpath("//h4");

    public VisaInvoicePage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public VisaInvoicePage shouldValidatePageLoaded() {

        boolean visaSubmitted = selenium.isDisplayed(displayStatus);
        if (visaSubmitted) {
            System.out.println("Viewing your invoice");
        } else {
            System.out.println("Cannot display your invoice.");
        }
        return this;
    }
}
