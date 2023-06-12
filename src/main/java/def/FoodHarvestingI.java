package def;

import java.util.ArrayList;

public class FoodHarvestingI {
    private static double maxFood;
    private static int bestIndex;
    private static ArrayList<TreeNode> bestTeam;
    private static String bestTeamName;

//    public static void main(String[] args) {
    public FoodHarvestingI() {
        System.out.println("-------------------- Food Harvesting I --------------------");
        new SoldierArrangement(true);
        new FoodHarvesting(true);

        // calculate total number of food can get
        int nodeWithFood = 10 - FoodHarvesting.nodesWithoutFood.size();
        int food = nodeWithFood*100;

        // initialise bestIndex and maxFood
        bestIndex=0;
        maxFood = food*getBuff(0);

        for (int i=1; i<nodeWithFood; i++){
            double foodProduction = food*getBuff(i);
            if (foodProduction>maxFood){
                maxFood = foodProduction;
                bestIndex = i;
            }
        }

        getBestTeam();
        System.out.println("\n--------------------Maximum Food Production--------------------");
        System.out.println(bestTeamName);
        System.out.println("Generals: "+bestTeam);
        System.out.println("Maximum food production: "+maxFood);
    }

    public static void getBestTeam(){
        switch (bestIndex){
            case 0:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(3,"S");
                bestTeamName = "S team in politic";
                break;
            case 1:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(3,"A");
                bestTeamName = "A team in politic";
                break;
            case 2:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(3,"B");
                bestTeamName = "B team in politic";
                break;
            case 3:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(3,"C");
                bestTeamName = "C team in politic";
                break;
            case 4:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(4,"S");
                bestTeamName = "S team in intelligence";
                break;
            case 5:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(4,"A");
                bestTeamName = "A team in intelligence";
                break;
            case 6:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(4,"B");
                bestTeamName = "B team in intelligence";
                break;
            case 7:
                bestTeam = SoldierArrangement.suggestGeneralsByAbility(4,"C");
                bestTeamName = "C team in intelligence";
                break;
            default:
        }
    }

    private static double getBuff(int index){
        double buff;
        switch (index){
            case 0 -> buff=2.0;
            case 1 -> buff=1.5;
            case 2 -> buff=1.2;
            case 3, 6 -> buff=1.0;
            case 4 -> buff=1.8;
            case 5 -> buff=1.3;
            case 7 -> buff=0.8;
            default -> buff=0;
        }
        return buff;
    }
}
