import processing.core.PApplet;
//import processing.data.Table;
//import processing.data.TableRow;
//
//import java.util.ArrayList;

public class MainClass extends PApplet {
    public static PApplet processing;
    public static void main(String[] args) {
        PApplet.main("MainClass", args);
    }
    Bird b;
    Pipe p;
    public void setup() {
        processing = this;

        surface.setSize(900 , 600);
        b = new Bird();
        p = new Pipe();


    }
    public void draw() {
        background(0);
        b.showBird();
        p.showPipe();
        b.update();
        p.movePipe();
        b.checkForDead();
        if (keyPressed) {
            if (keyCode == 38) {
                b.lift();
            }
            }
        }

    }

