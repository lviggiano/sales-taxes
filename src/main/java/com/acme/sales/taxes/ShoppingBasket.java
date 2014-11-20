package com.acme.sales.taxes;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ShoppingBasket {
    private List<Product> products = new ArrayList<>();

    public ShoppingBasket add(Product product) {
        products.add(product);
        return this;
    }

    public Receipt purchase(PrintWriter printer) {
        BigDecimal total = sum(products, Product::totalPrice);
        BigDecimal salesTaxes = sum(products, Product::salesTax);
        return new Receipt(printer, products, salesTaxes, total);
    }

    private BigDecimal sum(List<Product> products, Function<Product, BigDecimal> function) {
        return products.stream()
                    .map(function)
                    .reduce(new BigDecimal(0), (a, b) -> a.add(b));
    }

    public static ShoppingBasket get() {
        return new ShoppingBasket();
    }
}
