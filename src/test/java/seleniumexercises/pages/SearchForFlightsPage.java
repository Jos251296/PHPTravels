package seleniumexercises.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumexercises.helpers.SeleniumHelpers;

public class SearchForFlightsPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By buttonOneWay = By.xpath("//label[@for='flightSearchRadio-2']");
    private By buttonRoundTrip = By.xpath("//label[@for='flightSearchRadio-1']");
    private By dropDownFlightClass = By.xpath("//select[@name='cabinclass']/following-sibling::div");
    private By dropDownFirstClass = By.xpath("//select[@name='cabinclass']/following-sibling::div//div[@class='chosen-drop']//ul//li[contains(text(), 'First')]");
    private By dropDownBusinessClass = By.xpath("//select[@name='cabinclass']/following-sibling::div//div[@class='chosen-drop']//ul//li[contains(text(), 'Business')]");
    private By dropDownEconomyClass = By.xpath("//select[@name='cabinclass']/following-sibling::div//div[@class='chosen-drop']//ul//li[contains(text(), 'Economy')]");
    private By textfieldCountryFrom = By.xpath("//*[@id='s2id_location_from']/a");
    private By popupFrom = By.xpath("//*[@id='select2-drop']/ul/li/div");
    private By textfieldCountryTo = By.xpath("//*[@id='s2id_location_to']/a");
    private By popupTo = By.xpath("//*[@id='select2-drop']/ul/li/div/span");

    //Date picker module
    private By textfieldDepartDate = By.id("FlightsDateStart");
    //private By monthTitle = By.xpath("//*[@id=\"datepickers-container\"]/div[9]/nav/div[2]");
    //private By buttonNextMonth = By.xpath("//*[@id=\"datepickers-container\"]/div[9]/nav/div[3]");
    private By dataTableDepart = By.xpath("//*[@id=\"datepickers-container\"]/div[9]/div/div[1]/div/div[contains(text(),'20')]");
    private By textfieldReturnDate = By.id("FlightsDateEnd");
    private By dataTableReturn = By.xpath("//*[@id='datepickers-container']/div[10]/div/div/div/div[contains(text(),'24')]");

    private By buttonAdults = By.xpath("//input[contains(@name, \"fadults\")]/parent::div/span//button[contains(@class, \"bootstrap-touchspin-up\")]"); //needs to be number - 1
    private By buttonChild = By.xpath("//input[contains(@name, \"fchildren\")]/parent::div/span//button[contains(@class, \"bootstrap-touchspin-up\")]");
    private By buttonInfant = By.xpath("//input[contains(@name, \"finfant\")]/parent::div/span//button[contains(@class, \"bootstrap-touchspin-up\")]");
    private By buttonSearch = By.xpath("//div[@class='col-lg-1 col-sm-12 col-xs-12']//button[contains(text(),'Search')]"
    );

    public SearchForFlightsPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public SearchForFlightsPage setOneWayOrRoundTrip(String radioButton) {
        if(radioButton == "Round Trip" || radioButton == "round trip" || radioButton == "ROUND TRIP"){
            selenium.click(buttonRoundTrip);
        } else {
            selenium.click(buttonOneWay);
        }
        return this;
    }

    public SearchForFlightsPage setFlightClass (String flightClass) {
        selenium.click(dropDownFlightClass);

        if (flightClass == "First" | flightClass == "first" | flightClass == "FIRST") {
            selenium.click(dropDownFirstClass);
        } else if (flightClass == "Business" | flightClass == "business" | flightClass == "BUSINESS") {
            selenium.click(dropDownBusinessClass);
        } else selenium.click(dropDownEconomyClass);
        return this;
    }

    public SearchForFlightsPage setCountryFrom(String countryFrom){

        selenium.click(textfieldCountryFrom);
        selenium.sendKeys(textfieldCountryFrom, countryFrom);
        selenium.click(popupFrom);
        return this;
    }

    public SearchForFlightsPage setCountryTo(String countryTo) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(textfieldCountryTo));

        selenium.click(textfieldCountryTo);
        selenium.sendKeys(textfieldCountryTo, countryTo);
        selenium.click(popupTo);
        return this;
    }

    public SearchForFlightsPage setDepartDate(/*String day, String month*/) {

        selenium.click(textfieldDepartDate);

        /*String text = driver.findElement(monthTitle).getText();

        while(month != text){
                selenium.click(buttonNextMonth);
            }*/

        selenium.click(dataTableDepart);
        return this;
    }

    public SearchForFlightsPage setReturnDate() {
        selenium.click(textfieldReturnDate);
        selenium.click(dataTableReturn);
        System.out.println(dataTableReturn);
        return this;
    }

    public SearchForFlightsPage setNumberOfAdults(int numberOfAdults){

        for(int i=0; i<(numberOfAdults-1); i++)
            selenium.click(buttonAdults);
        return this;
    }

    public SearchForFlightsPage setNumberOfChildren(int numberOfChildren) {
        for(int i=0; i<(numberOfChildren); i++)
            selenium.click(buttonChild);
        return this;
    }

    public SearchForFlightsPage setNumberOfInfants(int numberOfInfants) {
        for(int i=0; i<(numberOfInfants); i++)
            selenium.click(buttonInfant);
        return this;
    }

    public SearchForFlightsPage setSearchButton() {
        selenium.click(buttonSearch);
        return this;
    }

}