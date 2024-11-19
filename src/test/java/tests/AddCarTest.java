package tests;

import factory.BrowserFactory;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GaragePage;
import pages.HomePage;

@Epic("Garage Functionality")
@Feature("Add Car to Garage")
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
    @Description("Test to add a new car to the garage and verify its details.")
    @Owner("QA Student")
    @Link(name = "Task Link", url = "https://example.com/task-id")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddCarToGarage() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Step 1: Log in as a guest", () -> homePage.clickGuestLogin());

        Allure.step("Step 2: Verify the transition to the Garage page", () -> {
            String currentUrl = driver.getCurrentUrl();
            softAssert.assertEquals(currentUrl, "https://qauto.forstudy.space/panel/garage", "URL не відповідає очікуваному");
        });

        Allure.step("Step 3-7: Add a car to the garage", () -> {
            garagePage.clickAddCar();
            garagePage.selectBrand("Audi");
            garagePage.selectModel("TT");
            garagePage.enterMileage("20");
            garagePage.clickSaveCar();
        });

        Allure.step("Step 8: Verify the added car details", () -> {
            softAssert.assertTrue(garagePage.isCarAddedDisplayed(), "Автомобіль 'Audi TT' не відображається на сторінці");
            softAssert.assertTrue(garagePage.getCurrentMileageDate().contains("2024"), "Дата додавання автомобіля не відповідає поточній");
            softAssert.assertEquals(garagePage.getMileageValue(), "20", "Значення пробігу не відповідає очікуваному (20)");
            softAssert.assertTrue(garagePage.isCarLogoDisplayed(), "Лого автомобіля не відображається");
            softAssert.assertTrue(garagePage.getCarLogoSrc().endsWith("audi.png"), "Зображення автомобіля не має очікуваного розширення 'audi.png'");
        });

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
