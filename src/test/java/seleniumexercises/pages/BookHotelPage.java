package seleniumexercises.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
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
    private By detailsFirstButton = By.xpath("//div[@class='product-long-item-wrapper']/div/div/div[2]/div/div[3]//a");
    private By searchButton = By.id("searchform");

    //Detail hotel selectors ---------------------------------------------------------------------------------

    private By modifyButton = By.xpath("//button[@data-target='#change-search'] ");
    private By textfieldNewDestination = By.className("select2-input");
    private By dropdownCheckIn = By.id("checkin");
    private By dropdownCheckOut = By.id("checkout");
    private By modifySearchButton = By.xpath("change-search");

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
 * (double lower / double upperValue) * upperPercentage = specifiedLowerPercentage
 * (double upper / double upperValue) * upperPercentage = specifiedUpperPercentage
 */

        String highestPrice = driver.findElement(By.xpath("//span[@class='irs-grid-text js-grid-text-4']")).getText();
        highestPrice = highestPrice.replaceAll("\\s","");
        double upperValue = Double.parseDouble(highestPrice);

        double specifiedLowerPercentage = (lower / upperValue) * 91.4894;
        int roundedSpecifiedLower = (int) Math.round(specifiedLowerPercentage);

        WebElement slideLower = driver.findElement(By.xpath("//span[@class='irs-slider from']"));
        Actions moveUp = new Actions(driver);
        moveUp.dragAndDropBy(slideLower, roundedSpecifiedLower, 0);
        moveUp.perform();

        double specifiedUpperPercentage = (upper / upperValue) * 91.4894;
        int roundedSpecifiedUpper = (int) Math.round(specifiedUpperPercentage);

        WebElement slideUpper = driver.findElement(By.xpath("//span[@class='irs-slider to']"));
        Actions moveDown = new Actions(driver);
        moveDown.dragAndDropBy(slideUpper, roundedSpecifiedUpper, 0);
        moveDown.perform();

        return this;
    }

    public BookHotelPage selectFilters(String filter1, String filter2){
        selenium.click(By.xpath("//label[@for='" + filter1 + "']"));
        selenium.click(By.xpath("//label[@for='" + filter2 + "']"));
        return this;
    }

    public BookHotelPage viewMoreButton(){
        selenium.click(viewMoreButton);
        return this;
    }

    public BookHotelPage setPropertyType(String propertyType){
        selenium.click(By.xpath("//label[@for='" + propertyType + "']"));
        return this;
    }

    public BookHotelPage setPriceFilter(String priceFilter){
        selenium.click(By.xpath("//label[contains(text(),'" + priceFilter + "')]"));
        return this;
    }

    public BookHotelPage searchButton(String yesOrNoSearch){
        if (yesOrNoSearch == "yes" || yesOrNoSearch == "Yes" || yesOrNoSearch == "YES"){
            selenium.click(searchButton);
        } else {
            System.out.println("Search button is not pressed");
        }
        return this;
    }

    public BookHotelPage buttonDetailsFirstHotel(){
        selenium.click(detailsFirstButton);
        return this;
    }

    //Detail Hotel functions ---------------------------------------------------------------------------------

    public BookHotelPage setModifyYesOrNo(String modify){
        if(modify == "Yes" || modify=="yes"){
        selenium.click(modifyButton);
        }
        return this;
    }

    public BookHotelPage setNewDestination(String destination){

        boolean modifyPageVisible = selenium.isDisplayed(By.xpath("//label[text()='Destination']"));
        if(modifyPageVisible) {
            selenium.dropdown(textfieldNewDestination, textfieldNewDestination, destination);
        }
        return this;
    }

    public BookHotelPage setDropdownDates (int dateCheckIn, int dateCheckOut){

        boolean modifyPageVisible = selenium.isDisplayed(By.xpath("//label[text()='Destination']"));
        if(modifyPageVisible){

            selenium.click(dropdownCheckIn);
            selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[1]//div[contains(text(),'%d']", dateCheckIn)));

            selenium.click(dropdownCheckOut);
            selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[1]//div[contains(text(),'%d']", dateCheckOut)));
        }

        return this;
    }

    /*
        private By selectDataCheckin = By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div/div[contains(text(),'20')]");
        private By selectDataCheckOut = By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div/div[contains(text(),'24')]");


        //And then the option to reselect number of persons hath vanished...(insert mysterious background noises)
        private By buttonAdults = By.xpath("//input[@name='adults']/parent::div/span/button[text()='+']");
        private By buttonChildren = By.xpath("//input[@name='children']/parent::div/span/button[text()='+']");
        private By buttonLessAdults = By.xpath("//input[@name='adults']/parent::div/span/button[text()='-']");

        Reworked should look something this:

        public BookHotelPage setAmountOfPeopleStupidEnoughToUseThisSiteToBook(int adultNum, int childrenNum){

        int placeholderValue =
                Integer.parseInt(driver.findElement(By.xpath("//input[@name='adults']")).getAttribute("value"));

        if(placeholderValue > 0){
            for(int i=0; i<placeholderValue; i++){
                selenium.click(buttonAdultsDown);
            }
        }

        for(int i = 0; i<adultNum;i++){
            selenium.click(buttonAdultsUp);
        }

        for(int i=0; i<childrenNum;i++){
            selenium.click(buttonChildrenUp);
        }
        return this;
    }
     */

}
