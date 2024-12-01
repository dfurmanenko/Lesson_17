import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApiTest {

    @Test
    public void testLogoutApiResponse() {

        Response response = RestAssured.given()
                .get("https://qauto.forstudy.space/api/auth/logout");


        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();


        SoftAssert softAssert = new SoftAssert();

        // Перевірка статусу коду
        softAssert.assertEquals(statusCode, 200, "Статус код не відповідає очікуваному");

        // Перевірка тіла відповіді
        softAssert.assertTrue(responseBody.contains("\"status\":\"ok\""), "Тіло відповіді не відповідає очікуваному");

        // Виконання всіх перевірок
        softAssert.assertAll();
    }
}
