import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
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
    SelenideElement inputFirstName = $("#firstName");
    SelenideElement inputLastName = $("#lastName");
    SelenideElement inputUserEmail = $("#userEmail");
    SelenideElement radioBtnGenderOther = $("[for='gender-radio-3']");
    SelenideElement inputMobile = $("#userNumber");
    SelenideElement inputDateOfBirth = $("#dateOfBirthInput");
    SelenideElement btnMonth = $(".react-datepicker__month-select");
    SelenideElement btnYear = $(".react-datepicker__year-select");
    ElementsCollection btnDay = $$("[role='option']");
    SelenideElement inputSubjects = $("#subjectsInput");
    ElementsCollection optionSubjects = $$("#react-select-2-option-0");
    SelenideElement checkBoxHobbiesSports = $("[for='hobbies-checkbox-1']");
    SelenideElement checkBoxHobbiesReading = $("[for='hobbies-checkbox-2']");
    SelenideElement checkBoxHobbiesMusic = $("[for='hobbies-checkbox-3']");
    SelenideElement btnSelectPicture = $("#uploadPicture");
    SelenideElement inputCurrentAddress = $("#currentAddress");
    SelenideElement btnDdlState = $("#state");
    SelenideElement optionState = $x("//*[.='NCR']");
    SelenideElement btnDdlCity = $("#city");
    SelenideElement optionCity = $x("//*[.='Noida']");
    SelenideElement btnSubmit = $("#submit");
    //Форма с веденными данными
    ElementsCollection table = $$x ("//tr/td");
    String tableCell = ("./td[2]");
    SelenideElement btnClose = $("#closeLargeModal");

    Student student = new Student();
    String nowDate = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH).format(new Date());

    //Поиск ячейки в таблице
    public SelenideElement tableElement(String nameOfCollum) {
        SelenideElement se;
        //return se = (table).find(Condition.text(nameOfCollum)).parent().tableCell;
        return se = (table).find(Condition.text(nameOfCollum)).parent().$x(tableCell);

    }

    //Заполнение формы полностью
    public void fullInTheForm() {
        inputFirstName.setValue(student.getFirstName());
        inputLastName.setValue(student.getLastName());
        inputUserEmail.setValue(student.getEmail());
        radioBtnGenderOther.click();
        inputMobile.setValue(student.getMobile());
        inputDateOfBirth.click();
        btnMonth.selectOption(student.getMonth());
        btnYear.selectOption(student.getYear());
        btnDay.find(Condition.text(student.getDay())).click();
        inputSubjects.setValue(student.getSubjects());
        optionSubjects.find(Condition.text(student.getSubjects())).click();
        checkBoxHobbiesMusic.click();
        checkBoxHobbiesReading.click();
        checkBoxHobbiesSports.click();
        btnSelectPicture.uploadFromClasspath("cat.jpg");
        inputCurrentAddress.setValue(student.getCurrentAddress());
        btnDdlState.hover().click();
        optionState.click();
        btnDdlCity.hover().click();
        optionCity.scrollIntoView(true).click();
        btnSubmit.scrollIntoView(true).click();
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
        btnClose.scrollIntoView(true).click();
        //Проверка, что после закрытия форма пустая
        inputFirstName.shouldBe(Condition.empty);
        inputLastName.shouldBe(Condition.empty);
        inputUserEmail.shouldBe(Condition.empty);
        inputMobile.shouldBe(Condition.empty);
        inputDateOfBirth.shouldHave(attribute("value", nowDate));
        inputSubjects.shouldBe(Condition.empty);
        inputCurrentAddress.shouldBe(Condition.empty);
        btnDdlState.shouldHave(Condition.text("Select State"));
        btnDdlCity.shouldHave(Condition.text("Select City"));
    }
}
