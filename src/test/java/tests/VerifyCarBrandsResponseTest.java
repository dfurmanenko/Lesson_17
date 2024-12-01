import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class VerifyCarBrandsResponseTest {

    @Test
    public void testGetCarBrands() {

        Response response = RestAssured.given()
                .baseUri("https://qauto.forstudy.space/api")
                .when()
                .get("/cars/brands");


        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(response.statusCode(), 200, "Response code is not 200");


        List<Map<String, Object>> brands = response.jsonPath().getList("data");


        boolean hasAudi = brands.stream()
                .anyMatch(brand ->
                        Integer.valueOf(1).equals(brand.get("id")) &&
                                "Audi".equals(brand.get("title"))
                );
        softAssert.assertTrue(hasAudi, "The response body does not contain the expected brand with id=1 and title='Audi'");


        softAssert.assertAll();
    }
}
