package com.acme.sales.taxes;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.RoundingMode.UP;

public class Product {
    final BigDecimal quantity;
    final String description;
    final BigDecimal price;
    final BigDecimal taxRate;

    Product(BigDecimal quantity, String description, BigDecimal price, BigDecimal taxRate) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.taxRate = taxRate;
    }

    public Product(int quantity, String description, double price) {
        this(new BigDecimal(quantity), description, new BigDecimal(price), new BigDecimal(10.0));
    }

    protected BigDecimal taxRate() {
        return taxRate;
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
