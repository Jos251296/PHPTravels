package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import seleniumexercises.helpers.SeleniumHelpers;

import java.awt.print.Book;

public class BookFlightPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToBeAsserted = By.xpath("//h4[text()='Filter Search']");
    private By textfieldSearchValidated = By.className("heading-title");
    private By selectDirect = By.xpath("//label[@for='0']");
    private By buttonViewMore = By.xpath("//span[text()='View More (+)']");
    private By buttonSpecifiedSearch = By.xpath("//button[text()='Search']");
    private By buttonModifySearch = By.xpath("//button[text()='Modify Search']");
    private By buttonOneway = By.xpath("//label[@for='flightSearchRadio-2']");
    private By buttonRoundTrip = By.xpath("//label[@for='flightSearchRadio-1']");
    private By dropdownFlightClass = By.className("form-icon-left flightclass");
    private By textfieldCountryFrom = By.id("s2id_location_from");
    private By textfieldCountryTo = By.id("sid_location_to");
    private By dropdownDepartDate = By.id("FlightsDateStart");
    private By buttonSearchModified = By.xpath("//div[@class='col-lg-1 col-sm-12 col-xs-12']//button[contains(text(),'Search')]");
    private By buttonBook = By.xpath("//ul[@id='LIST']/li/div/div/div[2]/form/div[2]//button");

    public BookFlightPage(WebDriver driver){
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public BookFlightPage Should_Assert_That_Page_Is_Loaded(){
        Assert.assertEquals("Filter Search", selenium.getElementText(textfieldToBeAsserted));

        boolean hotelSelected = selenium.isDisplayed(textfieldSearchValidated);
        if(hotelSelected){
            System.out.println("Your search has been submitted.");
        } else {
            System.out.println("Your query was insufficient.");
        }
        return this;
    }

    public BookFlightPage Should_Set_Direct_Flight(boolean yes){
        if(yes){
            selenium.click(selectDirect);
        }
        return this;
    }

    public BookFlightPage Should_Set_Lower_And_Upper_PriceRange(int lower, int upper){
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

    public BookFlightPage Should_Set_Airline_Company (boolean selectAirline, String airlineCompany) {
        if(selectAirline) {
            selenium.click(buttonViewMore);
            selenium.click(By.xpath("//span[contains(text(),'" + airlineCompany + "')]"));
        }
        return this;
    }

    public BookFlightPage Should_Press_Search_Button_To_Narrow_Down_Options(boolean yes){
        if(yes){
            selenium.click(buttonSpecifiedSearch);
        }
        return this;
    }

    public BookFlightPage Should_Press_Button_To_Modify_Original_Input(boolean yes){
        if(yes){
            selenium.click(buttonModifySearch);
        }
        return this;
    }

    public BookFlightPage Should_Modify_Original_Search_Query
            (boolean oneWay, String flightClass, String origin, String destination, int dateDepart){
        boolean modifySearch = selenium.isDisplayed(By.xpath("//label[text()='From']"));
        if(modifySearch){
            if(oneWay){
                selenium.click(buttonOneway);
            } else {
                selenium.click(buttonRoundTrip);
            }

            selenium.click(dropdownFlightClass);
            selenium.click(By.xpath(String.format("//ul//li[contains(text(), '%s')]", flightClass)));

            selenium.dropdown(textfieldCountryFrom, textfieldCountryFrom, origin);
            selenium.click(By.xpath(String.format("//div[@class='select2-result-label']//span[text()='%s']", origin)));
            selenium.dropdown(textfieldCountryTo, textfieldCountryTo, destination);
            selenium.click(By.xpath(String.format("//div[@class='select2-result-label']//span[text()='%s']", destination)));

            selenium.click(dropdownDepartDate);
            selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[1]//div[contains(text(),'%d']", dateDepart)));

            selenium.click(buttonSearchModified);
        }
        return this;
    }

    public BookFlightPage Should_Select_First_Option_Available(){
        selenium.click(buttonBook);
        return this;
    }

}
