package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.Student;
import pages.components.CalendarComponent;
import pages.components.CalendarComponent.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormPage {
    //Поля
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            inputFirstName = $("#firstName"),
            inputLastName = $("#lastName"),
            inputUserEmail = $("#userEmail"),
            radioBtnGenderOther = $("[for='gender-radio-3']"),
            inputMobile = $("#userNumber"),
            inputDateOfBirth = $("#dateOfBirthInput"),
            inputSubjects = $("#subjectsInput"),
            checkBoxHobbiesSports = $("[for='hobbies-checkbox-1']"),
            checkBoxHobbiesReading = $("[for='hobbies-checkbox-2']"),
            checkBoxHobbiesMusic = $("[for='hobbies-checkbox-3']"),
            btnSelectPicture = $("#uploadPicture"),
            inputCurrentAddress = $("#currentAddress"),
            btnDdlState = $("#state"),
            optionState = $x("//*[.='NCR']"),
            btnDdlCity = $("#city"),
            optionCity = $x("//*[.='Delhi']"),
            btnSubmit = $("#submit");
    private ElementsCollection optionSubjects = $$("#react-select-2-option-0");
    //Форма с введенными данными
    private ElementsCollection table = $$x("//tr/td");
    private String tableCell = ("./td[2]");
    private SelenideElement btnClose = $("#closeLargeModal");

    public CalendarComponent calendar = new CalendarComponent();

    private final String FORM_TITLE = "Student Registration Form";

    //Поиск ячейки в таблице
    public SelenideElement tableElement(String nameOfCollum) {
        SelenideElement se;
        return se = (table).find(Condition.text(nameOfCollum)).parent().$x(tableCell);
    }

    public PracticeFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }


    //Заполнение формы полностью
    public PracticeFormPage fullInTheForm(Student student) {
        inputFirstName.setValue(student.getFirstName());
        inputLastName.setValue(student.getLastName());
        inputUserEmail.setValue(student.getEmail());
        radioBtnGenderOther.click();
        inputMobile.setValue(student.getMobile());
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

        return this;
    }

    public PracticeFormPage sendForm() {
        btnSubmit.scrollIntoView(true).click();

        return this;
    }

    //проверка формы
    public PracticeFormPage checkForm(Student student) {
        tableElement("Student Name").shouldHave(Condition.text(student.getFullName()));
        tableElement("Student Email").shouldHave(Condition.text(student.getEmail()));
        tableElement("Gender").shouldHave(Condition.text("Other"));
        tableElement("Mobile").shouldHave(Condition.text(student.getMobile()));
        tableElement("Date of Birth").shouldHave(Condition.text(student.getDateOfBirth()));
        tableElement("Subjects").shouldHave(Condition.text(student.getSubjects()));
        tableElement("Hobbies").shouldHave(Condition.text("Music, Reading, Sports"));
        tableElement("Picture").shouldHave(Condition.text("cat.jpg"));
        tableElement("Address").shouldHave(Condition.text(student.getCurrentAddress()));
        tableElement("State and City").shouldHave(Condition.text("NCR Delhi"));

        return this;
    }

    //Проверка, что после закрытия форма пустая
    public PracticeFormPage checkDataFormAfterClose(String date) {
        btnClose.scrollIntoView(true).click();
        //Проверка, что после закрытия форма пустая
        inputFirstName.shouldBe(Condition.empty);
        inputLastName.shouldBe(Condition.empty);
        inputUserEmail.shouldBe(Condition.empty);
        inputMobile.shouldBe(Condition.empty);
        inputDateOfBirth.shouldHave(attribute("value", date));
        inputSubjects.shouldBe(Condition.empty);
        inputCurrentAddress.shouldBe(Condition.empty);
        btnDdlState.shouldHave(Condition.text("Select State"));
        btnDdlCity.shouldHave(Condition.text("Select City"));

        return this;
    }
}
