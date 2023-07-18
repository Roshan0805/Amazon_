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
public class View {
    protected static final Scanner SCANNER = new Scanner(System.in);

    /**
     * <p>
     *     Represent the constant values of user entered value
     * </p>
     */
    protected enum UserChoice {

        ZERO(0),ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9);

        final int index;

        UserChoice(final int index) {
            this.index = index;
        }

        /**
         * <p>
         *     Represents value of user entered value
         * </p>
         * @param value Represents the value enter by the user
         * @return Represent the constant value corresponding to user enter value
         */
        public static UserChoice getValue(final int value) {
            switch (value) {
                case 1:
                    return ONE;
                case 2:
                    return TWO;
                case 3:
                    return THREE;
                case 4:
                    return FOUR;
                case 5:
                    return FIVE;
                case 6:
                    return SIX;
                case 7:
                    return SEVEN;
                case 8:
                    return EIGHT;
                case 9:
                    return NINE;
                default:
                    return ZERO;
            }
        }
    }
}
