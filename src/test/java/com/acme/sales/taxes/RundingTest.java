package com.acme.sales.taxes;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.RoundingMode.UP;
import static org.junit.Assert.assertEquals;

public class RundingTest {

    @Test
    public void roundTest() {
        BigDecimal amount = new BigDecimal("0.05");
        assertEquals(new BigDecimal("1.15"), Product.round(new BigDecimal("1.12"), amount, UP));
        assertEquals(new BigDecimal("1.20"), Product.round(new BigDecimal("1.151"), amount, UP));
        assertEquals(new BigDecimal("1.15"), Product.round(new BigDecimal("1.15"), amount, UP));
        assertEquals(new BigDecimal("1.95"), Product.round(new BigDecimal("1.900001"), amount, UP));
        assertEquals(new BigDecimal("2.00"), Product.round(new BigDecimal("1.950001"), amount, UP));
    }
}
