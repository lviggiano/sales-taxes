package com.acme.sales.taxes;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.RoundingMode.UP;

public class Product {
    private final BigDecimal quantity;
    private final String description;
    private final BigDecimal price;

    public Product(int quantity, String description, double price) {
        this(new BigDecimal(quantity), description, new BigDecimal(price));
    }

    private Product(BigDecimal quantity, String description, BigDecimal price) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
    }

    Product(Product wrapped) {
        this(wrapped.quantity, wrapped.description, wrapped.price);
    }

    protected BigDecimal taxRate() {
        return new BigDecimal(10);
    }

    public BigDecimal totalPrice() {
        return price.multiply(quantity).add(salesTax());
    }

    public BigDecimal salesTax() {
        BigDecimal tax = price.multiply(quantity)
                              .multiply(taxRate())
                              .divide(new BigDecimal(100), 2, ROUND_HALF_UP);

        BigDecimal rounding = new BigDecimal("0.05");
        return round(tax, rounding, UP);
    }

    static BigDecimal round(BigDecimal value, BigDecimal rounding, RoundingMode mode) {
        return value.divide(rounding, 0, mode).multiply(rounding);
    }

    void print(PrintWriter printer) {
        printer.printf("%d %s: %3.2f%n", quantity.intValue(), description, totalPrice());
    }
}
