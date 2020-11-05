package seleniumexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class SearchHotelPage {
    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldDestination = By.xpath("//div[@id='hotels']/div//a");
    private By textfieldHotelOrLocation = By.xpath("//*[@id='select2-drop']/div/input");
    private By dropdownCheckIn = By.id("checkin");
    private By selectDataCheckin = By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div/div[contains(text(),'20')]");
    private By dropdownCheckOut = By.id("checkout");
    private By selectDataCheckOut = By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div/div[contains(text(),'24')]");

    private By buttonAdults = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By buttonChildren = By.xpath("//input[@name= 'children']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By submitHotelButton = By.xpath("//form[@name= 'HOTELS']/div/div/div[@class='col-lg-2 col-sm-12 col-xs-12']/button[contains(text(), 'Search')]");
    private By buttonLessAdults = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-down')]");


    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }


    public SearchHotelPage setTextfieldDestination (String destination){

        selenium.click(textfieldDestination);
        selenium.sendKeys(textfieldDestination, destination);
        selenium.click(By.xpath("//div/span[@class='select2-match']"));
        return this;

    }

    public SearchHotelPage setDropdownCheckIn (){

        selenium.click(dropdownCheckIn);
        selenium.click(selectDataCheckin);
        return this;
    }

    public SearchHotelPage setDropdownCheckOut() {
        selenium.click(dropdownCheckOut);
        selenium.click(selectDataCheckOut);
        return this;
    }

    public SearchHotelPage setAdultNum(int adultNum){
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

    public SearchHotelPage setChildrenNum(int childrenNum){
        for(int i=0; i<(childrenNum); i++)
            selenium.click(buttonChildren);
        return this;
    }

    public SearchHotelPage clickSubmitHotel(){
        selenium.click(submitHotelButton);
        return this;
    }
}
