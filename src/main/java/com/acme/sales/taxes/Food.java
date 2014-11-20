package com.acme.sales.taxes;

public class Food extends TaxExemptProduct {
    public Food(int quantity, String description, double price) {
        super(quantity, description, price);
    }
}
