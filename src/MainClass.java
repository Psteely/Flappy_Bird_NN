import processing.core.PApplet;

import java.util.ArrayList;
//import processing.data.Table;
//import processing.data.TableRow;
//
//import java.util.ArrayList;

public class MainClass extends PApplet {
    public static PApplet processing;

    public static void main(String[] args) {
        PApplet.main("MainClass", args);
    }

    ArrayList<Pipe> Pipes = new ArrayList<>();
    ArrayList<Bird> Birds = new ArrayList<>(100);
    int pipeFrequency;
    int birdPopulation;

    public void setup() {
        processing = this;

        surface.setSize(900, 600);
        birdPopulation = 100;

        Pipes.add(new Pipe());
        nextGen();     //   set up and initial generation


    }

    public void draw() {

        pipeFrequency++;
        background(0);
        for (Bird b : Birds) {
            b.showBird();
            b.checkForDead(Pipes);
            b.update();
            b.lift(Pipes);
        }
        for (Pipe p : Pipes) {
            p.showPipe();
            p.movePipe();
            p.pipeDone();
        }
        for (int i = 0; i < Pipes.size(); i++) {
            if (!Pipes.get(i).pipeAlive) {
                Pipes.remove(i);
                break;    // Should only need to remove one pipe at a time.
            }
        }

        if (pipeFrequency > 150) {
            Pipes.add(new Pipe());
            pipeFrequency = 0;
        }
        checkForNextGen();

    }

    public void keyPressed() {
        if (key == 'n') {   //  only for testing
            noLoop();
        }
        if (key == 's') {   //  only for testing
            loop();
        }

    }

    public void nextGen() {
        for (int i = 0; i < birdPopulation; i++) {
            Birds.add(new Bird());
        }

    }

    public void resetPipes () {
        Pipes.removeAll(Pipes);
        pipeFrequency = 0;
        Pipes.add(new Pipe());
    }

    public void checkForNextGen() {
        int count = 0;
        for (int i = 0; i < Birds.size(); i++) {
            if (!Birds.get(i).alive) {
                count++;
            }
        }
        //println(count);
        if (count == birdPopulation) {  //population is dead.
            Birds.removeAll(Birds);
            resetPipes();
            nextGen();
        }
    }
}







