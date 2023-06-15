package def;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SoldierArrangement {
    static ArrayList<TreeNode> generals = new ArrayList<>();
    static ArrayList<TreeNode> sortedPolitics = new ArrayList<>();
    static ArrayList<TreeNode> sortedIntelligence = new ArrayList<>();
    private static boolean isFoodHarvesting = false;

    public SoldierArrangement(){
        Army army = new Army(); // liddis first, no central class yet
        for (int i = 3; i < Army.army.size(); i++) {
            generals.add(new TreeNode(Army.army.get(i)));
        }
        do {
            String[] options1 = {"1", "2", "3", "4", "Exit"};
            var select = JOptionPane.showOptionDialog(null,
                    "1. Soldier Arrangement\n" +
                            "2. Sorted General's Abilities\n" +
                            "3. View 1 General's Abilities\n" +
                            "4. Search by ability value\n" +
                            "Exit",
                    "Soldier Arrangement",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options1,
                    options1[0]);
            switch (select) {
                case 0 -> soldierArrangement();
                case 1 -> sortedGeneralAbilities();
                case 2 -> viewGeneralAbilities();
                case 3 -> searchByAbilityValue();
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

    public SoldierArrangement(boolean isFood){
        if (!generals.isEmpty())
            generals.clear();
        isFoodHarvesting = isFood;
        Army army = new Army(); // liddis first, no central class yet
        for (int i = 3; i < Army.army.size(); i++) {
            generals.add(new TreeNode(Army.army.get(i)));
        }
    }

    public static void soldierArrangement() {
        String[] options2 = {"1", "2", "3", "4", "5"};
        var sChoice = JOptionPane.showOptionDialog(null,
                "1. Strength\n" +
                        "2. Leadership\n" +
                        "3. Intelligence\n" +
                        "4. Politic\n" +
                        "5. Hitpoint",
                "Soldier Arrangement",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options2,
                options2[0]);
        int choice = sChoice + 1;

        String[] options3 = {"S", "A", "B", "C"};
        var sTier = JOptionPane.showOptionDialog(null,
                "Enter tier requirement (S/A/B/C): ",
                "Soldier Arrangement",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options3,
                options3[0]);
        String tier = "";
        switch (sTier){
            case 0 -> tier = "S";
            case 1 -> tier = "A";
            case 2 -> tier = "B";
            case 3 -> tier = "C";
        }
        ArrayList<TreeNode> soldiers = suggestGeneralsByAbility(choice, tier);
        System.out.println("Suggested Generals: ");
        for (TreeNode soldier : soldiers) {
            System.out.println(soldier.getArmyData().getName());
        }
        System.out.println();
    }

    public static void sortedGeneralAbilities() {
        do {
            String[] options2 = {"1", "2", "3", "4", "5", "Exit"};
            var choice = JOptionPane.showOptionDialog(null,
                    "1. Strength\n" +
                            "2. Leadership\n" +
                            "3. Intelligence\n" +
                            "4. Politic\n" +
                            "5. Hitpoint\n" +
                            "Exit",
                    "Sorted General Abilities",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options2,
                    options2[0]);
            switch (choice) {
                case 0 -> {
                    sortStrength();
                    System.out.println("Sort by Strength:");
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getStrength());
                    }
                    System.out.println();
                }
                case 1 -> {
                    sortLeadership();
                    System.out.println("Sort by Leadership:");
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getLeadership());
                    }
                    System.out.println();
                }
                case 2 -> {
                    sortIntelligence();
                    System.out.println("Sort by Intelligence:");
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getIntelligence());
                    }
                    System.out.println();
                }
                case 3 -> {
                    sortPolitic();
                    System.out.println("Sort by Politic:");
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getPolitic());
                    }
                    System.out.println();
                }
                case 4 -> {
                    sortHitpoint();
                    System.out.println("Sort by Hit Point:");
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getHitpoint());
                    }
                    System.out.println();
                }
                case 5 -> {
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
        } while(true);
    }

    public static void viewGeneralAbilities() {
        do {
            var name = JOptionPane.showInputDialog("Enter the value you want to search for: ");
            if (name.equals("Exit")) {
                return;
            }
            boolean search = false;
            for (TreeNode general : generals) {
                if (general.getArmyData().getName().equals(name)) {
                    System.out.println("\n" + general.getArmyData().getStats());
                    search = true;
                }

            }
            if (!search) {
                System.out.println("\nGeneral not found. Press enter to continue.");
            }
        } while (true);
    }

    public static void searchByAbilityValue() {
        String[] options2 = {"1", "2", "3", "4", "5"};
        var sChoice = JOptionPane.showOptionDialog(null,
                "1. Strength\n" +
                        "2. Leadership\n" +
                        "3. Intelligence\n" +
                        "4. Politic\n" +
                        "5. Hitpoint",
                "Soldier Arrangement",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options2,
                options2[0]);
        int choice = sChoice + 1;

        var sTarget = JOptionPane.showInputDialog("Enter the value you want to search for: ");
        int target = Integer.parseInt(sTarget);

        // binary search
        int low = 0;
        int high = generals.size()-1;
        boolean search = false;

        while(low<=high){
            int middle = low + (high-low)/2;
            int value = 0;

            switch(choice){
                case 1 -> {
                    sortLeadership();
                    value = generals.get(middle).getArmyData().getLeadership();
                }
                case 2 -> {
                    sortStrength();
                    value = generals.get(middle).getArmyData().getStrength();
                }
                case 3 -> {
                    sortIntelligence();
                    value = generals.get(middle).getArmyData().getIntelligence();
                }
                case 4 -> {
                    sortPolitic();
                    value = generals.get(middle).getArmyData().getPolitic();
                }
                case 5 -> {
                    sortHitpoint();
                    value = generals.get(middle).getArmyData().getHitpoint();
                }
            }

            if(value == target){
                System.out.println("\nAttribute: " + choice + " Value: " + target);
                System.out.println(generals.get(middle).getArmyData().getName() + " " + value + "found.");
                search = true;
                break;
            } else if(value > target){
                high = middle-1;
            } else{
                low = middle +1;
            }
        }
        if (!search){
            System.out.println("\nAttribute: " + choice + " Value: " + target);
            System.out.println("Value not found");
        }
    }


    // Helper methods
    // 1. Soldier Arrangement
    public static ArrayList<TreeNode> suggestGeneralsByAbility(int attribute, String level) {
        ArrayList<ArrayList<TreeNode>> combinations = generateCombinations();
        ArrayList<TreeNode> suggestedGenerals = new ArrayList<>();
        int sum = 0;
        for (ArrayList<TreeNode> combination : combinations) {
            boolean meetsRequirement;
            sum = calculateSum(combination, attribute);
            switch (level) {
                case "S" -> meetsRequirement = sum >= 250;
                case "A" -> meetsRequirement = sum >= 220 && sum < 250;
                case "B" -> meetsRequirement = sum >= 190 && sum < 220;
                case "C" -> meetsRequirement = sum <= 190;
                default -> meetsRequirement = false;
            }
            if (meetsRequirement) {
                suggestedGenerals.addAll(combination);
                break;
            }
        }
        if (!isFoodHarvesting)
            System.out.println("Sum of abilities: " + sum);
        return suggestedGenerals;
    }

    private static ArrayList<ArrayList<TreeNode>> generateCombinations() {
        ArrayList<ArrayList<TreeNode>> combinations = new ArrayList<>();
        int n = generals.size();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    ArrayList<TreeNode> combination = new ArrayList<>();
                    combination.add(generals.get(i));
                    combination.add(generals.get(j));
                    combination.add(generals.get(k));
                    combinations.add(combination);
                }
            }
        }
        return combinations;
    }

    private static int calculateSum(ArrayList<TreeNode> generals, int attribute) {
        int sum = 0;
        for (TreeNode general : generals) {
            switch (attribute) {
                case 1 -> sum += general.getArmyData().getLeadership();
                case 2 -> sum += general.getArmyData().getStrength();
                case 3 -> sum += general.getArmyData().getIntelligence();
                case 4 -> sum += general.getArmyData().getPolitic();
                case 5 -> sum += general.getArmyData().getHitpoint();
            }
        }
        return sum;
    }

    //2. Sorting
    public static void sortStrength() {
        // selection sort
        for (int i = 0; i < generals.size()-1; i++){
            int min = i;
            for (int j = i+1; j < generals.size(); j++){
                if (generals.get(j).getArmyData().getStrength() < generals.get(min).getArmyData().getStrength()) {
                    min = j;
                }
            }
            TreeNode temp = generals.get(min);
            generals.set(min, generals.get(i));
            generals.set(i, temp);
        }
    }
    public static void sortLeadership() {
        // bubble sort
        for (int i = 0; i < generals.size()-1; i++){
            if (generals.get(i).getArmyData().getLeadership() > generals.get(i+1).getArmyData().getLeadership()) {
                TreeNode temp = generals.get(i);
                generals.set(i, generals.get(i+1));
                generals.set(i+1, temp);
            }
        }
    }

    public static void sortIntelligence() {
        // insertion sort
        for (int i = 1; i < generals.size(); i++){
            TreeNode temp = generals.get(i);
            int j = i-1;
            while (j >= 0 && generals.get(j).getArmyData().getIntelligence() > temp.getArmyData().getIntelligence()) {
                generals.set(j+1, generals.get(j));
                j--;
            }
            generals.set(j+1, temp);
        }
        sortedIntelligence.addAll(generals);
    }

    public static void sortPolitic() {
        // quicksort
        quickSort(0, generals.size()-1);
        sortedPolitics.addAll(generals);
    }
    public static void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi-1);
            quickSort(pi+1, high);
        }
    }
    public static int partition(int low, int high) {
        int pivot = generals.get(high).getArmyData().getPolitic();
        int i = low-1;
        for (int j = low; j < high; j++){
            if (generals.get(j).getArmyData().getPolitic() < pivot) {
                i++;
                TreeNode temp = generals.get(i);
                generals.set(i, generals.get(j));
                generals.set(j, temp);
            }
        }
        TreeNode temp = generals.get(i+1);
        generals.set(i+1, generals.get(high));
        generals.set(high, temp);
        return i+1;
    }

    public static void sortHitpoint(){
        // merge sort
        mergeSort(0, generals.size()-1);
    }
    public static void mergeSort(int low, int high) {
        if (low < high) {
            int mid = (low+high)/2;
            mergeSort(low, mid);
            mergeSort(mid+1, high);
            merge(low, mid, high);
        }
    }
    public static void merge(int low, int mid, int high) {
        int n1 = mid-low+1;
        int n2 = high-mid;
        ArrayList<TreeNode> left = new ArrayList<>();
        ArrayList<TreeNode> right = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            left.add(generals.get(low+i));
        }
        for (int j = 0; j < n2; j++) {
            right.add(generals.get(mid+1+j));
        }
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (left.get(i).getArmyData().getHitpoint() <= right.get(j).getArmyData().getHitpoint()) {
                generals.set(k, left.get(i));
                i++;
            } else {
                generals.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            generals.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            generals.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
