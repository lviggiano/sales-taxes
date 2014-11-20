package com.acme.sales.taxes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintWriter;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketTest {

    private @Mock Product product1;
    private @Mock Product product2;
    private @Mock PrintWriter printer;

    @Test
    public void getShouldReturnNewInstance() {
        ShoppingBasket instance = ShoppingBasket.get();
        assertThat(instance, notNullValue());
    }

    @Test
    public void shouldProcessPurchaseOnAllProducts() {
        doReturn(new BigDecimal("10")).when(product1).totalPrice();
        doReturn(new BigDecimal("2")).when(product1).salesTax();

        doReturn(new BigDecimal("20")).when(product2).totalPrice();
        doReturn(new BigDecimal("4")).when(product2).salesTax();

        ShoppingBasket.get()
                .add(product1)
                .add(product2)
                .purchase(printer).print();

        verify(product1, times(1)).print(same(printer));
        verify(product2, times(1)).print(same(printer));

        verify(printer, times(1)).printf(anyString(), eq(new BigDecimal("6")));
        verify(printer, times(1)).printf(anyString(), eq(new BigDecimal("30")));

    }
}
