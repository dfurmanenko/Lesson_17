package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        driver.manage().window().maximize();
    }

    @Test
    public void testInvalidLoginPassword1111() {

        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signInButton.click();

        // Крок 3: Ввести email та password
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        emailInput.sendKeys("test@hillel.ua");
        passwordInput.sendKeys("1111");

        // Крок 4: Натиснути кнопку Login
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Перевірка: Відображення повідомлення про помилку
        WebElement errorMessage = driver.findElement(By.cssSelector(".alert-danger"));
        Assert.assertTrue(errorMessage.getText().contains("Wrong email or password"),
                "Помилка: Повідомлення про помилку не відображається або має неправильний текст");
    }

    @Test
    public void testInvalidLoginPassword1234() {
        // Крок 2: Натиснути на кнопку Sign In
        WebElement signInButton = driver.findElement(By.cssSelector("button[data-target='#sign-in-modal']"));
        signInButton.click();

        // Крок 3: Ввести email та password
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        emailInput.sendKeys("test@hillel.ua");
        passwordInput.sendKeys("1234");

        // Крок 4: Натиснути кнопку Login
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Перевірка: Відображення повідомлення про помилку
        WebElement errorMessage = driver.findElement(By.cssSelector(".alert-danger"));
        Assert.assertTrue(errorMessage.getText().contains("Wrong email or password"),
                "Помилка: Повідомлення про помилку не відображається або має неправильний текст");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
