package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    private SelenideElement inputDateOfBirth = $("#dateOfBirthInput");

    SelenideElement btnMonth = $(".react-datepicker__month-select");
    SelenideElement btnYear = $(".react-datepicker__year-select");
    ElementsCollection btnDay = $$("[role='option']");

    public void setDate(String day, String month, String year) {
        inputDateOfBirth.click();
        btnMonth.selectOption(month);
        btnYear.selectOption(year);
        btnDay.find(Condition.text(day)).click();
    }
}
