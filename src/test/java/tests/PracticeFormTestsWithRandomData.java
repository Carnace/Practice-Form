package tests;

import com.github.javafaker.Faker;
import models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Tag("properties")
public class PracticeFormTestsWithRandomData extends BaseTest {

    Faker faker = new Faker(new Locale("en"));
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String mobile = faker.number().digits(10);
    String streetAddress = faker.address().streetAddress();

    Student student = new Student().setFirstName(firstName).setLastName(lastName).setEmail(email).setMobile(mobile).setCurrentAddress(streetAddress);
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
}
