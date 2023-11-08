package com.Engeto.Restaurant;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class OrderManager {
    Order order = new Order();
    public  void saveOrdersToFile(String filename) {

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : order.getOrders().values()) {
                writer.write(order.getOrderId() + "\t" + order.getTableNumber()+"\t"
                +order.getDishId()+"\t"+order.getCountDish()+"\t" +order.getOrderTime()
               .format(DateTimeFormatter.ofPattern("HH:mm"))
                +"\t"+ fulfilmentTimeString(order.getFulfilmentTime())+"\t" +order.isPaid());
                writer.newLine(); }
            System.out.println("Objednávky byly uloženy do souboru '" + filename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static String fulfilmentTimeString(LocalTime localtime){
        String fulfilmentTimeStr = localtime != null ?
                localtime.format(DateTimeFormatter.ofPattern("HH:mm")) : "null";

        return fulfilmentTimeStr;
    }



    public void loadOrdersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Rozdelíme metódu na časti pre načítanie a parsovanie
                 order = loadOrderFromLine(line);
                if (order != null) {
                    order.addOrder(order);
                                    }
            }
            System.out.println("Objednávky byly načteny ze souboru '" + filename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metóda na parsovanie riadku na objekt Order
    private Order loadOrderFromLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length != 7) {
            return null; // Nesprávny počet položiek, vrátime null
        }

        int orderId = Integer.parseInt(parts[0]);
        int tableNumber = Integer.parseInt(parts[1]);
        int dishId = Integer.parseInt(parts[2]);
        int countDish = Integer.parseInt(parts[3]);
        //LocalTime orderTime = LocalTime.parse(parts[4], DateTimeFormatter.ofPattern("HH:mm"));
        //String fulfilmentTime = fulfilmentTimeString(LocalTime.parse(parts[5]));
        boolean isPaid = Boolean.parseBoolean(parts[6]);

        Order order = new Order(tableNumber, dishId, countDish);
        order.setOrderId(orderId);
       // order.setOrderTime(orderTime);
       // order.setFulfilmentTime(fulfilmentTime);
        order.setPaid(isPaid);

        return order;
    }




}
