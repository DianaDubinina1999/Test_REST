package org.ibs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Utils {

    static String sessionID;

    public static Response getFood () {
        Response response = given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .when ()
                .get ("/food");
        sessionID = response.getCookie ("JSESSIONID");
        return response;
    }

    public static void postFood (String foodName, String foodType, boolean isExotic) {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .cookie ("JSESSIONID", sessionID)
                .body ("{\n" +
                        "  \"name\": \"" + foodName + "\",\n" +
                        "  \"type\": \"" + foodType + "\",\n" +
                        "  \"exotic\": " + isExotic + "\n" +
                        "}")
                .when ()
                .post ("/food")
                .then ()
                .log ().all ();

    }

    public static void resetFood () {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .cookie ("JSESSIONID", sessionID)
                .when ()
                .post ("/data/reset")
                .then ()
                .log ().all ();
    }

    public static void getInfo (String foodName) {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .cookie ("JSESSIONID", sessionID)
                .when ()
                .get ("/food")
                .then ()
                .log ().all ()
                .assertThat ()
                .body ("name", hasItem (foodName));
    }

    public static void isTableBase () {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .cookie ("JSESSIONID", sessionID)
                .when ()
                .get ("/food")
                .then ()
                .log ().all ()
                .assertThat ()
                .body ("name", containsInAnyOrder ("Апельсин", "Капуста", "Помидор", "Яблоко"));
    }
}
