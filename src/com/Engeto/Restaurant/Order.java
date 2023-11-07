package com.Engeto.Restaurant;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Order {



    private int orderId;
    private int tableNumber;
    private Dish dish;
    private LocalTime orderTime;
    private LocalTime fulfilmentTime;
    private boolean isPaid;
    private int countDish = 1;

    private static int nextOrderId =1;
    //  zoznam objednavok : Tablenumber,Objednavka
    private static Map<Integer, Order> orderss = new HashMap<>();

    public void addOrder(Order order) {
        orderss.put(order.getOrderId(), order);
    }
    public static Order getOrder(int orderId) {
        return orderss.get(orderId);
    }

    public static Map<Integer, Order> getOrderss() {
        return orderss;
    }

    public static void setOrderss(Map<Integer, Order> orderss) {
        Order.orderss = orderss;
    }

    public static List<Order> getOrdersForTable(int tableNumber) {
        return orderss.values()
                .stream()
                .filter(order -> order.getTableNumber() == tableNumber)
                .collect(Collectors.toList());
    }

    public static void printOrdersForTable(int tableNumber) {
        List<Order> orders = getOrdersForTable(tableNumber);
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
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(orderId).append(".").append(" ").append(dish.getTitle())
                .append(" ").append(countDish).append(" ").append("(")
                .append(totalDishPrice()).append(" €").append(")").append(":").append("\t")
                .append(orderTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("-");

        if (fulfilmentTime != null) {
            description.append(fulfilmentTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("\t");
                }
        description.append(isPaid ? "Zaplaceno" : "");
        return description.toString();
    }
    public double totalDishPrice() {
        double price = countDish * dish.getPrice();
        return Math.round(price * 100.0) / 100.0;
    }
    public static String formatTableNumber(int tableNumber) {
        if (tableNumber >= 1 && tableNumber <= 9) {
            return " " + tableNumber;  // Přidejte mezeru před jednocifernými čísly
        } else {
            return String.valueOf(tableNumber);  // Vrací dvojciferná čísla beze změn
        }
    }


//region construktors

    public Order(int tableNumber, int dishId, int countDish) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.dish = CookBook.getDishById(dishId);
        this.orderTime = LocalTime.now();
        this.isPaid = false;
        this.countDish = countDish;
        // Přidání objednávky do kolekce "orderss" s číslem stolu a objektem třídy Dish
        addOrder(this);
    }

    public Order(int tableNumber, int dishId) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.dish = CookBook.getDishById(dishId);
        this.orderTime = LocalTime.now();
        this.isPaid = false;
        this.countDish = 1;
        // Přidání objednávky do kolekce "orderss" s číslem stolu a objektem třídy Dish
        addOrder(this);

    }
    public Order() {
    }

//endregion construktors


//    public Order(int tableNumber, int dishId) {
//        this.orderId = nextOrderId++;
//        this.tableNumber = tableNumber;
//        this.dish = CookBook.getDishById(dishId);
//        this.orderTime = LocalTime.now();
//        this.isPaid = false;
//        orders.computeIfAbsent(tableNumber,
//                k -> new ArrayList<>()).add(this.dish);
//        orderList.add(this); // Pridanie objednávky do zoznamu
//        this.countDish = 1;
//    }
//
//
//    public Order(int tableNumber, int dishId, int countDish) {
//        this.orderId = nextOrderId++;
//        this.tableNumber = tableNumber;
//        this.dish = CookBook.getDishById(dishId);
//        this.orderTime = LocalTime.now();
//        this.isPaid = false;
//        this.countDish = countDish;
//        // Přidání objednávky do kolekce "orders" s číslem stolu a objektem třídy Dish
//        if (!orders.containsKey(tableNumber)) {
//            orders.put(tableNumber, new ArrayList<>());
//        }
//        orders.get(tableNumber).add(this.dish);
//
//    }







    public void setFulfilmentTime(LocalTime fulfilmentTime) {

        this.fulfilmentTime = fulfilmentTime;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getDishId() {
        return dish.getIdDish();
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public LocalTime getFulfilmentTime() {
        return fulfilmentTime;
    }


    public boolean isPaid() {
        return isPaid;
    }

    public int getCountDish() {
        return countDish;
    }

    public void setCountDish(int countDish) {
        this.countDish = countDish;
    }




    @Override
        public String toString () {
            return "Order{" +
                    "tableNumber=" + tableNumber +
                    ", dish=" + dish +
                    ", orderTime=" + orderTime +
                    ", fulfilmentTime=" + fulfilmentTime +
                    ", isPaid=" + isPaid +
                    '}';
        }
    }
