package def;

import java.util.*;
public class ArrowCaptured {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [] strawMen = new int [4]; //store the initial efficiency levels of the target in the order: front, left, right, back
        int [] times = {0, 0, 0, 0}; //keep track of the number of times each target has been hit
        System.out.println("Number of straw men");
        System.out.print("Front: ");
        strawMen[0] = sc.nextInt();
        System.out.print("Left: ");
        strawMen[1] = sc.nextInt();
        System.out.print("Right: ");
        strawMen[2] = sc.nextInt();
        System.out.print("Back: ");
        strawMen[3] = sc.nextInt();

        // Read the number of arrows in each wave
        System.out.print("Enter the number of wave of arrows: ");
        int wave = sc.nextInt();
        ArrayList<Integer> arrows = new ArrayList<>();
        System.out.println("Enter number of arrows in each wave: ");
        for(int i=0; i<wave; i++){
            arrows.add(sc.nextInt());
        }
        System.out.print("Arrows: "+arrows);
        System.out.println();

        List<Integer> arrowReceived = new ArrayList<>(); //store the number of arrows received
        List<String> boatDirection = new ArrayList<>(); //store the direction of the boat corresponding to each wave
        int total=0; //total number of arrows captured

        int arrowGet;
        for (int i=0; i<wave; i++){
            int index =max(strawMen); //return the index of the straw men with the highest efficiency
            boatDirection.add(direction(index)); //determine the direction of the boat
            arrowGet=(int)(arrows.get(i)*((double)strawMen[index]/100));
            arrowReceived.add(arrowGet);
            total+=arrowGet;
            times[index]++;
            strawMen[index]=efficiency(times[index], strawMen[index]); //update the new efficiency of the straw men
        }

        System.out.println("Boat direction: "+boatDirection);
        System.out.println("Arrow Received: "+arrowReceived);
        System.out.println("Total = "+total);


    }
    //return the index of the straw men with the highest efficiency
    public static int max(int [] strawMen){
        int max = strawMen[0];
        int index = 0;
        for (int i=1; i<strawMen.length; i++){
            if (strawMen[i]>max){
                max=strawMen[i];
                index=i;
            }
        }
        return index;
    }

    //calculate and return the new efficiency of a straw men based on the number of times it has been hit.
    public static int efficiency(int times, int strawMen){
        double eff=0;
        if (times==1){
            eff = (int)strawMen*0.8;
        }
        else if (times==2){
            eff = strawMen/2;
        }
        return (int)eff;
    }

    public static String direction(int index){
        if (index==0){
            return "front";
        }
        else if (index==1){
            return "left";
        }
        else if (index==2){
            return "right";
        }
        else{
            return "back";
        }
    }

}
