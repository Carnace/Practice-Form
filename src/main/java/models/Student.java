package models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Locale;

@Data
@Accessors(chain = true)
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String day = "22";
    private String month = "May";
    private String year = "1977";
    private String subjects = "History";
    private String currentAddress;

    public Student(typeOfData typeOfData) {
        switch (typeOfData) {
            case OTHERDATA:
                this.firstName = "Иван";
                this.lastName = "Смирнов";
                this.email = "aaa@aa.com";
                this.mobile = "1234567890";
                this.currentAddress = "Немного текста";
                break;
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateOfBirth() {
        return day + " " + month + "," + year;
    }

    public static enum typeOfData {
        RANDOMDATA,
        OTHERDATA
    }
}
