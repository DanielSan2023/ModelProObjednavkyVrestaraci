package com.Engeto.Restaurant;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Order {
    public static Map<Integer, List<Dish>> orders = new HashMap<>();
    public static List<Order> orderList = new ArrayList<>(); // Zoznam objednávok


    private int orderId;
    private int tableNumber;
    private Dish dish;
    private LocalTime orderTime;
    private LocalTime fulfilmentTime;
    private boolean isPaid;
    private int countDish = 1;

    private static int nextOrderId;
    //  zoznam objednavok : Tablenumber,Objednavka
    private static Map<Integer, Order> orderss = new HashMap<>();

    public void addOrder(Order order) {
        orderss.put(order.getOrderId(), order);
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
            System.out.println("Objednávky pro stůl č. " + tableNumber + ":");
            for (Order order : orders) {
                System.out.println(order.getDescription());
            }
        }
    }
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Číslo stolu: ").append(tableNumber).append("\n");
        description.append("Číslo objednávky na tomto stolu: ").append(orderId).append("\n");
        description.append("Jídlo: ").append(dish.getTitle()).append("\n");
        description.append("Počet porcí: ").append(countDish).append("\n");
        description.append("Cena: ").append(totalDishPrice()).append(" €").append("\n");
        description.append("Čas objednávky: ").append(orderTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("\n");

        if (fulfilmentTime != null) {
            description.append("Čas vyřízení: ").append(fulfilmentTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("\n");
        } else {
            description.append("Čas vyřízení: nezadáno").append("\n");
        }

        description.append("Stav platby: ").append(isPaid ? "Zaplaceno" : "Nezaplaceno").append("\n");

        return description.toString();
    }
    public double totalDishPrice() {
        return countDish * dish.getPrice();
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



    public static List<Order> getOrderList() {
        return orderList;
    }



    public void setFulfilmentTime() {
        if (orderTime != null && dish != null) {
            int preparationTimeMinutes = dish.getPreparationTime(); // Doba přípravy v minutách
            if (preparationTimeMinutes > 0) {
                fulfilmentTime = orderTime.plusMinutes(preparationTimeMinutes);
            }
        }
    }

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


    public static void listOrderDetails(int tableNumber) {
        List<Dish> dishes = orders.get(tableNumber);
        if (dishes == null) {
            System.out.println("Stůl " + tableNumber + " nemá žádné objednávky.");
            return;
        }
        //duplikaty prec
        Set<Dish> uniqueDishesSet = new HashSet<>(dishes);
        dishes = new ArrayList<>(uniqueDishesSet);

        System.out.println("Objednávky pro stůl č. " + tableNumber + ":");
        System.out.println("***");
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.println(+(i + 1) + ":" + dish.getTitle() + dish.getPrice() + " €");
            //      System.out.println("pocet jedla:"+ getCountDish());
//            System.out.println(" - Čas zadání objednávky: " + order.getOrderTime());
//            if (order.getFulfilmentTime() != null) {
//                System.out.println(" - Čas vyřízení objednávky: " + order.getFulfilmentTime());
//            }
//            System.out.println(" - Order ID: " + order.getOrderId());
//            System.out.println(" - Stav platby: " + (order.isPaid() ? "Zaplaceno" : "Nezaplaceno"));
//            System.out.println();

        }
        System.out.println("*****");
    }


    public static void listOrderTimes() {
        List<LocalTime> orderTimes = orderList.stream()
                .map(Order::getOrderTime)
                .collect(Collectors.toList());

        System.out.println("Časy objednávok:");

        for (int i = 0; i < orderTimes.size(); i++) {
            LocalTime orderTime = orderTimes.get(i);
            String formattedTime = orderTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.println("Objednávka " + (i + 1) + ": " + formattedTime);
        }
    }

    public   void listOrderDetailsForTable(int tableNumber) {
        List<Dish> dishes = orders.get(tableNumber);
        if (dishes == null) {
            System.out.println("Stůl " + tableNumber + " nemá žádné objednávky.");
            return;
        }

        System.out.println("Objednávky pre stůl č. " + tableNumber + ":");

        for (Dish dish : dishes) {
            LocalTime orderTime = getOrderTime();
            String formattedTime = orderTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.println("Stůl č. " + tableNumber + " - Jedlo: " + dish.getTitle() + "pocet jedal: "+getCountDish()+" Čas objednania: " + formattedTime);
        }
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
