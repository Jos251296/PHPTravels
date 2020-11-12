package seleniumexercises.pages;

import org.junit.Assert;
import seleniumexercises.helpers.GetDateClass;
import seleniumexercises.helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchForVisaPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToAssert = By.xpath("//a[@data-name='visa']");
    private By dropdownFromCountry = By.xpath("//select[@name='nationality_country']/following-sibling::div");
    private By textfieldFromCountry = By.xpath("//select[@name='nationality_country']/following-sibling::div//input[@class='chosen-search-input']");
    private By dropdownNationalityList = By.xpath("//select[@name='nationality_country']/following-sibling::div//li");
    private By dropdownToCountry = By.xpath("//select[@name='destination_country']/following-sibling::div");
    private By textfieldToCountry = By.xpath("//select[@name='destination_country']/following-sibling::div//input[@class='chosen-search-input']");
    private By dropdownDestinationList = By.xpath("//select[@name='destination_country']/following-sibling::div//li");
    private By textfieldDate = By.name("date");
    private By buttonSubmit = By.xpath("//button[text()='Submit']");
    private By displayBooking = By.className("collapse-link");


    public SearchForVisaPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public SearchForVisaPage Should_Assert_Page_Is_Loaded(){
        Assert.assertEquals("VISA", selenium.getElementText(textfieldToAssert));
        return this;
    }

    public SearchForVisaPage Should_Set_Origin_And_Destination(String countryOfOrigin, String countryToVisit){

        selenium.dropdown(dropdownFromCountry, textfieldFromCountry, countryOfOrigin);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryOfOrigin)));

        selenium.dropdown(dropdownToCountry, textfieldToCountry, countryToVisit);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryToVisit)));
        return this;
    }

    public SearchForVisaPage Should_Set_Date(){

        GetDateClass date = new GetDateClass();
        String currentDate = date.getCurrentDate();
        selenium.click(textfieldDate);
        selenium.sendKeys(textfieldDate, currentDate);
        return this;
    }

    public void Should_Submit_Query(){

        selenium.click(buttonSubmit);

    }

    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

/*
    public SearchForVisaPage setCountryOfOriginTo(String countryOfOrigin) {

        selenium.click(dropdownFromCountry);
        selenium.sendKeys(textfieldFromCountry, countryOfOrigin);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryOfOrigin)));

        //selenium.dropdown(probeersel, countryOfOrigin);
        return this;
    }

    public SearchForVisaPage setCountryToVisitTo(String countryToVisit) {

        selenium.click(dropdownToCountry);
        selenium.sendKeys(textfieldToCountry, countryToVisit);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryToVisit)));

//        selenium.dropdown(dropdownToCountry, countryToVisit);
        return this;
    }
 */
}