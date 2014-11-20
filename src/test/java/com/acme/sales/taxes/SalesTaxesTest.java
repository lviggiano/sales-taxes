package com.acme.sales.taxes;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SalesTaxesTest {

    @Test
    public void basicSalesTaxesShouldBeTenPecentByDefault() {
        Product item = new Product(1, "perfume", 5);
        assertThat(item.taxRate(), is(equalTo(new BigDecimal(10))));
    }

    @Test
    public void taxExemptItemsShouldHaveTaxRateEqualToZero() {
        TaxExemptProduct product = new Book(1, "A study in scarlet", 10.0);
        assertThat(product.taxRate(), equalTo(new BigDecimal(0)));
        assertThat(product.salesTax(), equalTo(new BigDecimal("0.00")));
        assertThat(product.totalPrice(), equalTo(new BigDecimal("10.00")));
    }

    @Test
    public void booksShouldBeExemptFromSalesTax() {
        Book book = new Book(1, "Lord of the rings", 10.0);
        assertThat(book, isA(TaxExemptProduct.class));
    }

    @Test
    public void foodShouldBeExemptFromSalesTax() {
        Food food = new Food(1, "Box of chocolates", 10.0);
        assertThat(food, isA(TaxExemptProduct.class));
    }

    @Test
    public void medicalProductShouldBeExemptFromSalesTax() {
        Medical medical = new Medical(1, "Aspirin", 10.0);
        assertThat(medical, isA(TaxExemptProduct.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void importedProductShouldNotBeTaxExempt() {
        TaxExemptProduct exempt = new Book(1, "The Da Vinci's Code", 10.0);
        Imported imported = new Imported(exempt);
        assertThat(imported, not(isA((Class) TaxExemptProduct.class)));
    }

    @Test
    public void importedExemptProductShouldHaveFivePecentTaxRate() {
        TaxExemptProduct exempt = new Book(1, "The Da Vinci's Code", 10.0);
        Imported importedExempt = new Imported(exempt);
        assertThat(importedExempt.taxRate(), equalTo(new BigDecimal(5)));
        assertThat(importedExempt.salesTax(), equalTo(new BigDecimal("0.50")));
        assertThat(importedExempt.totalPrice(), equalTo(new BigDecimal("10.50")));
    }

    @Test
    public void importedTaxableProductShouldHaveFifteenPercentTaxRate() {
        Product product = new Product(1, "Perfume", 20);
        Imported importedTaxable = new Imported(product);
        assertThat(importedTaxable.taxRate(), equalTo(new BigDecimal(15)));
        assertThat(importedTaxable.salesTax(), equalTo(new BigDecimal("3.00")));
        assertThat(importedTaxable.totalPrice(), equalTo(new BigDecimal("23.00")));
    }

    @Test
    public void shouldPrintTheProductAsPerSpecification() {
        Product product = new Imported(new Product(1, "Perfume", 20));
        StringWriter stringWriter = new StringWriter();
        product.print(new PrintWriter(stringWriter));
        assertThat(stringWriter.toString(), equalTo("1 Perfume: 23.00\n"));
    }

}
