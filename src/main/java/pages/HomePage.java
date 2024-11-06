package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Guest log in']")
    private WebElement guestLoginButton;

    public void clickGuestLogin() {
        wait.until(ExpectedConditions.visibilityOf(guestLoginButton));
        wait.until(ExpectedConditions.elementToBeClickable(guestLoginButton));
        guestLoginButton.click();
    }
}
