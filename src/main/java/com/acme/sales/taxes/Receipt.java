package com.acme.sales.taxes;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    private final PrintWriter printer;
    private final List<Product> products;
    private final BigDecimal salesTaxes;
    private final BigDecimal total;

    public Receipt(PrintWriter printer, List<Product> products, BigDecimal salesTaxes, BigDecimal total) {
        this.printer = printer;
        this.products = products;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public void print() {
        products.stream().forEach(p -> p.print(printer));
        printer.printf("Sales Taxes: %3.2f%n", salesTaxes);
        printer.printf("Total: %3.2f%n", total);
        printer.flush();
    }
}
