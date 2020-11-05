package seleniumexercises.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumexercises.helpers.SeleniumHelpers;

import java.awt.print.Book;

public class BookHotelPage {


    private WebDriver driver;
    private SeleniumHelpers selenium;

    //Select Hotel functions ---------------------------------------------------------------------------------

    private By viewMoreButton = By.xpath("//span[contains(text(),'View More (+)')]");
    private By detailsButton = By.xpath("/html/body/div[1]/div[1]/div[1]/section/div/div[2]/div[2]/div/div[2]/div[1]/div/div[2]/div/div[3]/div/div[2]/a");
    private By searchButton = By.xpath("//button[@id='searchform']");

    //Detail hotel selectors ---------------------------------------------------------------------------------

    private By dropdownCheckIn = By.id("checkin");
    private By selectDataCheckin = By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div/div[contains(text(),'20')]");
    private By dropdownCheckOut = By.id("checkout");
    private By selectDataCheckOut = By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div/div[contains(text(),'24')]");
    private By buttonAdults = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By buttonChildren = By.xpath("//input[@name= 'children']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By buttonLessAdults = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-down')]");
    private By modifyButton = By.xpath("//button[contains(text(),'Modify')]");

    //Select Hotel functions ---------------------------------------------------------------------------------

    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookHotelPage selectRadioButton(String starNum){
        selenium.click(By.xpath("//label[@for='" + starNum + "']"));
        return this;
    }

    public BookHotelPage selectPriceRange(int lower, int upper){

    /**
 * xpath to button lower price is //span[@class='irs-slider from'] (hotels)
 * of //span[@class='irs-slider from type_last']
 * xpath to button higher price is //span[@class='irs-slider to']
 * lower value is weergegeven hier (getText om mee te rekenen): //span[@class="irs-grid-text js-grid-text-0"]
 * lower % is altijd 0%
 * upper value is weergegeven hier (getText om mee te rekenen): //span[@class="irs-grid-text js-grid-text-4"]
 * upper % is altijd 91.4894%
 *
 * (double lower * double upperValue) * upperPercentage = specifiedLowerPercentage
 * (double upper * double upperValue) * upperPercentage = specifiedUpperPercentage
 */

        String highestPrice = driver.findElement(By.xpath("//span[@class='irs-grid-text js-grid-text-4']")).getText();
        double upperValue = Double.parseDouble(highestPrice);

        double specifiedLowerPercentage = (lower * upperValue) * 91.4894;
        int roundedSpecifiedLower = (int) Math.round(specifiedLowerPercentage);

        WebElement slideLower = driver.findElement(By.xpath("//span[@class='irs-slider from]"));
        Actions moveUp = new Actions(driver);
        moveUp.dragAndDropBy(slideLower, roundedSpecifiedLower, 0);

        double specifiedUpperPercentage = (upper * upperValue) * 91.4894;
        int roundedSpecifiedUpper = (int) Math.round(specifiedUpperPercentage);

        WebElement slideUpper = driver.findElement(By.xpath("//span[@class='irs-slider to']"));
        Actions moveDown = new Actions(driver);
        moveDown.dragAndDropBy(slideUpper, roundedSpecifiedUpper, 0);

        return this;
    }

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

    public BookHotelPage clickSearchButton(String yesOrNoSearch){
        if (yesOrNoSearch == "yes" || yesOrNoSearch == "Yes" || yesOrNoSearch == "YES"){
            selenium.click(searchButton);
        } else {
            System.out.println("Search button is not pressed");
        }
        return this;
    }

    public BookHotelPage clickDetailsButton(){
        selenium.click(detailsButton);
        return this;
    }

    //Detail Hotel functions ---------------------------------------------------------------------------------

    public BookHotelPage setDropdownCheckIn (){

        selenium.click(dropdownCheckIn);
        selenium.click(selectDataCheckin);
        return this;
    }

    public BookHotelPage setDropdownCheckOut() {
        selenium.click(dropdownCheckOut);
        selenium.click(selectDataCheckOut);
        return this;
    }

    public BookHotelPage setAdultNum(int adultNum){
        if(adultNum >= 2) {
            for (int i = 0; i < (adultNum - 2); i++)
                selenium.click(buttonAdults);
        } else if(adultNum == 1) {
            selenium.click(buttonLessAdults);
        } else {
            System.out.println("please select the number of adults!!!!");
        }

        return this;
    }

    public BookHotelPage setChildrenNum(int childrenNum){
        for(int i=0; i<(childrenNum); i++)
            selenium.click(buttonChildren);
        return this;
    }

    public BookHotelPage clickModifyButton(){
        selenium.click(modifyButton);
        return this;
    }

}
