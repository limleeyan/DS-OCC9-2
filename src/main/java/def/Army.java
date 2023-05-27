package def;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Army {
    static ArrayList<ArmyData> army = new ArrayList<>();
    static TreeNode root, mill, mgmt;
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new FileInputStream("src/main/java/def/ArmyList.txt"));
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
        // root.printTree();
    }
}
