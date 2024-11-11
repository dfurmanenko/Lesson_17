package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GaragePage {
    WebDriver driver;

    @FindBy(css = "button.btn.btn-primary")
    WebElement addCarButton;

    @FindBy(css = "select#addCarBrand")

    WebElement brandDropdown;

    @FindBy(css = "select#addCarModel")
    WebElement modelDropdown;

    @FindBy(css = "input#addCarMileage")
    WebElement mileageInput;

    @FindBy(css = "button[data-testid='save-car-button']")
    WebElement saveCarButton;

    @FindBy(xpath = "//div[contains(@class, 'car-name') and contains(text(), 'Audi TT')]")
    WebElement addedCarName;

    @FindBy(css = "p.car_update-mileage")
    WebElement mileageParagraph;

    @FindBy(css = "input[data-testid='miles']")
    WebElement mileageValue;

    @FindBy(css = ".car_logo img")
    WebElement carLogoImage;

    public GaragePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddCar() {
        addCarButton.click();
    }

    public void selectBrand(String brand) {
        new Select(brandDropdown).selectByVisibleText(brand);
    }

    public void selectModel(String model) {
        new Select(modelDropdown).selectByVisibleText(model);
    }

    public void enterMileage(String mileage) {
        mileageInput.clear();
        mileageInput.sendKeys(mileage);
    }

    public void clickSaveCar() {
        saveCarButton.click();
    }

    public boolean isCarAddedDisplayed() {
        return addedCarName.isDisplayed();
    }

    public String getCurrentMileageDate() {
        return mileageParagraph.getText();
    }

    public String getMileageValue() {
        return mileageValue.getAttribute("value");
    }

    public boolean isCarLogoDisplayed() {
        return carLogoImage.isDisplayed();
    }

    public String getCarLogoSrc() {
        return carLogoImage.getAttribute("src");
    }
}
