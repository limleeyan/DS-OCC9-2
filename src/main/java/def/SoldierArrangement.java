package def;

import java.util.Scanner;
import java.util.ArrayList;

public class SoldierArrangement {
    static Scanner in = new Scanner(System.in);
    static Scanner in2 = new Scanner(System.in); // for string input
    private static ArrayList<TreeNode> generals = new ArrayList<>();
    static ArrayList<TreeNode> sortedPolitics = new ArrayList<>();
    static ArrayList<TreeNode> sortedIntelligence = new ArrayList<>();
    private static boolean isFoodHarvesting = false;

    public SoldierArrangement(){
        Army army = new Army(); // liddis first, no central class yet
        for (int i = 3; i < Army.army.size(); i++) {
            generals.add(new TreeNode(Army.army.get(i)));
        }
        boolean yes;
        do {
            yes = true;
            System.out.println("\n-------------------Choose your army: --------------------");
            System.out.println("1. Soldier Arrangement");
            System.out.println("2. Sorted General's Abilities");
            System.out.println("3. View 1 General's Abilities");
            System.out.println("4. Search by ability value");
            System.out.println("-1. Exit");
            System.out.print("Choice: ");
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> soldierArrangement();
                case 2 -> sortedGeneralAbilities();
                case 3 -> viewGeneralAbilities();
                case 4 -> searchByAbilityValue();
                case -1 -> yes = false;
                default -> System.out.println("Invalid input");
            }
        } while (yes);
    }

    public SoldierArrangement(boolean isFood){
        isFoodHarvesting = isFood;
        Army army = new Army(); // liddis first, no central class yet
        for (int i = 3; i < Army.army.size(); i++) {
            generals.add(new TreeNode(Army.army.get(i)));
        }
    }

    public static void soldierArrangement() {
        System.out.println("\n-------------------Soldier Arrangement: --------------------");
        System.out.println("Enter attribute: ");
        System.out.println("1. Strength");
        System.out.println("2. Leadership");
        System.out.println("3. Intelligence");
        System.out.println("4. Politic");
        System.out.println("5. Hitpoint");
        System.out.print("Choice: ");
        int choice = in.nextInt();

        System.out.print("Enter tier requirement (S/A/B/C): ");
        String tier = in.next();

        ArrayList<TreeNode> soldiers = suggestGeneralsByAbility(choice, tier);
        System.out.println("Suggested Generals: ");
        for (TreeNode soldier : soldiers) {
            System.out.println(soldier.getArmyData().getName());
        }
    }

    public static void sortedGeneralAbilities() {
        boolean yes;
        do {
            yes = true;
            System.out.println("\n-----------------Sort by: ----------------------");
            System.out.println("1. Strength");
            System.out.println("2. Leadership");
            System.out.println("3. Intelligence");
            System.out.println("4. Politic");
            System.out.println("5. Hitpoint");
            System.out.println("-1. Exit");
            System.out.print("Choice: ");
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    sortStrength();
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getStrength());
                    }
                }
                case 2 -> {
                    sortLeadership();
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getLeadership());
                    }
                }
                case 3 -> {
                    sortIntelligence();
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getIntelligence());
                    }
                }
                case 4 -> {
                    sortPolitic();
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getPolitic());
                    }
                }
                case 5 -> {
                    sortHitpoint();
                    for (TreeNode general : generals) {
                        System.out.println(general.getArmyData().getName() + " " + general.getArmyData().getHitpoint());
                    }
                }
                case -1 -> yes = false;
                default -> System.out.println("Invalid input");
            }
        } while(yes);
    }

    public static void viewGeneralAbilities() {
        boolean yes;
        do {
            yes = true;
            System.out.println("\nChoose a general: (Enter \"Exit\" to exit)");
            String name = in2.nextLine();
            if (name.equals("Exit")) {
                yes = false;
            }
            boolean search = false;
            for (TreeNode general : generals) {
                if (general.getArmyData().getName().equals(name)) {
                    System.out.println(general.getArmyData().getStats());
                    search = true;
                }

            }
            if (search) {
                System.out.println("Press enter to continue");
            } else {
                System.out.println("General not found. Press enter to continue.");
            }
        } while (yes);
    }

    public static void searchByAbilityValue() {
        System.out.println("Enter the attribute you want to search by: ");
        System.out.println("1. Leadership");
        System.out.println("2. Strength");
        System.out.println("3. Intelligence");
        System.out.println("4. Politic");
        System.out.println("5. Hitpoint");
        System.out.print("Choice: ");
        int choice = in.nextInt();
        System.out.println("Enter the value you want to search for: ");
        System.out.print("Value: ");
        int target = in.nextInt();

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
                default -> System.out.println("Unexpected value: " + choice);
            }

            if(value == target){
                System.out.println(generals.get(middle).getArmyData().getName() + " " + value);
                search = true;
                break;
            } else if(value > target){
                high = middle-1;
            } else{
                low = middle +1;
            }
        }
        if (!search){
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
