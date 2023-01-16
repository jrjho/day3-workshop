package day3workshop;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        String dirPath = "data2";
        String fileName = " ";
        System.out.println("Hello World!");

        File newDirectory = new File(dirPath);
        if(newDirectory.exists()){
            System.out.println("Directory exists!");
        } else {newDirectory.mkdir();}

        System.out.println("Welcome to my shopping cart!");
        List<String> cartItems = new ArrayList<String>();

        Console con = System.console();
        String input = "";

        while(!input.equalsIgnoreCase("quit")){
            input = con.readLine("What do you want to perform? Type quit to exit.");

            switch (input) {
                case "help":
                    System.out.println("<List> to show a list of items in shopping cart");
                    System.out.println("<login name> to access your shopping cart");
                    System.out.println("<add item> to add items in your shopping cart");
                    System.out.println("<delete item> to delete items from your shopping cart");
                    System.out.println("<quit> to exit program");
                    
                    break;
                case "list":
                    break;
                case "users":
                    break;
                default:
                    break;
            }

                if(input.startsWith("add")){
                    String value;
                    input = input.replace(","," "); //replace , seperator with space
                    Scanner scanner = new Scanner(input.substring(4));
                    while(scanner.hasNext()){
                        value = scanner.next();
                        cartItems.add(value);

                    }

            }

        }

    }
}
