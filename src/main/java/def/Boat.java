package def;

public class Boat{
    private StrawMen left, right, front, back;
    int strawmen; //number of straw men available

    public Boat() {
    }

    public Boat(int left, int right, int front, int back) { //representing the number of straw men for each direction
        this.left = new StrawMen(left);
        this.right = new StrawMen(right);
        this.front = new StrawMen(front);
        this.back = new StrawMen(back);
    }

    //update the number of straw men for each direction
    public void setLeft(int left) {
        this.left = new StrawMen(left);
    }

    public void setRight(int right) {
        this.right = new StrawMen(right);
    }

    public void setFront(int front) {
        this.front = new StrawMen(front);
    }

    public void setBack(int back) {
        this.back = new StrawMen(back);
    }

    public int ArrowCaptured(String direction, int arrow) {
        int arrowGet = 0;

        if (direction.equals("left")) {
            strawmen = left.getRemainingStrawmen();
            arrowGet = arrow * strawmen / 100;
        } else if (direction.equals("right")) {
            strawmen = right.getRemainingStrawmen();
            arrowGet = arrow * strawmen / 100;
        } else if (direction.equals("front")) {
            strawmen = front.getRemainingStrawmen();
            arrowGet = arrow * strawmen / 100;
        } else if (direction.equals("back")) {
            strawmen = back.getRemainingStrawmen();
            arrowGet = arrow * strawmen / 100;
        }

        return arrowGet;
    }
}

