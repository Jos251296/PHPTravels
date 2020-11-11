package restassuredexercises;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class PersonaCreator {

    private static RequestSpecification requestSpec;

    @Before
    public void setupRequestSpecification() {
        requestSpec = RestAssured.given().
                baseUri("https://restful-booker.herokuapp.com/").
                basePath("booking/");
    }


    public int getPersona(){
        Booking persona = new Booking();
        BookingDates personaDates = new BookingDates();

        personaDates.setCheckIn("01-12-2020");
        personaDates.setCheckOut("01-01-2021");

        persona.setFirstName("Holly");
        persona.setLastName("Day");
        persona.setTotalPrice(420);
        persona.setBookingDates(personaDates);
        persona.setAdditionalNeeds("Massage");

        int personaId =
                given().
                        spec(requestSpec).
                        contentType(ContentType.JSON).
                        body(persona).
                        when().
                        post("https://restful-booker.herokuapp.com/booking").
                        then().
                        extract().
                        path("bookingid");

        return personaId;

    }
}
