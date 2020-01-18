import processing.core.PApplet;
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
    int score = 0;

    NeuralNetwork nn;

    float[] inputs = new float[5];
    float[] result = new float[1];

    Bird() {

        pos = new PVector(200, MainClass.processing.random(0,MainClass.processing.height / 2f));
        gravity = new PVector(0, 5);
        lift = new PVector(0, -10);
        drop = new PVector(0, 10);    /// just for testing
        dead = new PVector(-5, 0);
        nn = new NeuralNetwork(5,16,1);
        nn.setSoftMaxOff();  //  we only have one output so softmax is a bit useless.
    }

    void showBird() {
        //PApplet.println(alive);
        MainClass.processing.fill(255,100);
        if (!alive) {
            MainClass.processing.fill(255,0,0);
        }
        MainClass.processing.ellipse(pos.x, pos.y, birdSize, birdSize);
        //PApplet.println(score);

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

    void lift(ArrayList<Pipe> p_) {
        if (alive) {
            Pipe p;
            p = p_.get(0);
           inputs[0] = pos.y;
           inputs[1] = p.pos.x;
           inputs[2] = p.pipeGapStart;
           inputs[3] = p.pipeGap;
           inputs[4] = p.pipeWidth;
           result = nn.predict(inputs);
           //PApplet.println(result[0]);
           if (result[0] >0.5) {
               pos=pos.add(lift);
           }
        }
    }

    void drop() {                    /// just for testing
        if (alive) {
            pos = pos.add(drop);
        }
    }

    void checkForDead(ArrayList<Pipe> p_) {
        if (pos.y > MainClass.processing.height || pos.y < 0 ) {
            alive = false;
        }

        Pipe p;
        p = p_.get(0);
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
        }

        if (p.pos.x == 0 & alive) {
            score++;
        }
    }
}


