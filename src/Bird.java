import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


import java.util.ArrayList;

public class Bird {
    //PImage flappy;
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
        nn = new NeuralNetwork(5, 16, 1);
        //PImage flappy = MainClass.processing.loadImage("../../bigdata/FlappyBird.jpg");
        birdBirth();
    }
    Bird(NeuralNetwork nn_) {
        nn = new NeuralNetwork(nn_);
        birdBirth();
    }

    void birdBirth() {
        //pos = new PVector(200, MainClass.processing.random(0, MainClass.processing.height / 2f));
        pos = new PVector(100,MainClass.processing.height/2);
        gravity = new PVector(0, 5);
        lift = new PVector(0, -10);
        drop = new PVector(0, 10);    /// just for testing
        dead = new PVector(-5, 0);

        nn.setSoftMaxOff();

    }

    void showBird() {
        //PApplet.println(alive);
        MainClass.processing.fill(255, 100);
        if (!alive) {
            MainClass.processing.fill(255, 0, 0);
        }
        MainClass.processing.ellipse(pos.x, pos.y, birdSize, birdSize);
        //MainClass.processing.image(flappy,pos.x,pos.y,birdSize,birdSize);

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
            if (result[0] > 0.5) {
                pos = pos.add(lift);
            }
        }
    }

    void drop() {                    /// just for testing
        if (alive) {
            pos = pos.add(drop);
        }
    }

    void checkForDead(ArrayList<Pipe> p_) {
        if (pos.y > MainClass.processing.height || pos.y < 0) {
            alive = false;
        }

        Pipe p;
        p = p_.get(0);
        //PApplet.println("p.pos.x " + p.pos.x + " pos.x " + pos.x + " pipeWidth " + p.pipeWidth);
        if (p.pos.x < (pos.x + birdSize / 2f)) {     //  is the pipe at the bird
            if (p.pos.x + p.pipeWidth > (pos.x + birdSize)) {                               // is the pipe not pas the bird
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
        }

        if (p.pos.x < 0 & alive) {
            score++;
            //PApplet.println(score);
        }
    }
}


