package com.Engeto.Restaurant;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;



public class OrderManager {
    Order order = new Order();
    public  void saveOrdersToFile(String filename) {

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : order.getOrderss().values()) {
                writer.write(order.getOrderId() + "\t" + order.getTableNumber()+"\t"
                        +order.getDishId()+"\t"+order.getCountDish()+"\t" +order.getOrderTime()
                        .format(DateTimeFormatter.ofPattern("HH:mm"))
                        +"\t"+ fulfilmentTimeString(order.getFulfilmentTime())+"\t" +order.isPaid());
                writer.newLine();
            }
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


    public static void loadOrdersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int orderId = Integer.parseInt(parts[0]);
                int dishId = Integer.parseInt(parts[1]);

                // Zde byste měli vytvořit objednávku a přidat ji do kolekce "orderss"
                // Například:
                // Order order = new Order();
                // order.setOrderId(orderId);
                // order.setDish(CookBook.getDishById(dishId));
                // orderss.put(order.getOrderId(), order);
            }
            System.out.println("Objednávky byly načteny ze souboru '" + filename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
