package com.acme.sales.taxes;

import org.junit.Test;

import java.io.PrintWriter;

public class MainTest {

    public static void main(String[] args) {
        PrintWriter printer = new PrintWriter(System.out);

        printer.println();
        printer.println("Output 1:");
        Receipt receipt = ShoppingBasket.get()
                .add(new Book(1, "book", 12.49))
                .add(new Product(1, "music CD", 14.99))
                .add(new Food(1, "chocolate bar", 0.85))
                .purchase(printer);
        receipt.print();

        printer.println();
        printer.println("Output 2:");
        ShoppingBasket.get()
                .add(new Imported(new Food(1, "imported box of chocolates", 10.00)))
                .add(new Imported(new Product(1, "imported bottle of perfume", 47.50)))
                .purchase(printer)
                .print();

        printer.println();
        printer.println("Output 3:");
        ShoppingBasket.get()
                .add(new Imported(new Product(1, "imported bottle of perfume", 27.99)))
                .add(new Product(1, "bottle of perfume", 18.99))
                .add(new Medical(1, "packet of headache pills", 9.75))
                .add(new Imported(new Food(1, "box of imported chocolates", 11.25)))
                .purchase(printer)
                .print();

        printer.println("\n");

    }

    @Test
    public void runMain() {
        main(new String[0]);
    }

}
