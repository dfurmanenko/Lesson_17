import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetCarBrandsTest {

    @Test
    public void testGetCarBrands() {

        Response response = RestAssured.given()
                .get("https://qauto.forstudy.space/api/cars/brands");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();


        SoftAssert softAssert = new SoftAssert();

        // Перевірка статусу коду
        softAssert.assertEquals(statusCode, 200, "Статус код не відповідає очікуваному");

        // Перевірка, чи тіло відповіді містить об'єкт з id: 1 та title: Audi
        softAssert.assertTrue(responseBody.contains("\"id\":1"), "Тіло відповіді не містить об'єкт з id: 1");
        softAssert.assertTrue(responseBody.contains("\"title\":\"Audi\""), "Тіло відповіді не містить title: Audi");


        softAssert.assertAll();
    }
}
