package def;

import java.util.Random;
import java.util.Stack;

public class StrawMen2 {
    private int total; // total number of straw men
    private Random random = new Random(); // generate random number
    private Stack<Integer> numOfStrawmen = new Stack<>(); // represent number of straw men remaining at different efficiency levels.
    private Stack<Integer> efficiency = new Stack<>(); // representing the efficiency levels
    private int remainingUses = 2;

    public StrawMen2(int total) {
        efficiency.push(50); // 50% efficiency
        numOfStrawmen.push(total * efficiency.peek() / 100);
        efficiency.push(100); // 100% efficiency
        numOfStrawmen.push(total * efficiency.peek() / 100);
    }

    public boolean canCaptureArrow() {
        return !numOfStrawmen.empty() && remainingUses > 0;
    }

    public int getRemainingStrawmen() {
        if (canCaptureArrow()) {
            remainingUses--;
            return numOfStrawmen.pop();
        } else {
            return 0;
        }
    }

    public int getCurrentEfficiency() {
        if (efficiency.empty()) {
            return 0;
        }
        return efficiency.peek();
    }
}
