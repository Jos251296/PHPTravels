package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class SearchHotelPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToAssert = By.xpath("//a[@data-name='hotels']");
    private By textfieldDestination = By.xpath("//div[@id='hotels']/div//a");
    private By dropdownCheckIn = By.id("checkin");
    private By dropdownCheckOut = By.id("checkout");

    private By buttonAdultsUp = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By buttonChildrenUp = By.xpath("//input[@name= 'children']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-up')]");
    private By buttonAdultsDown = By.xpath("//input[@name= 'adults']/parent::div/span//button[contains(@class, 'bootstrap-touchspin-down')]");
    private By submitHotelButton = By.xpath("//form[@name= 'HOTELS']/div/div/div[@class='col-lg-2 col-sm-12 col-xs-12']/button[contains(text(), 'Search')]");


    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public SearchHotelPage Should_Assert_That_Page_Is_Loaded(){
        Assert.assertEquals("HOTELS", selenium.getElementText(textfieldToAssert));
        return this;
    }

    public SearchHotelPage Should_Set_Destination(String destination){

        selenium.dropdown(textfieldDestination, textfieldDestination, destination);
        selenium.click(By.xpath("//div/span[@class='select2-match']"));
        return this;
    }

    public SearchHotelPage Should_Set_Checkin_And_Checkout_Dates(int dateCheckIn, int dateCheckOut){

        selenium.click(dropdownCheckIn);
        selenium.click(By.xpath(String.format("//div[@id='datepickers-container']//div[contains(text(),'%d')]", dateCheckIn)));

        selenium.click(dropdownCheckOut);
        selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[2]//div[contains(text(),'%d')]", dateCheckOut)));
        return this;
    }

    public SearchHotelPage Should_Set_Amount_Of_Visitors(int adultNum, int childrenNum){

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

    public SearchHotelPage Should_Submit_Search_Query(){
        selenium.click(submitHotelButton);
        return this;
    }

    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

    /*
        private By selectDataCheckOut = By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div/div[contains(text(),'24')]");
            private By selectDataCheckin = By.xpath("//div[@id=\"datepickers-container\"]//div[contains(text(),'20')]");

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

        public SearchHotelPage setTextfieldDestination (String destination){

        selenium.click(textfieldDestination);
        selenium.sendKeys(textfieldDestination, destination);
        selenium.click(By.xpath("//div/span[@class='select2-match']"));
        return this;
    }
     */
}
