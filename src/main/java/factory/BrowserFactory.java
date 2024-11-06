package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    public static WebDriver getChromeDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
