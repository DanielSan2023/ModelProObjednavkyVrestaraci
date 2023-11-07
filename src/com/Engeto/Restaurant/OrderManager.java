package com.Engeto.Restaurant;
import java.io.*;
import java.util.Map;



public class OrderManager {
    public static void saveOrdersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : Order.getOrderss().values()) {
                writer.write(order.getOrderId() + " " + order.getDish().getIdDish());
                writer.newLine();
            }
            System.out.println("Objednávky byly uloženy do souboru '" + filename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
