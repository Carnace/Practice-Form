import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    //Поля
    String inputFirstName = ("#firstName");
    String inputLastName = ("#lastName");
    String inputUserEmail = ("#userEmail");
    String radioBtnGenderOther = ("[for='gender-radio-3']");
    String inputMobile = ("#userNumber");
    String inputDateOfBirth = ("#dateOfBirthInput");
    String btnMonth = (".react-datepicker__month-select");
    String btnYear = (".react-datepicker__year-select");
    String btnDay = ("[role='option']");
    String inputSubjects = ("#subjectsInput");
    String optionSubjects = ("#react-select-2-option-0");
    String checkBoxHobbiesSports = ("[for='hobbies-checkbox-1']");
    String checkBoxHobbiesReading = ("[for='hobbies-checkbox-2']");
    String checkBoxHobbiesMusic = ("[for='hobbies-checkbox-3']");
    String btnSelectPicture = ("#uploadPicture");
    String inputCurrentAddress = ("#currentAddress");
    String btnDdlState = ("#state");
    By optionState = new By.ByXPath("//*[.='NCR']");
    String btnDdlCity = ("#city");
    By optionCity = new By.ByXPath("//*[.='Noida']");
    String btnSubmit = ("#submit");
    //Форма с веденными данными
    By table = new By.ByXPath("//tr/td");
    By tableCell = new By.ByXPath("./td[2]");
    String btnClose = ("#closeLargeModal");

    Student student = new Student();
    String nowDate = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(new Date());

    //Поиск ячейки в таблице
    public SelenideElement tableElement(String nameOfCollum) {
        SelenideElement se;
        return se = $$(table).find(Condition.text(nameOfCollum)).parent().$(tableCell);
    }

    //Заполнение формы полностью
    public void fullInTheForm() {
        $(inputFirstName).setValue(student.getFirstName());
        $(inputLastName).setValue(student.getLastName());
        $(inputUserEmail).setValue(student.getEmail());
        $(radioBtnGenderOther).click();
        $(inputMobile).setValue(student.getMobile());
        $(inputDateOfBirth).click();
        $(btnMonth).selectOption(student.getMonth());
        $(btnYear).selectOption(student.getYear());
        $$(btnDay).find(Condition.text(student.getDay())).click();
        $(inputSubjects).setValue(student.getSubjects());
        $$(optionSubjects).find(Condition.text(student.getSubjects())).click();
        $(checkBoxHobbiesMusic).click();
        $(checkBoxHobbiesReading).click();
        $(checkBoxHobbiesSports).click();
        $(btnSelectPicture).uploadFromClasspath("cat.jpg");
        $(inputCurrentAddress).setValue(student.getCurrentAddress());
        $(btnDdlState).hover().click();
        $(optionState).click();
        $(btnDdlCity).hover().click();
        $(optionCity).scrollIntoView(true).click();
        $(btnSubmit).scrollIntoView(true).click();
    }

    //проверка формы
    public void checkForm() {
        tableElement("Student Name").shouldHave(Condition.text(student.getFullName()));
        tableElement("Student Email").shouldHave(Condition.text(student.getEmail()));
        tableElement("Gender").shouldHave(Condition.text("Other"));
        tableElement("Mobile").shouldHave(Condition.text(student.getMobile()));
        tableElement("Date of Birth").shouldHave(Condition.text(student.detDateOfBirth()));
        tableElement("Subjects").shouldHave(Condition.text(student.getSubjects()));
        tableElement("Hobbies").shouldHave(Condition.text("Music, Reading, Sports"));
        tableElement("Picture").shouldHave(Condition.text("cat.jpg"));
        tableElement("Address").shouldHave(Condition.text(student.getCurrentAddress()));
        tableElement("State and City").shouldHave(Condition.text("NCR Noida"));
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @BeforeEach
    void BeforeEach() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void checkDataInForm() {
        fullInTheForm();
        checkForm();
    }

    @Test
    void checkClearForm() {
        fullInTheForm();
        checkForm();
        $(btnClose).scrollIntoView(true).click();
        //Проверка, что после закрытия форма пустая
        $(inputFirstName).shouldBe(Condition.empty);
        $(inputLastName).shouldBe(Condition.empty);
        $(inputUserEmail).shouldBe(Condition.empty);
        $(inputMobile).shouldBe(Condition.empty);
        $(inputDateOfBirth).shouldHave(attribute("value", nowDate));
        $(inputSubjects).shouldBe(Condition.empty);
        $(inputCurrentAddress).shouldBe(Condition.empty);
        $(btnDdlState).shouldHave(Condition.text("Select State"));
        $(btnDdlCity).shouldHave(Condition.text("Select City"));
    }
}
