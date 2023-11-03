package com.Engeto.Restaurant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CookBook {

  private static Map<Integer, Dish> dishes; // Použijeme HashMap pro ukládání jídel podle jejich ID

  public CookBook() {
    dishes = new HashMap<>();
  }

  public void addDish(int idDish,Dish newDish) {
    // Přidáme nové jídlo do mapy s použitím jeho ID jako klíče
    dishes.put(idDish, newDish);
  }

  public void addDish(Dish newDish) {
    int id = generateNewId();
    newDish.setIdDish(id);
    dishes.put(id, newDish);
  }
  private int generateNewId() {
    int maxId = dishes.keySet().stream().max(Integer::compare).orElse(0);
    return maxId + 1;
  }
  // Metoda pro získání jídla z knihovny jídel podle ID
  public static Dish getDishById(int dishId) {
    for (Dish dish : dishes.values()) {
      if (dish.getIdDish() == dishId) {
        return dish;
      }
    }
    return null; // Jídlo s daným ID nebylo nalezeno
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
    // Vytvoříme seznam všech jídel a vrátíme ho
    return new ArrayList<>(dishes.values());
  }

  public void printDishes() {
    for (Map.Entry<Integer, Dish> entry : dishes.entrySet()) {
      int dishId = entry.getKey();
      Dish dish = entry.getValue();
      System.out.println("ID: " + dishId);
      System.out.println("Title: " + dish.getTitle());
      System.out.println("Price: " + dish.getPrice() + " Kč");
      System.out.println("Preparation Time: " + dish.getPreparationTime() + " minutes");
      System.out.println("Image: " + dish.getImage());
      System.out.println();
    }
  }
  public static void loadDishesFromFile(String filename) {
    try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        parseDishLine(line);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Nepodařilo se nalézt soubor " + filename + ": " + e.getLocalizedMessage());
    } catch (IOException e) {
      System.err.println("Chyba při čtení souboru " + filename + ": " + e.getLocalizedMessage());
    }
  }

  private static void parseDishLine(String line) {
    String[] blocks = line.split("\t");
    int numOfBlocks = blocks.length;
    if (numOfBlocks != 2) {
      System.err.println("Nesprávný počet položek na řádku: " + line + "! Počet položek: " + numOfBlocks + ".");
      return;
    }

    String title = blocks[0].trim();
    double price = Double.parseDouble(blocks[1].trim());
    int preparationTime = Integer.parseInt(blocks[2].trim());
    String image = blocks[3].trim();
    int idDish = Integer.parseInt(blocks[4].trim());

    Dish newDish = new Dish(title, price, preparationTime, image);
    addDish(idDish, newDish);
  }


}
