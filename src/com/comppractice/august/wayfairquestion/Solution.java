package com.comppractice.august.wayfairquestion;

import java.io.*;
import java.util.*;


interface IOrder {
    void setName(String name);

    String getName();

    void setPrice(int price);

    int getPrice();
}

interface IOrderSystem {
    void addToCart(IOrder order);

    void removeFromCart(IOrder order);

    int calculateTotalAmount();

    Map<String, Integer> categoryDiscounts();

    Map<String, Integer> cartItems();
}

class Order implements IOrder {
    private String name;
    private int price;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}

class OrderSystem implements IOrderSystem {
    Map<String, Integer> itemPrices = new LinkedHashMap<>();
    Map<String, Integer> cartItems = new LinkedHashMap<>();

    @Override
    public void addToCart(IOrder order) {
        itemPrices.put(order.getName(), order.getPrice());
        cartItems.put(order.getName(), cartItems.getOrDefault(order.getName(), 0) + 1);
    }

    @Override
    public void removeFromCart(IOrder order) {
        if (cartItems.getOrDefault(order.getName(), 0) > 0) {
            cartItems.put(order.getName(), cartItems.getOrDefault(order.getName(), 0) - 1);
        }
    }

    @Override
    public Map<String, Integer> cartItems() {
        return cartItems;
    }

    @Override
    public int calculateTotalAmount() {
        return this.cartItems.entrySet().stream()
                .map(entry -> getDiscountedPrice(itemPrices.get(entry.getKey())) * entry.getValue()).mapToInt(x -> x)
                .sum();
    }

    @Override
    public Map<String, Integer> categoryDiscounts() {
        Map<String, Integer> categoryDiscounts = new TreeMap<>();
        for (Map.Entry<String, Integer> nameQuantity : this.cartItems.entrySet()) {
            int itemPrice = itemPrices.get(nameQuantity.getKey());
            int totalDiscount = nameQuantity.getValue() * getDiscount(itemPrice);
            categoryDiscounts.put(getCategory(itemPrice),
                    categoryDiscounts.getOrDefault(getCategory(itemPrice), 0) + totalDiscount);
        }
        return categoryDiscounts;
    }

    private int getDiscount(int itemPrice) {
        String category = getCategory(itemPrice);
        if (category.equals("Cheap")) {
            return (int) (itemPrice * 0.1);
        } else if (category.equals("Moderate")) {
            return (int) (itemPrice * 0.2);
        } else {
            return (int) (itemPrice * 0.3);
        }
    }

    private int getDiscountedPrice(int itemPrice) {
        return itemPrice - getDiscount(itemPrice);
    }

    private String getCategory(int itemPrice) {
        if (itemPrice <= 10) {
            return "Cheap";
        } else if (itemPrice <= 20) {
            return "Moderate";
        } else {
            return "Expensive";
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter textWriter = new PrintWriter(System.out);

        IOrderSystem orderSystem = new OrderSystem();
        int oCount = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= oCount; i++) {
            String[] a = br.readLine().trim().split(" ");
            IOrder e = new Order();
            e.setName(a[0]);
            e.setPrice(Integer.parseInt(a[1]));
            orderSystem.addToCart(e);
        }
        int totalAmount = orderSystem.calculateTotalAmount();
        textWriter.println("Total Amount: " + totalAmount);

        Map<String, Integer> categoryDiscounts = orderSystem.categoryDiscounts();
        for (Map.Entry<String, Integer> entry : categoryDiscounts.entrySet()) {
            if (entry.getValue() > 0) {
                textWriter.println(entry.getKey() + " Category Discount: " + entry.getValue());
            }
        }

        Map<String, Integer> cartItems = orderSystem.cartItems();
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            textWriter.println(entry.getKey() + " (" + entry.getValue() + " items)");
        }

        textWriter.flush();
        textWriter.close();
    }
}