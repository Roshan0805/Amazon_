package com.amazon.view;

import java.util.Scanner;

/**
 * <p>
 * Represents the common view of view package common properties of userView
 * and productView represented in this view
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ScannerInstance {
    private static final Scanner SCANNER_REFERENCE = new Scanner(System.in);
    protected final Scanner SCANNER = getScanner();

    /**
     * <p>
     * References the scanner instance creation, it refer the single instance of scanner;
     * </p>
     *
     * @return Represents the {@link Scanner}
     */
    public static Scanner getScanner() {
        return SCANNER_REFERENCE;
    }
}
