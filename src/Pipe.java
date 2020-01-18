import processing.core.PVector;

//import java.util.ArrayList;

//import java.util.Random;

public class Pipe {
    PVector pos;
    PVector vel;
    int pipeWidth;
    int pipeGap;
    int pipeGapStart;
    boolean pipeAlive = true;


    Pipe() {
        pipeWidth = 50;
        pipeGap = 150;
        pipeGapStart = (int) MainClass.processing.random(0, MainClass.processing.height - pipeGap);
        // MainClass.processing.println(MainClass.processing.height - pipeGap);
        vel = new PVector(-5, 0);
        pos = new PVector(MainClass.processing.width, 0);  //start the pipe on the far right.

    }

    void showPipe() {
        MainClass.processing.fill(255);
        MainClass.processing.rect(pos.x, pos.y, pipeWidth, pipeGapStart);
        MainClass.processing.rect(pos.x, pipeGapStart + pipeGap, pipeWidth, MainClass.processing.height);

    }

    void movePipe() {
        pos = pos.add(vel);
    }

    void pipeDone() {
        if (pos.x < -30) {
            pipeAlive = false;
        }
    }

}
