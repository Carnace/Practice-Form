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

    @Test
    @DisplayName("Проверка авторизации")
    void checkAuthorization(){
        String login = loginConfig.login();
        String password = loginConfig.password();
        String address = System.getProperty("url","https://demoqa.com/login");

        loginPage.openPage(address)
                .authorization(login,password)
                .checkAuthorizationUserName(login);
    }


}
