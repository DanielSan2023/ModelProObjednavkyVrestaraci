
import com.Engeto.Restaurant.CookBook;
import com.Engeto.Restaurant.Dish;
import com.Engeto.Restaurant.DishException;
import com.Engeto.Restaurant.Order;

public class Main {
    public static void main(String[] args)  {
        CookBook cookBook = new CookBook();
        // Vytvoření jídel
//        Dish spaghetti,steak, spaghetti2 , updateDish = null;
//
//        try {
//            spaghetti = new Dish("Boloňské špagety", 12.99,30);
//           spaghetti2 = new Dish("Boloňské špety", 12.54, 30);
//             steak = new Dish("Hovězí steak", 18.99,45);
//            updateDish = new Dish("Boloňské špety", 12.54);
//        } catch (DishException e) {
//            throw new RuntimeException(e);
//        }
//        cookBook.addDish(steak);
//        cookBook.addDish(spaghetti);
//        cookBook.addDish(spaghetti2);
//
//        cookBook.printDishes();

        //Load File listdishes












        // Vytvoření objednávek

        Order order2 = new Order(2, 1);
        Order order3 = new Order(2, 1);
        Order order4 = new Order(2, 3);
       // System.out.println("Stol cislo 2: "+Order.getOrdersForTable(2));


        Order order5 = new Order(9, 1);
        Order order6 = new Order(9, 1);
        Order order7 = new Order(9, 3);


        Order order8 = new Order(2, 1);
        Order order9 = new Order(2, 1);
        Order order10 = new Order(2, 3);
      Order.listOrderDetails(9);
    }


    }
