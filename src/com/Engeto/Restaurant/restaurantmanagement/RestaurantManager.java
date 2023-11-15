package com.Engeto.Restaurant.restaurantmanagement;

import com.Engeto.Restaurant.dishmanager.CookBook;
import com.Engeto.Restaurant.ordermanager.Order;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.Engeto.Restaurant.ordermanager.Order.formatTableNumber;

public class RestaurantManager {
    Order order = new Order();


    public void countUnfinishedOrders() {
        int unfinishedOrders = 0;
        for (Order order : order.getOrders().values()) {
            if (!order.isPaid() && order.getFulfilmentTime() == null) {
                unfinishedOrders++;
            }
        }

        System.out.println("Počet aktualne rozpracovaných objednávek : " + unfinishedOrders);    }

    public void sortOrdersByOrderTime() {
        List<Order> ordersList = new ArrayList<>(order.getOrders().values());
        Collections.sort(ordersList, Comparator.comparing(Order::getOrderTime));
        for (Order order : ordersList) {
            System.out.println("Order ID: " + order.getOrderId() + ", Time: " + order.getOrderTime());
        }
    }

    public double averageProcessTimeforOrders() {
        int totalProcessedOrders = 0;
        Duration totalProcessingTime = Duration.ZERO;
        for (Order order : order.getOrders().values()) {
            Duration processingTime = getOrderProcessingTime(order);
            if (!processingTime.isZero()) {
                totalProcessedOrders++;
                totalProcessingTime = totalProcessingTime.plus(processingTime);
            }
        }
        if (totalProcessedOrders == 0) {
            return 0;
        }
        double averageTimeInSeconds = totalProcessingTime.getSeconds() / (double) totalProcessedOrders;
        return averageTimeInSeconds;
    }

    public Duration getOrderProcessingTime(Order order) {
        LocalTime orderTime = order.getOrderTime();
        LocalTime fulfilmentTime = order.getFulfilmentTime();
        if (fulfilmentTime != null) {
            return Duration.between(orderTime, fulfilmentTime);
        } else {
            return Duration.ZERO;
        }
    }

    public void printTodayOrderedDishes() {
        LocalDate today = LocalDate.now();
        System.out.println("Dnešné objednané jedlá:");
        Set<Integer> todayDishes = new HashSet<>();
        for (Order order : order.getOrders().values()) {
            LocalDate orderDate = LocalDate.now();
            if (orderDate.equals(today)) {
                int dishId = order.getDishId();
                if (!todayDishes.contains(dishId)) {
                    System.out.println(CookBook.getDishById(dishId));
                    todayDishes.add(dishId);
                }
            }
        }
    }

    public void printOrdersForTable(int tableNumber) {
        order.setNextBillId();
        List<Order> orders = order.getOrdersForTable(tableNumber);
        if (orders.isEmpty()) {
            System.out.println("Stůl " + tableNumber + " nemá žádné objednávky.");
        } else {
            System.out.println("** Objednávky pro stůl č." + formatTableNumber(tableNumber) + " **");
            System.out.println("****");
            for (Order order : orders) {
                System.out.println(order.getDescription());
            }
            System.out.println("******");
        }
    }


}
