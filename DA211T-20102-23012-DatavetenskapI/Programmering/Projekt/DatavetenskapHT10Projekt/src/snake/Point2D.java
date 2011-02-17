/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

/**
 *
 * @author wiik
 */
public class Point2D extends Object{
    /**
     * klassens instans variabler
     */
    private int x,y;
    /**
     * Point2D konstruktorn med två inparametrar
     *
     * @param x     int sätts till instansvariabeln x
     * @param y     int sätts till instansvariabeln y
     */
    public Point2D(int x, int y){
        setX(x);
        setY(y);
    }
    /**
     * Point2D konstruktorn med två inparametrar
     *
     * @param x     int sätts till instansvariabeln x
     * @param y     int sätts till instansvariabeln y
     */
    public Point2D(float x, float y){
        setX(x);
        setY(y);
    }
    /**
     * Point2D konstruktorn med två inparametrar
     *
     * @param x     int sätts till instansvariabeln x
     * @param y     int sätts till instansvariabeln y
     */
    public Point2D(double x, double y){
        setX(x);
        setY(y);
    }
    /**
     * metoder för att hantera typkonvertering innan värdenna sätts till
     * x variabeln
     * @return
     */
    int X(){return x;}
    public void setX(double x){ this.x = (int) x;}
    public void setX(float x){ this.x = (int) x;}
    public void setX(int x){ this.x =  x;}
    /**
     * metoder för att hantera typkonvertering innan värdenna sätts till
     * y variabeln
     * @return
     */
    int Y(){return y;}
    public void setY(double y){ this.y = (int) y;}
    public void setY(float y){ this.y = (int) y;}
    public void setY(int y){ this.y = y;}

}
