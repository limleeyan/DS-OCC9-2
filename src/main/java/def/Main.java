package def;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int option;
        do {
            System.out.println("\n------------Three Kingdoms------------");
            System.out.println("1. Basic Features");
            System.out.println("2. Advanced Features");
            System.out.println("-1. Exit");
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> basicFeatures();
                case 2 -> advancedFeatures();
                case -1 -> System.out.println("See you again!");
                default -> System.out.println("Invalid option!");
            }
        } while (option != -1);
    }

    public static void basicFeatures(){
        int option;
        do {
            System.out.println("\n------------Basic Features------------");
            System.out.println("1. Print Wu's Kingdom Hierarchy");
            System.out.println("2. Soldier's Arrangement, Sorting and Searching");
            System.out.println("3. Borrowing Arrows with Straw Boats");
            System.out.println("4. Enemy Fortress Attack Simulation");
            System.out.println("5. Food Harvesting");
            System.out.println("6. Text decryption");
            System.out.println("7. Red Cliff on Fire");
            System.out.println("8. Cao Cao on Hua Rong Road");
            System.out.println("-1. Exit");
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> new Army(true);
                case 2 -> new SoldierArrangement();
                case 3 -> new BorrowingArrow();
                case 4 -> new ShortestPath();
                case 5 -> new FoodHarvesting();
                case 6 -> new CaesarCipher();
                case 7 -> new MatrixCluster();
                case 8 -> new MazePath2();
                case -1 -> System.out.println("See you again!");
                default -> System.out.println("Invalid option!");
            }
        } while (option != -1);
    }

    public static void advancedFeatures() throws Exception {
        int option;
        do {
            System.out.println("\n------------Advanced Features------------");
            System.out.println("1. Dynamic Arrow Borrowing");
            System.out.println("2. Advanced Text Encryption");
            System.out.println("3. Food Harvesting I");
            // System.out.println("4. Enemy Fortress Attack Simulation I");
            System.out.println("-1. Exit");
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> new BorrowingArrow2();
                case 2 -> new TextConverterSecure();
                case 3 -> new FoodHarvestingI();
                // case 4 -> new AttackSimulation();
                case -1 -> System.out.println("See you again!");
                default -> System.out.println("Invalid option!");
            }
        } while (option != -1);
    }
}
