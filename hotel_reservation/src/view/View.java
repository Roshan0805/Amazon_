package view;

import controller.Controller;
import model.Admin;
import model.Suite;
import view.validation.Validation;

import java.util.Scanner;

public class View {

    private static final View VIEW = new View();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Validation VALIDATION = Validation.getInstance();
    private static final Controller CONTROLLER = Controller.getInstance();
    private static Long singleRoomId = 1L;
    private static Long doubleeRoomId = 1L;
    private static Long suiteRoomId = 1L;

    private View() {}

    public static View getInstance() {
        return VIEW;
    }
    private void adminLogin() {
        System.out.println("Enter the option\n1.signUp\t2.signIn\t3.exit");

        try {
            final int adminOption = Integer.parseInt(SCANNER.nextLine());

            switch (adminOption) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    SCANNER.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    adminLogin();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the option in number");
            adminLogin();
        }
    }

    private void signUp() {
        final Admin admin = new Admin();
        admin.setName(getName());
        admin.setEmail(getEmail());
        admin.setPassWord(getPassword());
        admin.setPhoneNumber(getPhoneNumber());

    }

    private void signIn() {
        if (CONTROLLER.signIn(getEmail(), getPassword())) {
            System.out.println("Sign in successful");
        }
        System.out.println("signIn unsuccessful");
    }

    private String getName() {
        System.out.println("Enter your name\t(want to exit press #)");
        final String name = SCANNER.nextLine();

        if(VALIDATION.isReturnToMenu(name)) {
            adminLogin();
        }

        return name;
    }

    private String getEmail() {
        System.out.println("Enter your email\t(want to exit press #)");
        final String email = SCANNER.nextLine();

        if(VALIDATION.isReturnToMenu(email)) {
            adminLogin();
        }
        if (VALIDATION.validateEmail(email)) {
            return email;
        }
        System.out.println("invalid email");

        return getEmail();
    }

    private String getPassword() {
        System.out.println("Enter your password\t(want to exit press #)");
        final String password = SCANNER.nextLine();

        if(VALIDATION.isReturnToMenu(password)) {
            adminLogin();
        }

        return password;
    }

    private String getPhoneNumber() {
        System.out.println("Enter your name\t(want to exit press #)");
        final String phoneNumber = SCANNER.nextLine();

        if(VALIDATION.isReturnToMenu(phoneNumber)) {
            adminLogin();
        }

        return phoneNumber;
    }

    private void add() {
        System.out.println("Enter the room type\n1.single room\n2.double room\n3.suite");
        try {
            final int roomType = Integer.parseInt(SCANNER.nextLine());

            switch(roomType) {
                case 1:
                    addSingleRoom();
                    break;
                case 2:
                    addDoubleRoom();
                    break;
                case 3:
                    addSuite();
                    break;
                case 4:
                    backToLogin();
                    break;
                default :
                    System.out.println("Enter the correct option");
                    add();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the option in numbers");
            add();
        }

    }

    private void backToLogin() {
        adminLogin();
    }

    private void addSuite() {
        final Suite suite = new Suite()
        CONTROLLER.addSuite()

    }

    private void addDoubleRoom() {
    }

    private void addSingleRoom() {
    }
}
