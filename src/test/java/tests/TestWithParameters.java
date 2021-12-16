package tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import service.LoginConfig;


@Tag("Owner")
public class TestWithParameters extends BaseTest{

    LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);

    String login = loginConfig.login();
    String password = loginConfig.password();


    @Test
    @DisplayName("Проверка авторизации")
    void checkAuthorization(){
        loginPage.openPage()
                .authorization(login,password)
                .checkAuthorizationUserName(login);
    }
}
