package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideGaragePage {

    public void clickAddCar() {
        Selenide.$("button.btn.btn-primary").click();
    }

    public void selectBrand(String brand) {
        Selenide.$("#addCarBrand").selectOption(brand);
    }

    public void selectModel(String model) {
        Selenide.$("#addCarModel").selectOption(model);
    }

    public void enterMileage(String mileage) {
        Selenide.$("#addCarMileage").setValue(mileage);
    }

    public void clickSaveCar() {
        Selenide.$("//button[text()='Add']").click();
    }

    public SelenideElement getAddedCarName() {
        return Selenide.$("//div[contains(@class, 'car-name') and contains(text(), 'Audi TT')]");
    }

    public SelenideElement getCurrentMileageDate() {
        return Selenide.$("p.car_update-mileage");
    }

    public SelenideElement getMileageValue() {
        return Selenide.$("input[data-testid='miles']");
    }

    public SelenideElement getCarLogoImage() {
        return Selenide.$(".car_logo img");
    }
}
