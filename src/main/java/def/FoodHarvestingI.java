package def;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FoodHarvestingI {
    static Scanner sc = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    private static double[] buff = new double[2];
    private static String[] teamType = new String[2];

//    public static void main(String[] args) {
    public FoodHarvestingI() {
        while (true) {
            String[] options = {"1", "2", "Exit"};
            var select = JOptionPane.showOptionDialog(null,
                    "1. Maximum Food Production\n" +
                            "2. Food Production by Selected Generals\n" +
                            "Exit",
                    "Food Harvesting I",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (select == 2) return;

            new SoldierArrangement(true);
            ArrayList<TreeNode> generals = SoldierArrangement.generals;
            new FoodHarvesting(true);

            // calculate total number of food can get
            int nodeWithFood = 10 - FoodHarvesting.nodesWithoutFood.size();
            int food = nodeWithFood * 100;

            switch (select) {
                case 0 -> maxFoodProduction(food, generals);
                case 1 -> foodSelectGenerals(food, generals);
            }
        }
    }

    private static void maxFoodProduction(int food, ArrayList<TreeNode> generals){
        ArrayList<TreeNode> bestTeam = getBestTeam(generals);
        double totalFood = food*buff[0]*buff[1];
        System.out.println("\n--------------------Maximum Food Production--------------------");
        System.out.println(teamType[0]+" & "+teamType[1]);
        System.out.println("Generals: "+bestTeam);
        System.out.println("Maximum food production: "+totalFood);
        System.out.println();
    }

    private static void foodSelectGenerals(int food, ArrayList<TreeNode> generals){
        // let user choose generals
        System.out.println("1. Xu Sheng\t\t2. Zhu Ge Jin\t3. Lu Su\t\t4. Tai Shi Ci\t5. Xiao Qiao\n" +
                "6. Da Qiao\t\t7. Zhou Tai\t\t8. Gan Ning\t\t9. Lu Meng\t\t10. Huang Gai");
        System.out.print("Please select 3 generals by entering their index: ");
        int[] index = new int[3];
        for (int i=0; i<index.length; i++){
            index[i] = sc2.nextInt();
        }

        ArrayList<TreeNode> selectedGenerals = new ArrayList<>();
        for (int i: index){
            selectedGenerals.add(generals.get(i-1));
        }
        getBuff(calculateSum(selectedGenerals,0),0); //get buff for politic team
        getBuff(calculateSum(selectedGenerals,1),1); // get buff for intelligence team
        double totalFood = food*buff[0]*buff[1];
        System.out.println("\n--------------------Food Production by Selected Generals--------------------");
        System.out.println(teamType[0]+" & "+teamType[1]);
        System.out.println("Generals: "+selectedGenerals);
        System.out.println("Total food production: "+totalFood);
        System.out.println();
    }

    private static void getBuff(int sum, int attribute){
        // attribute: 0 for Politic, 1 for Intelligence
        if (attribute==0) {
            if (sum >= 250) {
                buff[0] = 2.0;
                teamType[0] = "S Team in Politic";
            }
            else if (sum >= 220) {
                buff[0] = 1.5;
                teamType[0] = "A Team in Politic";
            }
            else if (sum >= 190) {
                buff[0] = 1.3;
                teamType[0] = "B Team in Politic";
            }
            else {
                buff[0] = 1.0;
                teamType[0] = "C Team in Politic";
            }
        }
        else if (attribute==1){
            if (sum>=250) {
                buff[1] = 1.8;
                teamType[1] = "S Team in Intelligence";
            }
            else if (sum>=220) {
                buff[1] = 1.3;
                teamType[1] = "A Team in Intelligence";
            }
            else if (sum>=190) {
                buff[1] = 1.0;
                teamType[1] = "B Team in Intelligence";
            }
            else {
                buff[1] = 0.8;
                teamType[1] = "C Team in Intelligence";
            }
        }
    }

    public static ArrayList<TreeNode> getBestTeam(ArrayList<TreeNode> generals){
        ArrayList<ArrayList<TreeNode>> SPoliticTeams = new ArrayList<>();
        int n = generals.size();

        //get all combinations
        ArrayList<ArrayList<TreeNode>> combinations = new ArrayList<>();
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

        // get combinations of S team in politic
        int sumPolitic = 0;
        for (ArrayList<TreeNode> combination : combinations) {
            sumPolitic = calculateSum(combination,0);
            if (sumPolitic>=250)
                SPoliticTeams.add(combination);
        }

        // get S team in politic with highest intelligence
        int maxSumIntelligence = 0;
        ArrayList<TreeNode> bestTeam = new ArrayList<>();
        for (ArrayList<TreeNode> teams: SPoliticTeams){
            int sum = calculateSum(teams, 1);
            if (sum>maxSumIntelligence){
                maxSumIntelligence = sum;
                bestTeam = teams;
            }
        }

        getBuff(calculateSum(bestTeam,0),0); //get buff for politic team
        getBuff(maxSumIntelligence,1); // get buff for intelligence team
        return bestTeam;
    }

    private static int calculateSum(ArrayList<TreeNode> generals, int attribute) {
        // attribute: 0 for Politic, 1 for Intelligence
        int sum = 0;
        for (TreeNode general : generals) {
            switch (attribute) {
                case 0 -> sum += general.getArmyData().getPolitic();
                case 1 -> sum += general.getArmyData().getIntelligence();
            }
        }
        return sum;
    }
}
