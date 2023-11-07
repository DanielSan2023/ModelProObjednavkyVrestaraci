
import com.Engeto.Restaurant.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CookBook cookBook = new CookBook();
        List<Order> orders = new ArrayList<>();


         //Vytvoření jídel
        Dish spaghetti, steak, spaghetti2, updateDish,kofola,kofola2 = null;

        Dish jedlo1;
       try {kofola  = new Dish("Kofola malá",2,5);
           kofola2  = new Dish("Kofola velká",3,5);
            spaghetti = new Dish("Boloňské špagety(150g)", 12.99, 30);
            spaghetti2 = new Dish("Sicilske špety", 12.54, 30);
            steak = new Dish("Hovězí steak", 18.99, 45);

            jedlo1 = new Dish("Kuracie nugetky", 12.30, 28);
            Dish jedlo2 = new Dish("Kuracie stehienka", 14.30, 35);
            Dish jedlo3 = new Dish("Hovadzie na smotane", 18.30, 35);
            Dish jedlo4 = new Dish("Pizza Hawai (450g)", 9.30, 22);
            Dish jedlo5 = new Dish("Pizza 4 Seasons(600g)", 10.30, 22);
            Dish jedlo6 = new Dish("Hydinové guľky v rajčiakovej omáčke ", 14.30, 35);
            Dish jedlo7 = new Dish("Morčací paprikáš", 15.30, 28);
           CookBook.addDish(kofola);
           CookBook.addDish(kofola2);
           CookBook.addDish(jedlo1);
           CookBook.addDish(jedlo2);
            CookBook.addDish(jedlo3);
            CookBook.addDish(jedlo4);
            CookBook.addDish(jedlo5);
            CookBook.addDish(jedlo6);
            CookBook.addDish(jedlo7);



        } catch (DishException e) {
            throw new RuntimeException(e);
        }
        CookBook.addDish(steak);
        CookBook.addDish(spaghetti);
        CookBook.addDish(spaghetti2);


        try {
            cookBook.saveToFile("listDishes.txt", cookBook);
        } catch (DishException e) {
            throw new RuntimeException(e);
        }


        //Load File listdishes
        try {
            CookBook.loadDishesFromFile(Settings.fileNameforLoad());
        } catch (DishException e) {
            System.err.println("Chyba pri cteni ze souboru : "+e.getLocalizedMessage());
        }

        // Vytvoření objednávek
        Order order2 = new Order(2, 1,3);
        Order order4 = new Order(2, 3);
        Order order8 = new Order(2, 9,2);
        Order order10 = new Order(2, 6);
        Order order12 = new Order(11, 1,4);
        Order order14 = new Order(11, 7);
        Order order16 = new Order(11, 2,4);
        Order order18 = new Order(11, 6);
        Order order5 = new Order(9, 1,2);
        Order order7 = new Order(9, 3);
//       Order.listOrderDetails(9);
//       Order.listOrderDetails(2);




        //vypise all dishes
        cookBook.printDishes();


        Order.printOrdersForTable(2);
        Order.printOrdersForTable(9);
        Order.printOrdersForTable(11);

        // vybavenie objednavky
        order2.setFulfilmentTime();
        order4.setFulfilmentTime();
        order2.markAsPaid();
        Order.printOrdersForTable(2);
       order4.setFulfilmentTime();


       OrderManager.saveOrdersToFile("orders.txt");


    }


    }
