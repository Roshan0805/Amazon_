package com.amazon.activator;

import com.amazon.model.User;
import com.amazon.view.ScannerInstance;
import com.amazon.view.UserView;

import java.util.Scanner;

/**
 * <p>
 * Represents the starting view
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonActivatorView extends ScannerInstance {

    public static void main(final String[] args) {

        final UserView userView = UserView.getInstance();
        final Scanner scanner = ScannerInstance.getScanner();

        System.out.println("Welcome to amazon");

        if (userView.userController.checkProductList()) {
            UserView.AUTHENTICATION_VIEW.displayMenu();
            System.out.println("Do you want to continue yes(y) else press n to exit");
            final String exitChoice = scanner.nextLine().trim();

            if (userView.USER_VALIDATION.toContinueValidation(exitChoice)) {
                UserView.AUTHENTICATION_VIEW.displayMenu();
            } else {
                scanner.close();
            }
        } else {
            System.out.println("Product list is empty please signUp to add some products");
            UserView.AUTHENTICATION_VIEW.displayMenu();
        }
    }
}
