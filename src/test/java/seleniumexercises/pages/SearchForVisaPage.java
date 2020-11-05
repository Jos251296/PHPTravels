package seleniumexercises.pages;

import seleniumexercises.helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForVisaPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By dropdownFromCountry = By.xpath("//select[@name='nationality_country']/following-sibling::div");
    private By textfieldFromCountry = By.xpath("//select[@name='nationality_country']/following-sibling::div//input[@class='chosen-search-input']");
    private By dropdownToCountry = By.xpath("//select[@name='destination_country']/following-sibling::div");
    private By textfieldToCountry = By.xpath("//select[@name='destination_country']/following-sibling::div//input[@class='chosen-search-input']");
    private By textfieldDate = By.name("date");
    private By buttonSubmit = By.xpath("//button[text()='Submit']");

    public SearchForVisaPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public SearchForVisaPage setCountryOfOriginTo(String countryOfOrigin) {

        selenium.click(dropdownFromCountry);
        selenium.sendKeys(textfieldFromCountry, countryOfOrigin);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryOfOrigin)));
        return this;
    }

    public SearchForVisaPage setCountryToVisitTo(String countryToVisit) {

        selenium.click(dropdownToCountry);
        selenium.sendKeys(textfieldToCountry, countryToVisit);
        selenium.click(By.xpath(String.format("//li/em[text()='%s']", countryToVisit)));
        return this;
    }

    public SearchForVisaPage setDate (String date){

        selenium.click(textfieldDate);
        selenium.sendKeys(textfieldDate, date);
        return this;
    }

    public void submit (){

        selenium.click(buttonSubmit);
    }
}