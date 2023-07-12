package com.amazon.view.builder;

import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.ProductView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductBuilder extends ProductView {

    private static final ProductBuilder PRODUCT_BUILDER = new ProductBuilder();

    private ProductBuilder() {}

    public static ProductBuilder getInstance() {
        return PRODUCT_BUILDER;
    }

    public Product buildProduct(final Long userId) {
        final Product product = new Product();

        product.setCategory(getCategory(userId));
        product.setName(getName(userId));
        product.setDescription(getDescription(userId));
        product.setPrice(getPrice(userId));

        final Date date = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss E dd:MM:yyyy zz");
        final String time = formatter.format(date);

        product.setUpdatedTime(time);
        product.setAdminId(userId);

        return product;
    }


    /**
     * <p>
     * Gets the product category from the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represent {@link Product.Category}
     */
    private Product.Category getCategory(final Long userId) {
        System.out.println(String.join("", "Enter the product category\n1.mobile_phones\n",
                "2.footwear\n3.electronics\n4.clothing\n5.kitchen_appliances\n6.sports\n7.books\n8.toys\t",
                "(press # to return to product menu)"));

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (PRODUCT_VALIDATION.isReturnToMenu(userChoice)) {
                accessProduct(userId);
            }

            final int productChoice = Integer.parseInt(userChoice);

            final Product.Category category = Product.Category.getCategory(productChoice);

            if (null == category) {
                System.out.println("Enter a valid category");
            } else {
                return category;
            }

        } catch (IllegalArgumentException exception) {
            System.out.println("Enter a valid category");
        }

        return getCategory(userId);
    }

    /**
     * <p>
     * Gets the product name from user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} name
     */
    private String getName(final Long userId) {
        try {
            System.out.println("Enter the product name\t(press # for back to menu)");
            final String productName = SCANNER.nextLine();

            if (PRODUCT_VALIDATION.isReturnToMenu(productName)) {
                accessProduct(userId);
            }

            return productName;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getName(userId);
    }


    /**
     * <p>
     * Get the product description from the user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} description
     */
    private String getDescription(final Long userId) {
        try {
            System.out.println("Enter the product description\t(press # for back to menu)");
            final String description = SCANNER.nextLine();

            if (PRODUCT_VALIDATION.isReturnToMenu(description)) {
                accessProduct(userId);
            }

            return description;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getDescription(userId);
    }

    /**
     * <p>
     * Gets and validate price that is entered by the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represents price value entered by user
     */
    private double getPrice(final Long userId) {
        System.out.println("Enter the Product price\t(press # for back to product menu)");

        try {
            final double productPrice = Double.parseDouble(SCANNER.nextLine().trim());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(productPrice))) {
                accessProduct(userId);
            }
            return productPrice;
        } catch (final NumberFormatException Exception) {
            System.out.println("Enter the value is number");
        }
        System.out.println("Do you want to enter price again press yes(y) else press any letter key");

        try {
            if ("y".equalsIgnoreCase(SCANNER.nextLine().trim())) {
                return getPrice(userId);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}
