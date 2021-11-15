package tests;

import com.codeborne.selenide.Configuration;
import models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends BaseTest{


    Student student = new Student();
    String nowDate = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(new Date());

    @Test
    void checkDataInForm() {
        practiceFormPage.openPage()
                .fullInTheForm(student.getFirstName(),student.getLastName(),student.getEmail(),student.getMobile(),student.getSubjects(),student.getCurrentAddress())
                .calendar.setDate(student.getDay(),student.getMonth(),student.getYear());
        practiceFormPage.sendForm()
                .checkForm(student.getFullName(),student.getEmail(),student.getMobile(),student.getSubjects(),student.getDateOfBirth(), student.getCurrentAddress());
    }

    @Test
    void checkClearForm() {
        practiceFormPage.openPage()
                .fullInTheForm(student.getFirstName(),student.getLastName(),student.getEmail(),student.getMobile(),student.getSubjects(),student.getCurrentAddress())
                .calendar.setDate(student.getDay(),student.getMonth(),student.getYear());
        practiceFormPage.sendForm()
                .checkDataFormAfterClose(nowDate);
    }
}
