package seleniumexercises.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumexercises.helpers.SeleniumHelpers;

import java.awt.print.Book;

public class BookHotelPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;
    private By viewMoreButton = By.xpath("//span[contains(text(),'View More (+)')]");
    private By detailsButton = By.xpath("/html/body/div[1]/div[1]/div[1]/section/div/div[2]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[2]/a");

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
        selenium.click(By.xpath("//label[@for='" + filterType + "']"));
        return this;
    }

    public BookHotelPage clickViewMoreButton(){
        selenium.click(viewMoreButton);
        return this;
    }

    public BookHotelPage clickPropertyType(String propertyType){
        selenium.click(By.xpath("//label[@for='" + propertyType + "']"));
        return this;
    }

    public BookHotelPage clickPriceFilter(String priceFilter){
        selenium.click(By.xpath("//label[contains(text(),'" + priceFilter + "')]"));
        return this;
    }

    public BookHotelPage clickDetailsButton(){
        selenium.click(detailsButton);
        return this;
    }
}
