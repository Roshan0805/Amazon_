package com.amazon.activator;

import com.amazon.view.AuthenticationView;
import com.amazon.view.ScannerInstance;

/**
 * <p>
 * Represents the starting view
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonActivator {

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");
        AuthenticationView.getInstance().displayMenu();
    }
}
