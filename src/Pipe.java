import processing.core.PVector;

import java.util.Random;

public class Pipe {
    PVector pos;
    PVector vel;
    int pipeWidth;
    int pipeGap;
    int pipeGapStart;

    Pipe () {
        pipeWidth = 30;
        pipeGap = 10;
        pipeGapStart = (int) MainClass.processing.random(0, (int) (MainClass.processing.height-pipeGap));
        vel = new PVector(-3,0);
        pos = new PVector(MainClass.processing.width,0);  //start the pipe on the far right.

    }

    void showPipe() {
        MainClass.processing.rect(pos.x,pos.y,pipeWidth,pipeGapStart);
        MainClass.processing.rect(pos.x,pipeGapStart+pipeGap,pipeWidth,MainClass.processing.height);

    }

    void movePipe() {
        pos = pos.add(vel);
    }

}
