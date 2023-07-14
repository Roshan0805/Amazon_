package com.amazon;

import com.amazon.view.AuthenticationView;

/**
 * <p>
 *     Represents the activation of an application
 * </p>
 */
public class Activator {

    /**
     * <p>
     *     Represents the starting point the program
     * </p>
     * @param args Represents the command line arguments
     */
    public static void main(final String[] args) {
        AuthenticationView.getInstance().displayMenu();
    }
}
