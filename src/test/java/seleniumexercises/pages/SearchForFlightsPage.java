package seleniumexercises.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumexercises.helpers.SeleniumHelpers;

public class SearchForFlightsPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldToAssert = By.xpath("//a[@data-name='flights']");
    private By buttonOneWay = By.xpath("//label[@for='flightSearchRadio-2']");
    private By buttonRoundTrip = By.xpath("//label[@for='flightSearchRadio-1']");
    private By dropDownFlightClass = By.xpath("//select[@name='cabinclass']/following-sibling::div");
    private By textfieldCountryFrom = By.xpath("//div[@id='s2id_location_from']/a");
    private By textfieldCountryTo = By.xpath("//div[@id='s2id_location_to']/a");

    //Date picker module --------------------------------------------------------------------------------------------
    private By textfieldDepartDate = By.id("FlightsDateStart");
    private By dataTableDepart = By.xpath("//*[@id='datepickers-container']/div[9]/div/div[1]/div/div[contains(text(),'20')]");
    private By textfieldReturnDate = By.id("FlightsDateEnd");
    private By dataTableReturn = By.xpath("//*[@id='datepickers-container']/div[10]/div/div/div/div[contains(text(),'24')]");

    //Amount of passengers selectors ---------------------------------------------------------------------------------
    private By buttonAdultsUp = By.xpath("//input[@name='fadults']/parent::div/span/button[text()='+']");
    private By buttonAdultsDown = By.xpath("//input[@name='fadults']/parent::div/span/button[text()='-']");
    private By buttonChildUp = By.xpath("//input[@name='fchildren']/parent::div/span/button[text()='+']");
    private By buttonInfantUp = By.xpath("//input[@name='finfant']/parent::div/span/button[text()='+']");

    private By buttonSearch = By.xpath("//div[@class='col-lg-1 col-sm-12 col-xs-12']//button[contains(text(),'Search')]");

    //Search flight functions ---------------------------------------------------------------------------------
    public SearchForFlightsPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public SearchForFlightsPage Should_Assert_Page_Is_Loaded(){

        Assert.assertEquals("FLIGHTS", selenium.getElementText(textfieldToAssert));
        boolean searchQuery = selenium.isDisplayed(textfieldToAssert);
        if(searchQuery){
            System.out.println("Entering search query.");
        } else {
            System.out.println("Cannot open FLIGHTS tab.");
        }
        return this;
    }

    public SearchForFlightsPage Should_Select_OneWay(boolean oneWay) {
        if(oneWay){
            selenium.click(buttonOneWay);
        } else {
            selenium.click(buttonRoundTrip);
        }
        return this;
    }

    public SearchForFlightsPage Should_Select_Flight_Class(String flightClass) {
        selenium.click(dropDownFlightClass);
        selenium.click(By.xpath(String.format("//ul[@class='chosen-results']//li[text()='%s']", flightClass)));
        return this;
    }

    public SearchForFlightsPage Should_Set_Origin_And_Destination(String origin, String destination){
        selenium.dropdown(textfieldCountryFrom, textfieldCountryFrom, origin);
        selenium.click(By.xpath(String.format("//div[@class='select2-result-label']//span[text()='%s']", origin)));

        selenium.dropdown(textfieldCountryTo, textfieldCountryTo, destination);
        selenium.dropdown(textfieldCountryTo, textfieldCountryTo, destination);
        selenium.click(By.xpath(String.format("//div[@class='select2-result-label']//span[text()='%s']", destination)));
        return this;
    }

    public SearchForFlightsPage Should_Set_Depart_And_Return_Date() {

        selenium.click(textfieldDepartDate);
        selenium.click(dataTableDepart);

        boolean returnDateAvailable = selenium.isDisplayed(textfieldReturnDate);
        if(returnDateAvailable){
            selenium.click(textfieldReturnDate);
            selenium.click(dataTableReturn);
        }
        return this;
    }


    public SearchForFlightsPage Should_Set_Number_Of_Passengers(int amountOfAdults, int amountOfChildren, int amountOfInfants){

        int placeholderValue =
                Integer.parseInt(driver.findElement(By.xpath("//input[@name='fadults']")).getAttribute("value"));

        if(placeholderValue > 0){
            for(int i=0; i<placeholderValue; i++){
                selenium.click(buttonAdultsDown);
            }
        }

        for(int i=0; i<amountOfAdults; i++) {
                selenium.click(buttonAdultsUp);
        }

        for(int i=0; i<(amountOfChildren); i++) {
            selenium.click(buttonChildUp);
        }

        for(int i=0; i<(amountOfInfants); i++) {
            selenium.click(buttonInfantUp);
        }
        return this;
    }

    public SearchForFlightsPage Should_Press_Search_Button() {
        selenium.click(buttonSearch);
        return this;
    }

    /************************************************
     * DE CODE HIERONDER IS VERWIJDERD GEDURENDE HET
     * LEESBAAR MAKEN VAN DE PAGE EN TEST OBJECTEN.
     * ALS COMMENTAAR BEWAARD VOOR EVT. BEOORDELING
     ************************************************/

    /*

        private By popupFrom = By.xpath("//div[@id='select2-drop']/ul/li/div");
        private By popupTo = By.xpath("//div[@id='select2-drop']/ul/li/div/span");
        private By monthTitle = By.xpath("//*[@id=\"datepickers-container\"]/div[9]/nav/div[2]");
        private By buttonNextMonth = By.xpath("//*[@id=\"datepickers-container\"]/div[9]/nav/div[3]");
            private By buttonChild = By.xpath("//input[contains(@name, \"fchildren\")]/parent::div/span//button[contains(@class, \"bootstrap-touchspin-up\")]");

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

        selenium.click(textfieldCountryTo);
        selenium.sendKeys(textfieldCountryTo, countryTo);
        selenium.click(popupTo);
        return this;
    }


     */


}