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
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/"); // Заміни на URL тестової сторінки
    }

    @Test
    public void testSocialMediaLinks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Перехід у фрейм, якщо блок соцмереж знаходиться всередині нього
        WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe.social-frame")));
        driver.switchTo().frame(frameElement);

        // Зчитування тайтлу фрейму для перевірки
        String frameTitle = (String) js.executeScript("return document.title;");
        System.out.println("Title of the iframe: " + frameTitle);

        // Прокручуємо сторінку вниз, щоб завантажити блок соцмереж
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Перевірка на наявність блоку соцмереж
        boolean socialBlockVisible = wait.until(driver -> {
            try {
                return driver.findElement(By.cssSelector(".social-block")).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        });
        Assert.assertTrue(socialBlockVisible, "Social media block не знайдений на сторінці навіть після прокручування.");

        // Пошук блоку соцмереж
        WebElement socialMediaBlock = driver.findElement(By.cssSelector(".social-block"));

        // Перевірка кількості іконок соцмереж
        List<WebElement> socialIcons = socialMediaBlock.findElements(By.tagName("a"));
        Assert.assertEquals(socialIcons.size(), 5, "Social network block doesn’t contain 5 items");

        // Очікувані URL-адреси для перевірки
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

            // Перемикання на нову вкладку та перевірка URL
            driver.switchTo().window(tabs.get(1));
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrls.get(i)), "Incorrect URL of Social network");

            // Закриття вкладки та повернення на основну
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }

        // Повернення до основного контенту після завершення тесту
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
