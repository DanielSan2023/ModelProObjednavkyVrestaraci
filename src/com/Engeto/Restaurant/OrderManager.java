package com.Engeto.Restaurant;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Path file = Path.of(filename);
        try {
            if (Files.size(file) == 0) {
                System.out.println("Soubor '" + filename + "' je prázdný.");
                return;  }
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Order order = loadOrderFromLine(line);
                    if (order != null) {
                        order.addOrder(order);
                    } else {
                        throw new RestaurantException("Neplatný formát řádku v souboru " + filename);    }    }
                System.out.println("Objednávky byly načteny ze souboru '" + filename + "'.");
            } catch (IOException e) {
                System.err.println("Chyba při načítání objednávek: " + e.getMessage());
            }
        } catch (RestaurantException | IOException e) {
            System.err.println("Chyba při zjišťování velikosti souboru: " + e.getMessage());
        }
    }


    private Order loadOrderFromLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length != 7) {
            return null;         }

        int orderId = Integer.parseInt(parts[0]);
        int tableNumber = Integer.parseInt(parts[1]);
        int dishId = Integer.parseInt(parts[2]);
        int countDish = Integer.parseInt(parts[3]);
        //LocalTime orderTime = LocalTime.parse(parts[4], DateTimeFormatter.ofPattern("HH:mm"));
        //String fulfilmentTime = fulfilmentTimeString(LocalTime.parse(parts[5]));
        boolean isPaid = Boolean.parseBoolean(parts[6]);
        Order order = new Order(tableNumber, dishId, countDish);
        order.setOrderId(orderId);
        order.setPaid(isPaid);
        return order;
    }





}
