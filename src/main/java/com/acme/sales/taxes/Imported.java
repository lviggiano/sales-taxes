package com.acme.sales.taxes;

import java.math.BigDecimal;

public class Imported extends Product {
    private final Product product;

    public Imported(Product product) {
        super(product);
        this.product = product;
    }

    @Override
    public BigDecimal taxRate() {
        return new BigDecimal(5)
                .add(product.taxRate());
    }
}
