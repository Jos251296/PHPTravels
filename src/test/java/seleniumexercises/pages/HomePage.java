package seleniumexercises.pages;

import seleniumexercises.helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    final String BASE_URL = "https://phptravels.net";

    private By buttonAcceptCookies = By.xpath("//button[text()='Got it!']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public HomePage load() {
        driver.get(BASE_URL);
        selenium.click(buttonAcceptCookies);
        return this;
    }

    public HomePage selectMenuItem(String menuItem) {
        selenium.click(By.xpath("//a[contains(text(), '" + menuItem + "')]"));
        return this;
    }
}
