import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class QAutoTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
    }

    @Test
    public void pageTitleWaitTest() {
        wait.until(ExpectedConditions.titleIs("Hillel Qauto"));
        System.out.println("Title is correct: " + driver.getTitle());
    }

    @Test
    public void guestLoginClickTest() {
        WebElement guestLoginButton = driver.findElement(By.cssSelector("button[data-target='#login']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", guestLoginButton);
        System.out.println("Clicked on 'Guest log in' button using JavaScript.");
    }

    @Test
    public void addCarButtonClickableTest() {
        WebElement addCarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add_car")));
        System.out.println("Add car button is clickable.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
