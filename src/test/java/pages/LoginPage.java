package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement
            formTitle = $(".main-header"),
            inputUserName = $("#userName"),
            inputPassword = $("#password"),
            btnLogin = $("#login"),
            textUserName = $("#userName-value");

    private final String FORM_TITLE = "Login";

    @Step("Открытие страницы login")
    public LoginPage openPage(String url){
        open(url);
        formTitle.shouldHave(Condition.text(FORM_TITLE));

        return this;
    }

    @Step("Авторизация")
    public LoginPage authorization(String userName,String userPassword){
        inputUserName.setValue(userName);
        inputPassword.setValue(userPassword);
        btnLogin.click();

        return this;
    }

    @Step("Проверка, что пользователь авторизован")
    public LoginPage checkAuthorizationUserName(String userName){
        textUserName.shouldHave(Condition.text(userName));

        return this;
    }


}
