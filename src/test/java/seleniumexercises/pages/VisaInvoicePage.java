package seleniumexercises.pages;

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

    public String getInvoiceResult(){
        return selenium.getElementText(displayStatus);
    }
}
