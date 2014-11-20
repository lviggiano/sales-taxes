package com.acme.sales.taxes;

import java.math.BigDecimal;

public abstract class TaxExemptProduct extends Product {
    public TaxExemptProduct(int quantity, String description, double price) {
        super(quantity, description, price);
    }

    @Override
    public BigDecimal taxRate() {
        return new BigDecimal(0);
    }
}
