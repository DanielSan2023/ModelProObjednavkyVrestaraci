package com.Engeto.Restaurant;


import java.io.*;
import java.util.*;


public class CookBook {

  private static Map<Integer, Dish> dishes; // Použijeme HashMap pro ukládání jídel podle jejich ID

  public CookBook() {
    dishes = new HashMap<>();
  }

  public static void addDish(int idDish, Dish newDish) {
    // Přidáme nové jídlo do mapy s použitím jeho ID jako klíče
    dishes.put(idDish, newDish);
  }

// // public  void addDish(Dish newDish) {
//    int id = generateNewId();
//    newDish.setIdDish(id);
//    dishes.put(id, newDish);
//  }
  public static void addDish(Dish newDish) {
    int id = generateNewId();
    newDish.setIdDish(id);
    dishes.put(id, newDish);
  }
  private static int generateNewId() {
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
  public void saveToFile(String filename, CookBook cookBook) throws DishException {
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
        for (Dish dish : cookBook.getDishes()) { // Předpokládáme, že máte metodu getDishes v třídě CookBook
        writer.println(dish.getTitle() + Settings.fileItemDelimiter() + dish.getPrice() + Settings.fileItemDelimiter()
                + dish.getPreparationTime());
      }
    } catch (IOException e) {
      throw new DishException("Chyba při zápisu do souboru '" + filename + "': " + e.getLocalizedMessage());
    }
  }

  public static void loadDishesFromFile(String filename) throws DishException {
    try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
        while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Dish dish = parseDishLine(line);
        addDish(dish); // Přidejte vytvořený objekt Dish do seznamu jídel
      }
    } catch (FileNotFoundException e) {
      throw new DishException("Nepodařilo se nalézt soubor " + filename + ": " + e.getLocalizedMessage());
    }
  }
  private static Dish parseDishLine(String line) throws DishException {
    String[] blocks = line.split(Settings.fileItemDelimiter());
    int numOfBlocks = blocks.length;
    if (numOfBlocks != 3) {
      System.err.println("Nesprávný počet položek na řádku: " + line + "! Počet položek: " + numOfBlocks + ".");
      return null; // Vrátí null, pokud nebylo možné načíst Dish
    }
    String title = blocks[0].trim();
    double price = Double.parseDouble(blocks[1].trim());
    int preparationTime = Integer.parseInt(blocks[2].trim());
    // Vytvoření objektu Dish s načtenými hodnotami
    Dish newDish = new Dish(title, price, preparationTime);
    return newDish;
  }


}
