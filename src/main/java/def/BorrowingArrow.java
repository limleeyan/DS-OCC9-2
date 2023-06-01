package def;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowingArrow {
    Boat ship = new Boat();
    static Random random = new Random();
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> arrow = new ArrayList<>();
    ArrayList<String> direction = new ArrayList<>();
    ArrayList<Integer> arrowReceived = new ArrayList<>();
    int wave, totalArrow=0;

    public BorrowingArrow() {
        NumberOfStrawmen();
    }

    public void NumberOfStrawmen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of straw men");
        System.out.print("Front: ");
        ship.setFront(sc.nextInt());
        System.out.print("Left: ");
        ship.setLeft(sc.nextInt());
        System.out.print("Right: ");
        ship.setRight(sc.nextInt());
        System.out.print("Back: ");
        ship.setBack(sc.nextInt());
        //randomize the number of attack from enemy
        wave = random.nextInt(6)+5;
        for (int i = 0; i < wave; i++) {
            // Randomize the number of arrows from the enemy
            if (i == 0)
                arrow.add(2000);
            else
                arrow.add(arrow.get(i - 1) * (random.nextInt(21)+60) / 100);

            // Randomize direction of the ship
            direction.add(randomDirection());

            arrowReceived.add(ship.ArrowCaptured(direction.get(i), arrow.get(i)));
            totalArrow += arrowReceived.get(i);
        }

        printArray("Arrow", arrow);
        printArray("Boat direction", direction);
        printArray("Arrow received", arrowReceived);
        System.out.println(sb.toString());
        System.out.println("Total = "+totalArrow);
    }

    public void printArray(String prompt, ArrayList<?> list) {
        System.out.print(prompt + ": [");
        if (!list.isEmpty()) {
            System.out.print(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                System.out.print(" " + list.get(i));
            }
        }
        System.out.println("]");
    }

    public static String randomDirection() {
        int index = random.nextInt(4);
        if (index == 0)
            return "left";
        else if (index == 1)
            return "right";
        else if (index == 2)
            return "front";
        else
            return "back";
    }
}
