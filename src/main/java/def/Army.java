package def;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Army {
    static ArrayList<ArmyData> army = new ArrayList<>();
    // to get the army stats, you can use this static arraylist by running Army army = new Army();
    // for example, to get the strength of the first army, you can use:
    // Army.army.get(0).getStrength();

    static TreeNode root, mill, mgmt;
    // i think this tree is mostly to satisfy requirements of subquestion 1 and not much use later.
    // if you want to use this tree, you can use this static treenode after running Army.main(new String[]{});
    // but the generals lists are better managed in an arraylist anyway, so yeah

    public Army(){
        if (!army.isEmpty())
            army.clear();
        fillArmy();
    }
    public Army(boolean print){
        fillArmy();
        printArmy();
    }
    static void fillArmy(){
        try {
            Scanner in = new Scanner(new FileInputStream("src/main/resources/ArmyList.txt"));
            while (in.hasNextLine()){
                String str = in.nextLine();
                String[] data = str.split(",");
                ArmyData armyData = new ArmyData(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                army.add(armyData);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        root = new TreeNode(army.get(0));
        mill = new TreeNode(army.get(1));
        mgmt = new TreeNode(army.get(2));
        root.addChild(mill);
        root.addChild(mgmt);
        for (int i = 3; i < army.size(); i++){
            TreeNode child = new TreeNode(army.get(i));
            if (army.get(i).getIntelligence() > army.get(i).getStrength()) {
                mgmt.addChild(child);
            } else {
                mill.addChild(child);
            }
        }
    }

    public static void printArmy(){
        root.printTree();
    }
}
