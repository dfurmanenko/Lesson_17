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

public class LogoDisplayTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Перевірка відображення логотипу на сторінці")
    public void testLogoDisplay() {

        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'header_logo')]")));

        if (logo.isDisplayed()) {
            System.out.println("Logo displayed");
        } else {
            System.out.println("Logo does not displayed");
        }

        Assert.assertTrue(logo.isDisplayed(), "Логотип не відображається на сторінці.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
