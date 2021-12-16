package tests;

import com.codeborne.selenide.Configuration;
import models.Student;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
@Tag("properties")
public class PracticeFormTests extends BaseTest {


    Student student = new Student();
    String nowDate = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(new Date());

    @Test
    @DisplayName("Отправка и проверка формы")
    void checkDataInForm() {
        practiceFormPage.openPage()
                .fullInTheForm(student)
                .calendar.setDate(student.getDay(), student.getMonth(), student.getYear());
        practiceFormPage.sendForm()
                .checkForm(student);
    }

    @Test
    @DisplayName("Проверка, что после отправки форма пустая")
    void checkClearForm() {
        practiceFormPage.openPage()
                .fullInTheForm(student)
                .calendar.setDate(student.getDay(), student.getMonth(), student.getYear());
        practiceFormPage.sendForm()
                .checkDataFormAfterClose(nowDate);
    }
}
