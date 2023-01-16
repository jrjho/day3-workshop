package day3workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String dirPath = "data2";
        String fileName = " ";

        // File loginFile = new File(dirPath, fileName)


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
                    cartItems = readCartItemsFromFile( dirPath,  fileName);
                    listCart(cartItems);
                    break;
                case "users":
                    listUsers(dirPath);
                    break;
                default:
                    break;
            }
                if(input.startsWith("login")){
                    fileName = createLoginFile(input, dirPath, fileName);
                }

                if(input.startsWith("add")){
                    String value;
                    input = input.replace(","," "); //replace , seperator with space
                    Scanner scanner = new Scanner(input.substring(4));
                    
                    FileWriter fw = new FileWriter(dirPath + "/" + fileName);
                    PrintWriter pw = new PrintWriter(fw);

                    while(scanner.hasNext()){
                        value = scanner.next();
                        cartItems.add(value);

                        pw.printf("%s\n",value);
                    }
                    pw.flush();
                    fw.flush();
                    fw.close();
                    pw.close();
                }
                if(input.startsWith("delete")){
                    cartItems = deleteCartItem(cartItems, input);
                    updateCartItemToFile(cartItems, dirPath, fileName);
                }

        }

    }

    public static void updateCartItemToFile(List<String> cartItems, String dirPath, String fileName) throws IOException{
        FileWriter fw = new FileWriter(dirPath + File.separator + fileName, false);
        BufferedWriter bw = new BufferedWriter(fw);

        int listCount = 0;
        while(listCount < cartItems.size()){
            bw.write(cartItems.get(listCount));
            bw.newLine();
            listCount++;
        }
        bw.flush();
        fw.flush();
        bw.close();
        fw.close();
    }

    public static void listUsers(String dirPath){
        File directoryPath = new File(dirPath);
        String contents[] = directoryPath.list();
        for(String file : contents){
            System.out.printf("%s",file);
        }
    }
    public static String createLoginFile(String input, String dirPath, String fileName) throws IOException{
        input = input.replace(","," ");
        Scanner scanner = new Scanner(input.substring(6));
        while(scanner.hasNext()){
            fileName = scanner.next();
        }
        File loginFile = new File(dirPath + "/" + fileName);
        boolean isCreated = loginFile.createNewFile();
        if(isCreated){
            System.out.println("New file created." + loginFile.getCanonicalPath());
        }else {System.out.println("File already created");}
        return fileName;
    }
    public static List<String> deleteCartItem(List<String> cartItems, String input) {
        String strValue = "";
        Scanner scanner = new Scanner(input.substring(6));

        while (scanner.hasNext()) {
            strValue = scanner.next();
            int listItemIndex = Integer.parseInt(strValue);

            if (listItemIndex < cartItems.size()) {
                cartItems.remove(listItemIndex);
            } else {
                System.out.println("Incorrect item index.");
            }
        }

        return cartItems;
    }

    // public static List<String> deleteCart(List<String> cartItems, String input) {
    //     String value= "";
    //     // input = input.replace(","," "); //replace , seperator with space
    //     Scanner scanner = new Scanner(input.substring(6));
    //     while(scanner.hasNext()){
    //         value = scanner.next();
    //         int listItemIndex = Integer.parseInt(value);
    //         if(listItemIndex < cartItems.size()){
    //             cartItems.remove(listItemIndex);
    //         }else{System.out.println("Incorrect item index.");}
    //     }
    //     return cartItems;
    // }

    public static List<String> readCartItemsFromFile(String dirPath, String fileName) throws IOException{
        List<String> items = new ArrayList<String>();
        File file = new File(dirPath + File.separator + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sr;
        while((sr = br.readLine()) !=null){
            items.add(sr);
        }
        return items;
    }

    public static void listCart(List<String> cartItems){
        if (cartItems.size() > 0 ){
            for(int i = 0;  i < cartItems.size(); i++)
                System.out.printf("%d. %s\n", i ,cartItems.get(i));  
        }else{System.out.println("Cart is empty");}

    }
}
