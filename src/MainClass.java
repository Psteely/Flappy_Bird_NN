import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;
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
    int birdPopulation = 600;
    ArrayList<Bird> Birds = new ArrayList<>(birdPopulation);
    int pipeFrequency;
    int generation;
    boolean dead;
    int score = 0;

    public void setup() {
        processing = this;

        surface.setSize(900, 600);

        generation = 1;



        Pipes.add(new Pipe());
        //nextGen();     //   set up and initial generation
        for (int i = 0; i < birdPopulation; i++) {
            Birds.add(new Bird());
        }

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
            dead = p.pipeDone();
            if (dead) {
                score ++;
                MainClass.processing.println("Score = " + score);
            }
        }
        for (int i = 0; i < Pipes.size(); i++) {
            if (!Pipes.get(i).pipeAlive) {
                Pipes.remove(i);
                break;    // Should only need to remove one pipe at a time.
            }
        }

        if (pipeFrequency > 100) {
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

    public void nextGen(NeuralNetwork nn_) {
        Birds = null;
        generation++;
        score = 0;
        System.out.println("Generation = " + generation);
        Birds = new ArrayList<>(birdPopulation);
        for (int i = 0; i < birdPopulation; i++) {
          // NeuralNetwork nn1 = new NeuralNetwork(nn_);
           NeuralNetwork nn1 = NeuralNetwork.nncopy(nn_);
           nn1.mutate(.5f);
            Birds.add(new Bird(nn1));
        }

    }

    public void resetPipes() {
        Pipes.removeAll(Pipes);
        pipeFrequency = 0;
        Pipes.add(new Pipe());
    }

    Bird bestBird() {
        Bird bestBird = new Bird();
        int highScore = 0;
        for (Bird b : Birds) {
            if (b.score > highScore) {
                highScore = b.score;
                bestBird = b;
            }
        }
        return bestBird;
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
            //  Which is our best bird

            Bird topBird;
            topBird = bestBird();
            //println(topBird.score);


            NeuralNetwork nnn = new NeuralNetwork(topBird.nn);

            nextGen(nnn);
            //PApplet.println("new gen");
        }
    }
}







