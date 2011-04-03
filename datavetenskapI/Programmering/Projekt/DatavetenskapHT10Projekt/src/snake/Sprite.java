/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author wiik
 */
public class Sprite {
    /**
     * instans varaibler i klassen
     */
    private boolean inGame;
    private int dots;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    /**
     * Sprite konstruktorn initierar alla variabler till dess första värde.
     */
    public Sprite(){
        inGame = true;
        dots = 3;
        left = false;
        right = true;
        up = false;
        down = false;
    }
    /**
     * getter och setter metoder till alla instans variabler
     */
    public void setDots(int dots ){ this.dots = dots; }

    public void itDots(){ this.dots++; }

    public int getDots(){ return dots; }

    public void setInGame(boolean inGame) { this.inGame = inGame; }

    public void setLeft(){ left = true; }

    public void setRight(){ right = true; }

    public void setUp(){ up = true; }

    public void setDown(){ down = true; }

    public boolean isInGame() { return inGame; }

    public boolean isLeft() { return left; }

    public boolean isRight() { return right; }

    public boolean isUp() { return up; }

    public boolean isDown() { return down; }
    /**
     * setToFalse metoden sätter variablerna down, up, right och left till false.
     */
    public void setToFalse(){
        down = false;
        up = false;
        right = false;
        left = false;
    }

}
