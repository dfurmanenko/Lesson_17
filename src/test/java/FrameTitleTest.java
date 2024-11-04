import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FrameTitleTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
    }

    @Test
    public void testFrameTitle() {

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src*='youtube.com']")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualTitle = (String) js.executeScript("return document.title;");


        Assert.assertTrue(actualTitle.contains("YouTube"), "Title doesn’t contain the expected phrase 'YouTube'");
        System.out.println("Title contains 'YouTube': " + actualTitle);


        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
