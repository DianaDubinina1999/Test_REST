package org.ibs;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class Utils {

    public static void postFood (String foodName, String foodType, boolean isExotic) {
        RestAssured.defaultParser = Parser.JSON;

        given ()
                .basePath ("/food")
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .body ("{\n" +
                        "  \"name\": \"" + foodName + "\",\n" +
                        "  \"type\": \"" + foodType + "\",\n" +
                        "  \"exotic\": " + isExotic + "\n" +
                        "}")
                .when ()
                .post ()
                .then ()
                .log ().all ()
                .assertThat ()
                //.contentType(ContentType.JSON)
                .body ("name", equalTo (foodName));
    }

    public static void getFood () {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .when ()
                .get ("/food")
                .then ()
                .log ().all ();
    }


    public static void resetFood () {
        given ()
                .header ("Accept", "application/json")
                .header ("Content-Type", "application/json")
                .when ()
                .post ("/data/reset")
                .then ()
                .log ().all ();
    }

}