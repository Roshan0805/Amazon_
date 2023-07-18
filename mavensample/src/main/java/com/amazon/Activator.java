package com.amazon;

import com.amazon.view.AuthenticationView;

public class Activator {
    public static void main(String[] args) {
        AuthenticationView.getInstance().displayMenu();
    }
}
