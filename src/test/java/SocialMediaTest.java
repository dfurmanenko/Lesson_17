import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
    }

    @Test
    public void testSocialMediaLinks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        boolean socialBlockVisible = wait.until(driver -> {
            try {
                return driver.findElement(By.cssSelector(".social-block")).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        });

        Assert.assertTrue(socialBlockVisible, "Social media block не знайдений на сторінці навіть після прокручування.");


        WebElement socialMediaBlock = driver.findElement(By.cssSelector(".social-block"));


        List<WebElement> socialIcons = socialMediaBlock.findElements(By.tagName("a"));
        Assert.assertEquals(socialIcons.size(), 5, "Social network block doesn’t contain 5 items");


        List<String> expectedUrls = List.of(
                "https://facebook.com/",
                "https://twitter.com/",
                "https://instagram.com/",
                "https://linkedin.com/",
                "https://youtube.com/"
        );

        for (int i = 0; i < socialIcons.size(); i++) {
            WebElement icon = socialIcons.get(i);
            js.executeScript("arguments[0].click();", icon);

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertTrue(tabs.size() > 1, "New tab did not open after clicking");


            driver.switchTo().window(tabs.get(1));
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrls.get(i)), "Incorrect URL of Social network");


            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
