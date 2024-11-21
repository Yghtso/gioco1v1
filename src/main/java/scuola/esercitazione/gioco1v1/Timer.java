package scuola.esercitazione.gioco1v1;

import javafx.scene.control.Label;

public class Timer extends Thread {

    private int seconds;
    private int minutes;
    private boolean running;
    private boolean finito=false;
    private Label LabelTimer;
    

    public Timer(int seconds,Label labelTimerBianco) {
        this.seconds = seconds;
        this.running = true;
        this.LabelTimer = labelTimerBianco;
    }


    @Override
    public void run() {
        while (seconds > 0 && running) {
            try {

                Thread.sleep(1000);
                seconds--;
            } catch (InterruptedException e) {
                break;
            }
        }

        if (seconds == 0) {
            finito=true;
        }
    }

    public void stopTimer() {
        running = false;
    }

    public void startTimer(){

        running=true;

    }

    public int getSecondsRemaining(){
        return seconds%60;
    }

    public int getMinutesRemaining(){
        return seconds/60;
    }

    public boolean getFinito(){
        return finito;
    }
}
