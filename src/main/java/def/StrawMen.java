package def;

import java.util.Random;
import java.util.Stack;

public class StrawMen{
    private int total; //total number of straw men
    private Random random = new Random(); //generate random number
    Stack<Integer> numOfStrawmen = new Stack<>(); //represent number of straw men remaining at different efficiency levels.
    Stack<Integer> efficiency = new Stack<>(); // representing the efficiency levels

    public StrawMen(int total) {
        efficiency.push(40); //40% efficiency
        numOfStrawmen.push(total * efficiency.peek() / 100);
        efficiency.push(80); //80% efficiency
        numOfStrawmen.push(total * efficiency.peek() / 100);
        efficiency.push(100); //100% efficiency
        numOfStrawmen.push(total * efficiency.peek() / 100);
    }

    public int getRemainingStrawmen() {
        if(numOfStrawmen.empty()){
            return 0;
        }
        return numOfStrawmen.pop();
    }

    public int getCurrentEfficiency() {
        if(efficiency.empty()){
            return 0;
        }
        return efficiency.pop();
    }
}
