package seleniumexercises.helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelpers {

    private WebDriver driver;

    public SeleniumHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By by) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();
        }
        catch (TimeoutException te) {
            Assert.fail(String.format("Exception in click(): %s", te.getMessage()));
        }
    }

    public void sendKeys(By by, String textToType) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).sendKeys(textToType);
        }
        catch (TimeoutException te) {
            Assert.fail(String.format("Exception in sendKeys(): %s", te.getMessage()));
        }
    }

    /**
     * SELECT werkt inderdaad niet,vandaar deze omslachtige helper.
     * Gedachte: misschien list van values vangen en invoer daartegen
     * vergelijken om het gewenste element te selecteren?
     * Lijkt ook omslachtig...
     */
    public void dropdown(By by1, By by2, String country){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by1));
            driver.findElement(by1).click();
            driver.findElement(by2).sendKeys(country);
        }
        catch (TimeoutException te) {
            Assert.fail(String.format("Exception in select(): %s", te.getMessage()));
        }
    }

    public boolean isDisplayed(By by) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }
        catch (TimeoutException te) {
            return false;
        }
    }

    public String getElementText(By by) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by).getText();
        }
        catch (TimeoutException te) {
            return "Element not found";
        }
    }


}
