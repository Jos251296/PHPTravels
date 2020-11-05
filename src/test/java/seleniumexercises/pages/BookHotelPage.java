package seleniumexercises.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookHotelPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;
    private By filterButton = By.xpath("//input[@id='Airport Transport']");

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookHotelPage selectRadioButton(String starNum){
        selenium.click(By.xpath("//label[@for='" + starNum + "']"));
        return this;
    }

    /*public BookHotelPage selectPriceRange(){

    }*/

    public BookHotelPage selectFilterButton(String filterType){
        selenium.click(By.xpath("//input[@id='" + filterType + "']"));
        return this;
    }


}
