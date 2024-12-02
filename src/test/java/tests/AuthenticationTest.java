import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.time.Duration;

public class AuthenticationTest {

    private final WebDriver driver;
    private static final String BASE_URL = "https://guest:welcome2qauto@qauto.forstudy.space/";

    public AuthenticationTest(WebDriver driver) {
        this.driver = driver;
    }

    @Factory
    public static Object[] createInstances() {
        return new Object[]{
                new AuthenticationTest(new ChromeDriver()),
                new AuthenticationTest(new FirefoxDriver())
        };
    }

    @Test
    public void testInvalidLoginShowsErrorMessage() {
        driver.get(BASE_URL);


        WebElement signInButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign In']")));
        signInButton.click();


        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        emailInput.sendKeys("test@hillel.ua");
        passwordInput.sendKeys("1111");


        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();


        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message")));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Wrong email or password";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Помилка: текст повідомлення не відповідає очікуваному.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
