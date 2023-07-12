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
    private static final ScannerInstance SCANNER_REFERENCE = new ScannerInstance();
    private final Scanner SCANNER = new Scanner(System.in);
    protected final String getString = SCANNER.nextLine().trim();

    /**
     * <p>
     * References the single instance of scanner instance is created ;
     * </p>
     *
     * @return Represents the {@link Scanner}
     */
    public static ScannerInstance getInstance() {
        return SCANNER_REFERENCE;
    }
}
