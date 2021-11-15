package tests;

import com.github.javafaker.Faker;
import models.Student;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class PracticeFormTestsWithRandomData extends BaseTest {

    String subjects = "History";

    Faker faker = new Faker(new Locale("ru"));
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String mobile = faker.phoneNumber().phoneNumber();
    String streetAddress = faker.address().streetAddress();


    @Test
    void checkDataInForm() {
        practiceFormPage.openPage()
                .fullInTheForm(firstName, lastName, email, mobile, subjects, streetAddress)
                .sendForm();
    }
}
