package com.acme.sales.taxes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptTest {

    private @Mock PrintWriter printer;
    private @Mock Product product1;
    private @Mock Product product2;

    @Test
    public void shouldPrintEveryProduct() {
        List<Product> products = Arrays.asList(product1, product2);
        BigDecimal salesTaxes = new BigDecimal(10);
        BigDecimal total = new BigDecimal(20);

        Receipt receipt = new Receipt(printer, products, salesTaxes, total);

        receipt.print();

        InOrder inOrder = inOrder(product1, product2, printer);
        inOrder.verify(product1).print(same(printer));
        inOrder.verify(product2).print(same(printer));
        inOrder.verify(printer).printf(anyString(), same(salesTaxes));
        inOrder.verify(printer).printf(anyString(), same(total));
    }
}
