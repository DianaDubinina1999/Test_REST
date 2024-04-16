package org.ibs;

import org.junit.jupiter.api.Test;

public class RestTest {

    @Test
    void test1 () {
        Specifications.installSpecification (Specifications.requestSpecification
                ("http://localhost:8080/api"), Specifications.responseSpecification (200));

        Utils.getFood ();
        Utils.isTableBase ();

        Utils.postFood ("Огурец", "VEGETABLE", false);
        Utils.getInfo ("Огурец");

        Utils.postFood ("Артишок", "VEGETABLE", true);
        Utils.getInfo ("Артишок");

        Utils.postFood ("Вишня", "VEGETABLE", false);
        Utils.getInfo ("Вишня");

        Utils.postFood ("Манго", "VEGETABLE", true);
        Utils.getInfo ("Манго");


        Utils.resetFood ();

        Utils.isTableBase ();
    }
}
