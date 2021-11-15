package models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private String firstName = "Иван";
    private String lastName = "Смирнов";
    private String email = "aaa@aa.com";
    private String mobile = "1234567890";
    private String day = "22";
    private String month = "May";
    private String year = "1977";
    private String subjects = "History";
    private String currentAddress = "Немного текста";

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateOfBirth() {
        return day + " " + month + "," + year;
    }
}
