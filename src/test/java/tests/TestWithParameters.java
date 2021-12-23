package tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import service.LoginConfig;

@Tag("owner")
public class TestWithParameters extends BaseTest{
    public LoginConfig loginConfig = ConfigFactory.create(LoginConfig.class);

    String address = System.getProperty("url");

    @Test
    @DisplayName("Проверка авторизации")
    void checkAuthorization(){
        String login = loginConfig.loginFromTest();
        String password = loginConfig.passwordFromTest();

        loginPage.openPage(address)
                .authorization(login,password)
                .checkAuthorizationUserName(login);
    }


}
