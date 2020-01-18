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
    Bird b;
    int pipeFrequency;

    public void setup() {
        processing = this;

        surface.setSize(900, 600);
        b = new Bird();
        Pipes.add(new Pipe());

        //MainClass.processing.frameRate(20);
    }

    public void draw() {
        pipeFrequency++;
        background(0);
        b.showBird();
        b.checkForDead(Pipes);
        b.update();
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

        if (keyPressed) {

            if (keyCode == 38) {
                b.lift();
            }
            if (keyCode == 40) {   //  only for testing
                b.drop();
            }

        }
    }

    public void keyPressed() {
        if (key == 'n') {   //  only for testing
            noLoop();
        }
        if (key == 's') {   //  only for testing
            loop();
        }

    }

}





