import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.ArrayList;
import java.util.List;

public class SocialMediaTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
    }

    @Test
    public void testSocialMediaLinks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Перевіряємо, чи є `.social-block` на сторінці за допомогою JavaScript
        boolean socialBlockExists = (Boolean) js.executeScript(
                "return document.querySelector('.social-block') !== null;"
        );
        Assert.assertTrue(socialBlockExists, "Social media block не знайдений на сторінці за допомогою JavaScript.");

        // Прокручуємо вниз для видимості елемента
        js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

        // Шукаємо `.social-block` через XPath для надійності
        WebElement socialMediaBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//*[@class='social-block']")));

        // Перевірка кількості іконок соцмереж
        List<WebElement> socialIcons = socialMediaBlock.findElements(By.tagName("a"));
        Assert.assertEquals(socialIcons.size(), 5, "Social network block doesn’t contain 5 items");

        // URL-адреси для перевірки
        List<String> expectedUrls = List.of(
                "https://facebook.com/",
                "https://twitter.com/",
                "https://instagram.com/",
                "https://linkedin.com/",
                "https://youtube.com/"
        );

        for (int i = 0; i < socialIcons.size(); i++) {
            WebElement icon = socialIcons.get(i);
            js.executeScript("arguments[0].click();", icon); // Клік через JavaScriptExecutor

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            Assert.assertTrue(tabs.size() > 1, "New tab did not open after clicking");

            // Переключення на нову вкладку та перевірка URL
            driver.switchTo().window(tabs.get(1));
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrls.get(i)), "Incorrect URL of Social network");

            // Закриття вкладки та повернення на основну
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
