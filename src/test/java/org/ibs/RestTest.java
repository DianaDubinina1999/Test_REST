package org.ibs;

import org.junit.jupiter.api.Test;

import static org.ibs.Utils.postFood;

public class RestTest {

    @Test
    void test1 () {
        Specifications.installSpecification (
                Specifications.requestSpecification ("http://localhost:8080/api"),
                Specifications.responseSpecification (200));

        Utils.getFood ();

        postFood ("Огурец", "VEGETABLE", false);
        postFood ("Артишок", "VEGETABLE", true);
        postFood ("Вишня", "VEGETABLE", false);
        postFood ("Манго", "VEGETABLE", true);

        Utils.resetFood ();

    }
}
