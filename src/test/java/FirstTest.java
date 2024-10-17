import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;


public class FirstTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {

            driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");


            WebElement guestLoginButton = driver.findElement(By.xpath("//button[contains(text(),'Guest log in')]"));
            guestLoginButton.click();


            Thread.sleep(10000);


            List<WebElement> borderMenuItems = driver.findElements(By.cssSelector(".border-menu-item"));


            for (WebElement item : borderMenuItems) {
                System.out.println(item.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

//            driver.quit();
        }
    }
}
