import processing.core.PVector;

public class Bird {
    PVector pos;
    PVector gravity;
    PVector lift;
    boolean alive = true;
    Bird () {

        pos = new PVector(40,MainClass.processing.height/2f);
        gravity = new PVector(0,5);
        lift = new PVector(0,-15);
    }

    void showBird() {
        MainClass.processing.fill(255);
        MainClass.processing.ellipse(pos.x,pos.y,20,20);

    }
    void update() {
        if (alive) {
            pos = pos.add(gravity);
        }
    }

    void lift() {
        if (alive) {
            pos = pos.add(lift);
        }
    }
    void checkForDead () {
        if (pos.y == MainClass.processing.height || pos.y == 0) {
            alive = false;
        }
    }
    }

