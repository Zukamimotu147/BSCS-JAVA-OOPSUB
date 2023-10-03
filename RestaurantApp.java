package OOPSubject;

import java.text.DecimalFormat;
import java.util.*;

public class Restaurant {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {
        ArrayList<Menu> list = new ArrayList<>();

        list.add(new Menu("C1", 100.00));
        list.add(new Menu ("C2", 150.00));
        list.add(new Menu ("C3", 200.00));
        list.add(new Menu ("R1", 35.00));
        list.add(new Menu ("R2", 50.00));

        double totalPHP = 0.00;
        double totalUSD = 0.00;
        ArrayList<Order> order = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        //Menu
        System.out.println("==========Restaurant Interactive Application=========");
        System.out.println("Menu:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName() + " - Php " + list.get(i).getPrice());
        }
        System.out.println("\nAdd Ons:");
        for (int i = 3; i < 5; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName() + " - Php " + list.get(i).getPrice());
        }

        
          
        while (true) {
            System.out.print("Enter the item number (Enter \"0\" to finish): ");
            int input = scan.nextInt();
            if (input == 0) {
                break;
            }
   
            System.out.print("Enter the quantity: ");
            int quantity = scan.nextInt();
   
            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    int selectedIndex = input - 1;
                    Menu selectedMenuItem = list.get(selectedIndex);
   
                    totalPHP += selectedMenuItem.getPrice() * quantity;
                    Order orderItem = new Order(selectedMenuItem, quantity);
                    order.add(orderItem);
                    break;
                default:
                    System.out.println("Invalid item number.");
                    break;
               }
           }
   
           totalUSD = totalPHP * 0.018;
   
           // Display the order summary
           System.out.println("\nOrder Summary:");
           for (Order orderItem : order) {
               System.out.println(orderItem.toString());
           }
           
           System.out.println("Total Price (USD): $ " + df.format(totalUSD));
   
           scan.close();
     }
}

class Menu {
          private String name;
          private double price;

          public Menu (String name, double price) {
               this.name = name;
               this.price = price;
          }

          public String getName() {
               return name;
          }

          public double getPrice() {
               return price;
          }
     }

     class Order {
          private Menu menuItem;
          private int quantity;
      
          public Order (Menu menuItem, int quantity) {
              this.menuItem = menuItem;
              this.quantity = quantity;
          }
      
      
          @Override
          public String toString() {
              return menuItem.getName() + " - Qty: " + quantity;
          }
     }