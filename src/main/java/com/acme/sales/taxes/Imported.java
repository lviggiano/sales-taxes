package com.acme.sales.taxes;

import java.math.BigDecimal;

public class Imported extends Product {
    public Imported(Product wrapped) {
        super(wrapped.quantity, wrapped.description, wrapped.price, new BigDecimal(5).add(wrapped.taxRate()));
    }
}
