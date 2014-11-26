package com.acme.sales.taxes;

import java.math.BigDecimal;

public abstract class TaxExemptProduct extends Product {
    public TaxExemptProduct(int quantity, String description, double price) {
        super(new BigDecimal(quantity), description, new BigDecimal(price), new BigDecimal(0.0));
    }
}
