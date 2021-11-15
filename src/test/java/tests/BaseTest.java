package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.PracticeFormPage;

public class BaseTest {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }
}
