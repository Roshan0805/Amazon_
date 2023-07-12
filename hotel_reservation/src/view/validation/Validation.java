package view.validation;

public class Validation {

    private static final Validation VALIDATION = new Validation();

    private Validation() {}

    public static Validation getInstance() {
        return VALIDATION;
    }

    public boolean validateEmail(final String email) {
        return email.matches("^[a-zA-z]+[a-zA-Z0-9_.\\S]+@[a-z]{3,}\\.[a-z\\S]{2,3}$");
    }

    public boolean validatePhone(final String phoneNumber) {
        return phoneNumber.matches("^[6-9][0-9]{9}+$");
    }

    public boolean isReturnToMenu(final String value) {
        return ('#' == value.charAt(0));
    }
}
