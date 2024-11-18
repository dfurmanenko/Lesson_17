package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class DownloadInstructionsTest {
    WebDriver driver;

    @FindBy(xpath = "//button[text()='Guest log in']")
    WebElement guestLoginButton;

    @FindBy(xpath = "//a[@routerlink='instructions' and contains(., 'Instructions')]")
    WebElement instructionsMenu;

    @FindBy(xpath = "//a[@download and contains(@href, 'Front windshield wipers on Audi TT.pdf')]")
    WebElement pdfFileLink;

    @BeforeMethod
    public void setUp() {

        String downloadDir = Paths.get(System.getProperty("user.dir")).toString();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        // Налаштовуємо автоматичне завантаження в директорію проекту
        options.addArguments("user-data-dir=" + downloadDir);
        options.addArguments("download.default_directory=" + downloadDir);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
    }

    @Test
    public void testDownloadFile() throws InterruptedException {
        // Відкрити сторінку
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        // Очікування для кнопки "Guest log in"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(guestLoginButton));

        // Клік на кнопку
        guestLoginButton.click();

        // Перевірка URL
        String expectedUrl = "https://qauto.forstudy.space/panel/garage";
        String actualUrl = driver.getCurrentUrl();
        actualUrl = actualUrl.replaceAll("https://.*@","https://"); // Очищаємо домен з обліковими даними
        Assert.assertEquals(actualUrl, expectedUrl, "URL після входу не збігається з очікуваним!");

        // Клік на "Instructions" у меню
        wait.until(ExpectedConditions.elementToBeClickable(instructionsMenu));
        instructionsMenu.click();

        // Очікування появи посилання на файл
        wait.until(ExpectedConditions.visibilityOf(pdfFileLink));
        String fileUrl = pdfFileLink.getAttribute("href");

        // Завантаження файлу
        String downloadDir = Paths.get(System.getProperty("user.dir")).toString(); // Корінь проекту

        // Відкриття нового вікна
        String originalWindow = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0]);", fileUrl);

        // Перемикання на нову вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Очікування завантаження файлу з використанням циклу
        File downloadedFile = Paths.get(downloadDir, "Front windshield wipers on Audi TT.pdf").toFile();
        int retryCount = 0;
        boolean isFileDownloaded = false;

        while (retryCount < 10) { // Максимум 10 спроб
            if (downloadedFile.exists()) {
                isFileDownloaded = true;
                break;
            }
            Thread.sleep(1000); // Затримка 1 секунда
            retryCount++;
        }

        // Перевірка наявності файлу
        Assert.assertTrue(isFileDownloaded, "Файл не було завантажено!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
