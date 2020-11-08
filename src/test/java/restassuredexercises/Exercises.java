package restassuredexercises;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Exercises {

    private static RequestSpecification requestSpec;

    /**
     * Persona zoals gePOST via Postman:
     * first name = Holly
     * last name = Day
     * total price = 175
     * deposit paid = true
     * bookingdate checkin = 01-12-2020
     * bookingdate checkout = 01-01-2021
     * additional needs = Massage
     *
     * Note the bookingid after posting persona.
     */

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
        System.out.println(personaId);
        return personaId;

    }



    @Before
    public void setupRequestSpecification() {
        requestSpec = RestAssured.given().
                baseUri("https://restful-booker.herokuapp.com/").
                basePath("booking/");
    }

    @Test
    public void getBooking_checkStatusCode_shouldReturnHttp200() {

        /**
         * Perform a GET to https://restful-booker.herokuapp.com/booking/<your_booking_id>
         * and check that the status code equals 200
         */

//        PersonaHelper personaHelper = new PersonaHelper();
//        int personaId = personaHelper.getPersona();

        given().
                when().
                get("https://restful-booker.herokuapp.com/booking/" + getPersona()).
                then().assertThat().statusCode(200);
    }

    @Test
    public void getBooking_checkAdditionalNeeds_shouldReturnBreakfast() {

        /**
         * Perform a GET to https://restful-booker.herokuapp.com/booking/<your_booking_id>
         * and check that the value of the JSON response body element
         * 'additionalneeds' equals 'Breakfast'
         */

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + getPersona()).
                then()
                .assertThat().body("additionalneeds", equalTo("Massage"));
    }

    @Test
    public void getBooking_deserializeResponse_checkFirstName() {

        /**
         * Perform a GET to https://restful-booker.herokuapp.com/booking/<your_booking_id>.
         * Deserialize the JSON response to an instance of the Booking class.
         * You don't need to create or modify the Booking class yourself, it's in the 'main' package.
         * Use a JUnit assertion to check that the value of the 'firstname' element
         * is equal to your supplied first name.
         */

        Booking booking =

                given()
                        .when()
                        .get("https://restful-booker.herokuapp.com/booking/" + getPersona())
                        .as(Booking.class);

        Assert.assertEquals("Holly", booking.getFirstName());
    }

    @Test
    public void createBooking_extractId_retrieveBooking_checkLastName() {

        Booking booking = new Booking();
        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckIn("01-01-2020");
        bookingDates.setCheckOut("01-01-2021");

        booking.setFirstName("Bas");
        booking.setLastName("Dijkstra");
        booking.setTotalPrice(500);
        booking.setBookingDates(bookingDates);
        booking.setAdditionalNeeds("Breakfast");

        /**
         * Perform a POST to https://restful-booker.herokuapp.com/booking,
         * using the booking object created above as the payload.
         * Don't remove the call to contentType(), otherwise it will not work :)
         *
         * Extract and store the generated bookingid as an integer.
         */

        int bookingId =

                given()
                        .spec(requestSpec)
                        .contentType(ContentType.JSON)
                        .body(booking)
                        .when()
                        .post("https://restful-booker.herokuapp.com/booking")
                        .then()
                        .extract()
                        .path("bookingid");

        System.out.println(bookingId);


        /**
         * Use that value as a path
         * parameter to retrieve the booking once again, and check that the last name is equal
         * to the value you set before. Either deserialize the response like in the previous exercise,
         * or use body("", equalTo("")) directly.
         *
         * You don't need to create or modify the Booking class yourself, it's in the 'main' package.
         */

        Booking booking1 =

                given().
                        when().
                        get("https://restful-booker.herokuapp.com/booking/" + bookingId).
                        as(Booking.class);

        Assert.assertEquals("Bas", booking1.getFirstName());

    }

}

