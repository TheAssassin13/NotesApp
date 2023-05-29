import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private final String registrationNumber;
    public String firstName;
    public String lastName;
    public String email;
    private String phoneNumber;
    private LocalDate birthdate;
    private final String password;

    public User(String registrationNumber, String firstName, String lastName, String email, String phoneNumber, LocalDate birthdate) {
        this.registrationNumber = registrationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.password = createPassword();
    }

    public String createPassword() {
        String password;
        password = String.valueOf(this.firstName.charAt(0)).toUpperCase();
        password += String.valueOf(this.lastName.charAt(0)).toLowerCase();
        for (int i = registrationNumber.length() - 1; i >= registrationNumber.length() - 6; i--) {
            password += String.valueOf(this.registrationNumber.charAt(i)).toLowerCase();
        }
        return password;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

