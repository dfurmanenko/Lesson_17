package tests;
import factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import pages.GaragePage;
import pages.HomePage;


public class AddCarTest {
    WebDriver driver;
    HomePage homePage;
    GaragePage garagePage;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getChromeDriver();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        homePage = new HomePage(driver);
        garagePage = new GaragePage(driver);
    }

    @Test
    public void testAddCarToGarage() {
        SoftAssert softAssert = new SoftAssert();

        // Крок 3: Клік на "Guest log in"
        homePage.clickGuestLogin();

        // Крок 4: Перевірка переходу на сторінку Garage
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, "https://qauto.forstudy.space/panel/garage", "URL не відповідає очікуваному");

        // Крок 5-7: Додавання автомобіля
        garagePage.clickAddCar();
        garagePage.selectBrand("Audi");
        garagePage.selectModel("TT");
        garagePage.enterMileage("20");
        garagePage.clickSaveCar();

        // Перевірки
        softAssert.assertTrue(garagePage.isCarAddedDisplayed(), "Автомобіль 'Audi TT' не відображається на сторінці");
        softAssert.assertTrue(garagePage.getCurrentMileageDate().contains("2024"), "Дата додавання автомобіля не відповідає поточній");
        softAssert.assertEquals(garagePage.getMileageValue(), "20", "Значення пробігу не відповідає очікуваному (20)");
        softAssert.assertTrue(garagePage.isCarLogoDisplayed(), "Лого автомобіля не відображається");
        softAssert.assertTrue(garagePage.getCarLogoSrc().endsWith("audi.png"), "Зображення автомобіля не має очікуваного розширення 'audi.png'");

        // Валідація soft asserts
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
