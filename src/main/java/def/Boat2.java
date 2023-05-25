package def;

public class Boat2 {
    private StrawMen2 left, right, front, back;

    public Boat2() {
    }

    public Boat2(int left, int right, int front, int back) { // representing the number of straw men for each direction
        this.left = new StrawMen2(left);
        this.right = new StrawMen2(right);
        this.front = new StrawMen2(front);
        this.back = new StrawMen2(back);
    }

    // update the number of straw men for each direction
    public void setLeft(int left) {
        this.left = new StrawMen2(left);
    }

    public void setRight(int right) {
        this.right = new StrawMen2(right);
    }

    public void setFront(int front) {
        this.front = new StrawMen2(front);
    }

    public void setBack(int back) {
        this.back = new StrawMen2(back);
    }

    public boolean canCaptureArrow(String direction) {
        if (direction.equals("left")) {
            return left.canCaptureArrow();
        } else if (direction.equals("right")) {
            return right.canCaptureArrow();
        } else if (direction.equals("front")) {
            return front.canCaptureArrow();
        } else if (direction.equals("back")) {
            return back.canCaptureArrow();
        } else {
            return false;
        }
    }

    public int ArrowCaptured(String direction, int arrow) {
        int arrowGet = 0;

        if (direction.equals("left") && left.canCaptureArrow()) {
            arrowGet = arrow * left.getRemainingStrawmen() / 100;
        } else if (direction.equals("right") && right.canCaptureArrow()) {
            arrowGet = arrow * right.getRemainingStrawmen() / 100;
        } else if (direction.equals("front") && front.canCaptureArrow()) {
            arrowGet = arrow * front.getRemainingStrawmen() / 100;
        } else if (direction.equals("back") && back.canCaptureArrow()) {
            arrowGet = arrow * back.getRemainingStrawmen() / 100;
        }

        return arrowGet;
    }
}

