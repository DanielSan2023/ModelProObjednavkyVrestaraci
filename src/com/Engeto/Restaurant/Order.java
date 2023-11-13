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
    private static  int nextBillId =1 ;
    private static int nextOrderId =1;

    private  static Map<Integer, Order> orders = new HashMap<>();

//region constructors

    public Order(int tableNumber, int dishId, int countDish) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.dish = CookBook.getDishById(dishId);
        this.orderTime = LocalTime.now();
        this.isPaid = false;
        this.countDish = countDish;
           addOrder(this);
    }

    public Order(int tableNumber, int dishId) {
        this.orderId = nextOrderId++;
        this.tableNumber = tableNumber;
        this.dish = CookBook.getDishById(dishId);
        this.orderTime = LocalTime.now();
        this.isPaid = false;
        this.countDish = 1;
            addOrder(this);

    }
    public Order() {
    }

//endregion construktors

    //region Getter Setter
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }
    public  Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public  Map<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
    }
    public static int getNextBillId() {
        return nextBillId++;
    }
    public void setNextBillId() {
        this.nextBillId=1;
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

    public  LocalTime getOrderTime() {
        return orderTime;
    }
    public LocalTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(String time){
        this.fulfilmentTime = fulfilmentTime;
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

    //endregion Getter Setter



    public  List<Order> getOrdersForTable(int tableNumber) {
        return orders.values()
                .stream()
                .filter(order -> order.getTableNumber() == tableNumber)
                .collect(Collectors.toList());
    }


    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(Order.getNextBillId()).append(".").append(" ").append(dish.getTitle())
                .append(" ").append(countDish).append(" ").append("(")
                .append(totalDishPrice()).append(" €").append(")").append(":").append("\t")
                .append(orderTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("-");
        if (fulfilmentTime != null) {
            description.append(fulfilmentTime.format(DateTimeFormatter.ofPattern("HH:mm"))).append("\t");
                }
        description.append(isPaid ? "Zaplaceno" : "");
        return description.toString();
    }

    public void printTotalDishPriceForTable(int tableNumber) {
        List<Order> orders = getOrdersForTable(tableNumber);
        double total = 0.0;
        for (Order order : orders) {
            total += order.totalDishPrice();
        }
        System.out.println("Celkova cena konzumace pro stůl číslo "
                +tableNumber +" je :"  + Math.round(total * 100.0) / 100.0+"€");
    }

    public double totalDishPrice() {
        double price = countDish * dish.getPrice();
        return Math.round(price * 100.0) / 100.0;
    }
    public static String formatTableNumber(int tableNumber) {
        if (tableNumber >= 1 && tableNumber <= 9) {
            return " " + tableNumber;
        } else {
            return String.valueOf(tableNumber);
        }
    }
    public void setPaidForOrder(int orderId) throws DishException {
        Order order = getOrderById(orderId);
        try {
            if (order != null) {
                if (order.getFulfilmentTime() != null) {
                    order.markAsPaid();
                } else {
                    throw new DishException("Objednávka s ID " + orderId + " nebola vybavená, a preto nemôže byť označená ako zaplatená.");
                }
            } else {
                throw new DishException("Objednávka s ID " + orderId + " neexistuje.");
            }
        } catch (Exception e) {
            throw new DishException("Chyba pri platení objednávky: " + e.getMessage());
        }
    }

    public void setFulfilmentTimeForOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.settingFulfilmentTime();
        }
    }
    public Order getOrderById(int orderId) {
        return orders.values()
                .stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public void settingFulfilmentTime() {
        if (orderTime != null && dish != null) {
            int preparationTimeMinutes = dish.getPreparationTime();
            if (preparationTimeMinutes > 0) {
                fulfilmentTime = orderTime.plusMinutes(preparationTimeMinutes);
            }
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
