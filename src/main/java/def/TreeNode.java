package def;

import java.util.ArrayList;
import java.util.List;

// an ADT to store army data.
// The Generals' data are stored in this form. To get its stats, use the getArmyData() here and get[stats] method of ArmyData class.

public class TreeNode {
    private ArmyData data;
    private List<TreeNode> children;

    public TreeNode(ArmyData data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    //print tree
    public void printTree() {
        System.out.println(data.getName());
        for (TreeNode child : children) {
            System.out.println("  " + child.data.getName());
            for (TreeNode grandchild : child.children) {
                System.out.println("    " + grandchild.data.getName());
            }
        }
    }

    public ArmyData getArmyData() {
        return data;
    }

    public String toString(){
        return data.getName();
    }
}
