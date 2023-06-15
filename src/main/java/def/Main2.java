package def;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Main2 {
    public static void main(String[] args) throws Exception {
        String[] options = {"1", "2", "Exit"};
        //ImageIcon icon = new ImageIcon("src\\main\\java\\def\\img\\icon.png");

        do {
            var select = JOptionPane.showOptionDialog(null,
                    "1. Basic Features\n2. Advanced Features\nExit",
                    "Three Kingdoms",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (select){
                case 0 -> basicFeatures();
                case 1 -> advancedFeatures();
                case 2 -> {
                    String[] close = {"close"};
                    JOptionPane.showOptionDialog(null,
                            "See you again!",
                            "Three Kingdoms",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            close,
                            close[0]);
                    System.exit(0);
                }
            }
        } while (true);
    }

    public static void basicFeatures(){
        String[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "Exit"};
        //ImageIcon icon = new ImageIcon("src\\main\\java\\def\\img\\icon.png");

        do {
            var select = JOptionPane.showOptionDialog(null,
                    "1. Print Wu's Kingdom Hierarchy\n" +
                            "2. Soldier's Arrangement, Sorting and Searching\n" +
                            "3. Borrowing Arrows with Straw Boats\n" +
                            "4. Enemy Fortress Attack Simulation\n" +
                            "5. Food Harvesting\n" +
                            "6. Text decryption\n" +
                            "7. Red Cliff on Fire\n" +
                            "8. Cao Cao on Hua Rong Road\n" +
                            "Exit",
                    "Basic Features",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (select){
                case 0 -> new Army(true);
                case 1 -> new SoldierArrangement();
                case 2 -> new BorrowingArrow();
                case 3 -> new ShortestPath();
                case 4 -> new FoodHarvesting();
                case 5 -> new CaesarCipher();
                case 6 -> new MatrixCluster();
                case 7 -> new MazePath();
                case 8 -> {
                    String[] close = {"close"};
                    JOptionPane.showOptionDialog(null,
                            "See you again!",
                            "Three Kingdoms",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            close,
                            close[0]);
                    return;
                }
            }
        } while (true);
    }

    public static void advancedFeatures() throws Exception {
        String[] options = {"1", "2", "3", "4", "Exit"};
        //ImageIcon icon = new ImageIcon("src\\main\\java\\def\\img\\icon.png");

        do {
            var select = JOptionPane.showOptionDialog(null,
                    "1. Dynamic Arrow Borrowing\n" +
                            "2. Advanced Text Encryption\n" +
                            "3. Food Harvesting I\n" +
                            "4. Enemy Fortress Attack Simulation\n" +
                            "Exit",
                    "Advanced Features",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (select){
                case 0 -> new BorrowingArrow2();
                case 1 -> new TextConverterSecure();
                case 2 -> new FoodHarvestingI();
                case 3 -> new FortressAttackSimulation();
                case 4 -> {
                    String[] close = {"close"};
                    JOptionPane.showOptionDialog(null,
                            "See you again!",
                            "Three Kingdoms",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            close,
                            close[0]);
                    return;
                }
            }
        } while (true);
    }
}
