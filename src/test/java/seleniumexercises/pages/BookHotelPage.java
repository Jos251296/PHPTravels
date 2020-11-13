package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import seleniumexercises.helpers.SeleniumHelpers;

public class BookHotelPage {


    private WebDriver driver;
    private SeleniumHelpers selenium;

    //Select Hotel selectors ---------------------------------------------------------------------------------
    private By textfieldToAssert = By.xpath("//h4[text()='Filter Search']");
    private By viewMoreButton = By.xpath("//span[contains(text(),'View More (+)')]");
    private By buttonHighToLow = By.xpath("//label[text()='High to Low']");
    private By buttonLowToHigh = By.xpath("//label[text()='Low to High']");
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

    public BookHotelPage Should_Assert_That_Page_Is_Loaded(){
        Assert.assertEquals("Filter Search", selenium.getElementText(textfieldToAssert));

        boolean queryConfirmation = selenium.isDisplayed(textfieldToAssert);
        if(queryConfirmation){
            System.out.println("Your search query has been submitted.");
        } else{
            System.out.println("Your query was insufficient.");
        }
        return this;
    }

    public BookHotelPage Should_Filter_Search_Result(
            boolean filterSearch, String starRating, int lowerPrice, int upperPrice, String amenities1, String amenities2,
            String propertyType, boolean highToLow){

        if(filterSearch){
            selenium.click(By.xpath("//label[@for='" + starRating + "']"));

            /***************************************************************************************************
             * Laagste bedrag in slidebar is in paginacode gelijk aan 0%
             * Hoogste bedrag in slidebar is in paginacode gelijk aan 91.4894%
             * (double lower * double upperValue) * upperPercentage = specifiedLowerPercentage (i.e. lowerPrice)
             * (double upper * double upperValue) * upperPercentage = specifiedUpperPercentage (i.e. upperPrice)
             ***************************************************************************************************/

            String highestPrice = driver.findElement(By.xpath("//span[@class='irs-grid-text js-grid-text-4']")).getText();
            highestPrice = highestPrice.replaceAll("\\s","");
            double upperValue = Double.parseDouble(highestPrice);

            double specifiedLowerPercentage = (lowerPrice / upperValue) * 91.4894;
            int roundedSpecifiedLower = (int) Math.round(specifiedLowerPercentage);

            WebElement slideLower = driver.findElement(By.xpath("//span[@class='irs-slider from']"));
            Actions moveUp = new Actions(driver);
            moveUp.dragAndDropBy(slideLower, roundedSpecifiedLower, 0);
            moveUp.perform();

            double specifiedUpperPercentage = (upperPrice / upperValue) * 91.4894;
            int roundedSpecifiedUpper = (int) Math.round(specifiedUpperPercentage);

            WebElement slideUpper = driver.findElement(By.xpath("//span[@class='irs-slider to']"));
            Actions moveDown = new Actions(driver);
            moveDown.dragAndDropBy(slideUpper, roundedSpecifiedUpper, 0);
            moveDown.perform();

            selenium.click(viewMoreButton);
            selenium.click(By.xpath("//label[@for='" + amenities1 + "']"));
            selenium.click(By.xpath("//label[@for='" + amenities2 + "']"));

            selenium.click(By.xpath("//label[@for='" + propertyType + "']"));

            if(highToLow){
                selenium.click(buttonHighToLow);
            } else {
                selenium.click(buttonLowToHigh);
            }

            selenium.click(searchButton);
        }
        return this;
    }


    public BookHotelPage Should_Select_First_Hotel(){
        selenium.click(detailsFirstButton);
        return this;
    }

    //Detail Hotel functions ---------------------------------------------------------------------------------

    public BookHotelPage Should_Modify_Original_Search_Query(boolean modifySearch, String destination, int dateCheckIn, int dateCheckOut){

        if(modifySearch) {
            selenium.dropdown(textfieldNewDestination, textfieldNewDestination, destination);
        }

        selenium.click(dropdownCheckIn);
        selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[1]//div[contains(text(),'%d']", dateCheckIn)));

        selenium.click(dropdownCheckOut);
        selenium.click(By.xpath(String.format("//div[@id='datepickers-container']/div[1]//div[contains(text(),'%d']", dateCheckOut)));

        selenium.click(modifySearchButton);
        return this;
    }

    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

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
