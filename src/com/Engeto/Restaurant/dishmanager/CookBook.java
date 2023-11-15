package com.Engeto.Restaurant.dishmanager;


import com.Engeto.Restaurant.exceptions.DishException;
import com.Engeto.Restaurant.settings.Settings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CookBook {

    private static Map<Integer, Dish> dishes;

    public CookBook() {
        dishes = new HashMap<>();
    }

    public static void addDish(int idDish, Dish newDish) {
        dishes.put(idDish, newDish);
    }

    public void addDishObj(Dish newDish) {
        int id = generateNewId();
        newDish.setIdDish(id);
        dishes.put(id, newDish);
    }

    public static void addDish(Dish newDish) {
        int id = generateNewId();
        newDish.setIdDish(id);
        dishes.put(id, newDish);
    }

    private static int generateNewId() {
        int maxId = dishes.keySet().stream().max(Integer::compare).orElse(0);
        return maxId + 1;
    }

    public static Dish getDishById(int dishId) {
        for (Dish dish : dishes.values()) {
            if (dish.getIdDish() == dishId) {
                return dish;
            }
        }
        return null;
    }

    public void editDish(int dishId, Dish updatedDish) {
        if (dishes.containsKey(dishId)) {
            Dish dish = dishes.get(dishId);
            dish.setTitle(updatedDish.getTitle());
            dish.setPrice(updatedDish.getPrice());
            dish.setPreparationTime(updatedDish.getPreparationTime());
            dish.setImage(updatedDish.getImage());
        }
    }

    public void removeDish(int dishId) {
        dishes.remove(dishId);
    }

    public List<Dish> getDishes() {
        return new ArrayList<>(dishes.values());
    }

    public void printDishes() {
        for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
            int dishId = entry.getKey();
            Dish dish = entry.getValue();
            System.out.print("ID: " + dishId);
            System.out.print("  Jedlo: " + dish.getTitle());
            System.out.print(" cena: " + dish.getPrice() + " € ");
            System.out.print(" doba pripravy: " + dish.getPreparationTime() + " minut");
            //System.out.println("Image: " + dish.getImage());
            System.out.println(" ");
        }
    }

    public void saveToFile(String filename, CookBook cookBook) throws DishException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : cookBook.getDishes()) {
                writer.println(dish.getIdDish() + Settings.fileItemDelimiter() + dish.getTitle() + Settings.fileItemDelimiter()
                        + dish.getPrice() + Settings.fileItemDelimiter()
                        + dish.getPreparationTime());

            }
            System.out.println("Jedlá boli uložené do suboru '" + filename + "'.");
        } catch (IOException e) {
            throw new DishException("Chyba při zápisu do souboru '" + filename + "': " + e.getLocalizedMessage());
        }
    }


    public static void loadDishesFromFile(String filename) throws DishException {
        try {
            if (Files.size(Paths.get(filename)) == 0) {
                throw new DishException("Soubor '" + filename + "' je prázdný.");
            }
            try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Dish dish = parseDishLine(line);
                    addDish(dish); // Přidejte vytvořený objekt Dish do seznamu jídel
                }
            }
        } catch (FileNotFoundException e) {
            throw new DishException("Nepodařilo se nalézt soubor " + filename + ": " + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new DishException("Chyba při načítání jídel ze souboru " + filename + ": " + e.getLocalizedMessage());
        }
    }

    private static Dish parseDishLine(String line) throws DishException {
        String[] blocks = line.split(Settings.fileItemDelimiter());
        int numOfBlocks = blocks.length;
        if (numOfBlocks != 4) {
            System.err.println("Nesprávný počet položek na řádku: " + line + "! Počet položek: " + numOfBlocks + ".");
            return null; // Vrátí null, pokud nebylo možné načíst Dish
        }
        int idDish = Integer.parseInt(blocks[0].trim());
        String title = blocks[1].trim();
        double price = Double.parseDouble(blocks[2].trim());
        int preparationTime = Integer.parseInt(blocks[3].trim());
        // Vytvoření objektu Dish s načtenými hodnotami
        Dish newDish = new Dish(title, price, preparationTime, idDish);
        return newDish;
    }


}
