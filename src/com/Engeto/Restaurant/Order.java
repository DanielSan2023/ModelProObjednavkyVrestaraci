package com.Engeto.Restaurant;


import java.text.SimpleDateFormat;
import java.util.*;

public class Order {
    private static Map<Integer, List<Dish>> orders = new HashMap<>();

    private  int orderId;
    private  int tableNumber;
    private  Dish dish;
    private  Date orderTime;
    private Date fulfilmentTime;
    private  boolean isPaid;

    private static int nextOrderId = 1;

    public Order(int tableNumber, int dishId) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.dish = CookBook.getDishById(dishId);

        if (this.dish == null) {
            throw new IllegalArgumentException("dishId cannot be null");
        }
        this.orderTime = new Date();
        this.isPaid = false;

        // Přidání objednávky do kolekce "orders" s číslem stolu a objektem třídy Dish
        if (!orders.containsKey(tableNumber)) {
            orders.put(tableNumber, new ArrayList<>());
        }
        orders.get(tableNumber).add(this.dish);
    }




    public List<Dish> getOrdersForTable(int tableNumber) {
        return orders.get(tableNumber);
    }


    public void setFulfilmentTime(Date fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public  int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getDishId() {
        return dish.getIdDish();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Date getFulfilmentTime() {
        return fulfilmentTime;
    }

    public boolean isPaid() {
        return isPaid;
    }


    public static void listOrderDetails(int tableNumber) {
        List<Dish> dishes = orders.get(tableNumber);

        if (dishes == null) {
            System.out.println("Stůl " + tableNumber + " nemá žádné objednávky.");
            return;
        }

        System.out.println("Objednávky pro stůl " + tableNumber + ":");

        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.println("Objednávka " + (i + 1) + ":");
            System.out.println(" - Název jídla: " + dish.getTitle());
            System.out.println(" - Cena: " + dish.getPrice() + " Kč");
//            System.out.println(" - Počet kusů v objednávce: " + dish.getQuantity());

//            System.out.println(" - Čas zadání objednávky: " + dish.getOrderTime());
//            if (dish.getFulfilmentTime() != null) {
//                System.out.println(" - Čas vyřízení objednávky: " + dish.getFulfilmentTime());
//            }
//            System.out.println(" - Order ID: " + dish.getOrderId());
//            System.out.println(" - Stav platby: " + (dish.isPaid() ? "Zaplaceno" : "Nezaplaceno"));
//            System.out.println();
        }
    }




    @Override
    public String toString() {
        return "Order{" +
                "tableNumber=" + tableNumber +
                ", dish=" + dish +
                ", orderTime=" + orderTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", isPaid=" + isPaid +
                '}';
    }
}
