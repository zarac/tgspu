package rolfVrNinja;

import java.util.Random;

public class Player {
    private Random rand;
    private int choice;
    private int res;
    private int score;

    //Konstruktor
    public Player(){
        rand = new Random();
        choice = 0;
        res = 0;
        score = 0;
    }

    //retunerar highscore
    public int playerScore(){
        int higscore = 0;
        return higscore;
    }

    //Retunerar playername
    public String playerName(String playerName){
        
        return playerName ;
    }

    //Ökar poängen
    public void itScore() {
        score++;
    }

    //Retuerar score
    public int getScore() {
        return score;
    }

    //Sätter poäng
    public void setScore(int score) {
        this.score = score;
    }

    //retunerar choice
    public int getChoice() {
        return choice;
    }

    //Sätter choice
    public void setChoice(int choice) {
        this.choice = choice;
    }

    //Retunerar rand
    public Random getRand() {
        return rand;
    }

    //Sätter rand
    public void setRand(Random rand) {
        this.rand = rand;
    }

    //Retunerar res
    public int getRes() {
        return res;
    }

    //Sätter res
    public void setRes(int res) {
        this.res = res;
    }
    //Randomiserar vad datorn väljer för val
    public void newChoice() {
        choice = rand.nextInt(3);
    }
}
