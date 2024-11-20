package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class SelenideHomePage {

    public void clickGuestLogin() {
        Selenide.$("button:text('Guest log in')").click();
    }
}
