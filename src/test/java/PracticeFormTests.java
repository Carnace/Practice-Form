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
    By inputFirstName = new By.ByCssSelector("#firstName");
    By inputLastName = new By.ByCssSelector("#lastName");
    By inputUserEmail = new By.ByCssSelector("#userEmail");
    By radioBtnGenderOther = new By.ByCssSelector("[for='gender-radio-3']");
    By inputMobile = new By.ByCssSelector("#userNumber");
    By inputDateOfBirth = new By.ByCssSelector("#dateOfBirthInput");
    By btnMonth = new By.ByCssSelector(".react-datepicker__month-select");
    By optionMonthOrYear = new By.ByCssSelector("option");
    By btnYear = new By.ByCssSelector(".react-datepicker__year-select");
    By btnDay = new By.ByCssSelector("[role='option']");
    By inputSubjects = new By.ByCssSelector("#subjectsInput");
    By optionSubjects = new By.ByCssSelector("#react-select-2-option-0");
    By checkBoxHobbiesSports = new By.ByCssSelector("[for='hobbies-checkbox-1']");
    By checkBoxHobbiesReading = new By.ByCssSelector("[for='hobbies-checkbox-2']");
    By checkBoxHobbiesMusic = new By.ByCssSelector("[for='hobbies-checkbox-3']");
    By btnSelectPicture = new By.ByCssSelector("#uploadPicture");
    By inputCurrentAddress = new By.ByCssSelector("#currentAddress");
    By btnDdlState = new By.ByCssSelector("#state");
    By optionState = new By.ByXPath("//*[.='NCR']");
    By btnDdlCity = new By.ByCssSelector("#city");
    By optionCity = new By.ByXPath("//*[.='Noida']");
    By btnSubmit = new By.ByCssSelector("#submit");

    //Форма с веденными данными
    By table = new By.ByXPath("//tr/td");
    By tableCell = new By.ByXPath("./td[2]");
    By btnClose = new By.ByCssSelector("#closeLargeModal");

    Student student = new Student();
    String nowDate = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(new Date());

    //Поиск ячейки в таблице
    public SelenideElement tableElement(String nameOfCollum) {
        SelenideElement se;
        return se = $$(table).find(Condition.text(nameOfCollum)).parent().$(tableCell);
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
    void checkFullParameters() {
        $(inputFirstName).setValue(student.getFirstName());
        $(inputLastName).setValue(student.getLastName());
        $(inputUserEmail).setValue(student.getEmail());
        $(radioBtnGenderOther).click();
        $(inputMobile).setValue(student.getMobile());
        $(inputDateOfBirth).click();
        $(btnMonth).click();
        $(btnMonth).$$(optionMonthOrYear).find(Condition.text(student.getMonth())).click();
        $(btnYear).click();
        $(btnYear).$$(optionMonthOrYear).find(Condition.text(student.getYear())).click();
        $$(btnDay).find(Condition.text(student.getDay())).click();
        $(inputSubjects).setValue(student.getSubjects());
        $$(optionSubjects).find(Condition.text(student.getSubjects())).click();
        $(checkBoxHobbiesMusic).click();
        $(checkBoxHobbiesReading).click();
        $(checkBoxHobbiesSports).click();
        $(btnSelectPicture).uploadFile(new File("src/test/resources/cat.jpg"));
        $(inputCurrentAddress).setValue(student.getCurrentAddress());
        $(btnDdlState).hover().click();
        $(optionState).click();
        $(btnDdlCity).hover().click();
        $(optionCity).scrollIntoView(true).click();
        $(btnSubmit).scrollIntoView(true).click();

        //проверка формы
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
