package def;

// an ADT to store army data.
// normally the Generals' data are stored as a TreeNode form.
// To access its stats, you can use the getArmyData() method in TreeNode and get[stats] method of this class.

public class ArmyData {
    private String name;
    private String type;
    private int strength;
    private int leadership;
    private int intelligence;
    private int politic;
    private int hitpoint;

    public ArmyData(String n, String t, int s, int l, int i, int p, int h) {
        this.name = n;
        this.type = t;
        this.strength = s;
        this.leadership = l;
        this.intelligence = i;
        this.politic = p;
        this.hitpoint = h;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStats() {
        return "Strength: " + strength + "\nLeadership: " + leadership + "\nIntelligence: " + intelligence + "\nPolitic: " + politic + "\nHitpoint: " + hitpoint;
    }

    public int getStrength() {
        return strength;
    }

    public int getLeadership() {
        return leadership;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPolitic() {
        return politic;
    }

    public int getHitpoint() {
        return hitpoint;
    }
}
