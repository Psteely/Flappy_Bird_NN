import processing.core.PVector;

import java.util.ArrayList;

public class Bird {
    PVector pos;
    PVector gravity;
    PVector lift;
    PVector drop;    /// just for testing
    PVector dead;
    boolean alive = true;
    int birdSize = 20;

    Bird() {

        pos = new PVector(60, MainClass.processing.height / 2f);
        gravity = new PVector(0, 5);
        lift = new PVector(0, -10);
        drop = new PVector(0, 10);    /// just for testing
        dead = new PVector(-3, 0);
    }

    void showBird() {
        //MainClass.processing.fill(255);
        MainClass.processing.ellipse(pos.x, pos.y, birdSize, birdSize);

    }

    void update() {
        if (alive) {
            pos = pos.add(gravity);
        } else {
            pos = pos.add(dead);
        }
    }

    void lift() {
        if (alive) {
            pos = pos.add(lift);
        }
    }

    void drop() {
        if (alive) {
            pos = pos.add(drop);
        }
    }

    void checkForDead(ArrayList p_) {
        if (pos.y == MainClass.processing.height || pos.y == 0) {
            alive = false;
        }

        Pipe p;
        p = (Pipe) p_.get(0);
        if (p.pos.x < (pos.x + birdSize / 2f)) {     //  is the pipe at the bird
            if (p.pipeGapStart > (pos.y - birdSize / 2f)) {   //  is the bird hitting the top pipe
                // MainClass.processing.fill(255, 0, 0);
                alive = false;
                // MainClass.processing.noLoop();
            }
            if (p.pipeGapStart + p.pipeGap < pos.y + birdSize / 2f) {    // is the bird hitting the bottom
                //MainClass.processing.fill(255, 0, 0);
                alive = false;
                //MainClass.processing.noLoop();
            }
        } else MainClass.processing.fill(255);
    }
}

