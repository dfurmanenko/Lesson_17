import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpButtonColorTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        // Встановлюємо "explicit wait" тривалістю 10 секунд
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Перевірка кольору фону кнопки 'Sign up'")
    public void testSignUpButtonColor() {

        WebElement signUpButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Sign up']")));

        String backgroundColor = signUpButton.getCssValue("background-color");


        String expectedColor = "rgba(2, 117, 216, 1)"; // #0275d8 у rgba форматі

        if (backgroundColor.equals(expectedColor)) {
            System.out.println("Background color of Sign up button is correct");
        } else {
            System.out.println("Background color of Sign up button is incorrect");
        }


        Assert.assertEquals(backgroundColor, expectedColor, "Колір фону кнопки 'Sign up' некоректний.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
