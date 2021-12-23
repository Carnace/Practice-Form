package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import pages.PracticeFormPage;
import service.LoginConfig;

import static java.lang.String.format;

public class BaseTest {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;

        LoginConfig сonfig = ConfigFactory.create(LoginConfig.class);

        String login = сonfig.login();
        String password = сonfig.password();
        String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");
        Configuration.remote = format("https://%s:%s@%s", login, password, url);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
