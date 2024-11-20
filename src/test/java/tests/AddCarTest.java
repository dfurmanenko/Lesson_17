package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SelenideGaragePage;
import pages.SelenideHomePage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@Epic("Garage Functionality")
@Feature("Add Car to Garage")
public class AddCarTest {

    SelenideHomePage homePage;
    SelenideGaragePage garagePage;

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://qauto.forstudy.space";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        open("https://guest:welcome2qauto@qauto.forstudy.space/");
        homePage = new SelenideHomePage();
        garagePage = new SelenideGaragePage();
    }

    @Test
    @Description("Test to add a new car to the garage and verify its details.")
    @Owner("QA Student")
    @Link(name = "Task Link", url = "https://example.com/task-id")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddCarToGarage() {
        Allure.step("Step 1: Log in as a guest", () -> {
            homePage.clickGuestLogin();
        });

        Allure.step("Step 2: Verify the transition to the Garage page", () -> {
            webdriver().shouldHave(WebDriverConditions.url("https://qauto.forstudy.space/panel/garage"));
        });

        Allure.step("Step 3-7: Add a car to the garage", () -> {
            garagePage.clickAddCar();
            garagePage.selectBrand("Audi");
            garagePage.selectModel("TT");
            garagePage.enterMileage("20");
            garagePage.clickSaveCar();
        });

        Allure.step("Step 8: Verify the added car details", () -> {
            garagePage.getAddedCarName()
                    .shouldBe(visible)
                    .shouldHave(text("Audi TT"));

            garagePage.getCurrentMileageDate()
                    .shouldHave(text("2024"));

            garagePage.getMileageValue()
                    .shouldHave(value("20"));

            garagePage.getCarLogoImage()
                    .shouldBe(visible)
                    .shouldHave(attributeMatching("src", ".*audi\\.png$"));
        });
    }
}
