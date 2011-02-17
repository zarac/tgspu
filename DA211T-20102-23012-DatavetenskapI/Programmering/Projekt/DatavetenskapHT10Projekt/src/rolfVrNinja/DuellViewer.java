/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rolfVrNinja;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
/**
 *
 * @author ASUS
 */
public class DuellViewer extends JPanel {

    private PaintWindow window;
    private Projektil humanNoll;
    private Projektil humanEtt;
    private Projektil humanTvå;
    private Projektil ninjaNoll;
    private Projektil ninjaEtt;
    private Projektil ninjaTvå;
    private Projektil lika;
    private Projektil likaEtt;
    private Projektil likaTvå;
    private Projektil start;

    /**
     *Konstruktor
     */
    public DuellViewer(PaintWindow window) {
        this.window = window;
        initImg();
    }
    /*
     * Metoden inehåller bilderna till spelet.
     */
    public void initImg(){
//        String url = "D:/Laborationer/JavaApplication1/src/spel";
        // [HL-2011-01-17 04:15] Edited paths to be relative to class file.
        ninjaEtt = new Projektil("static/rolfVrNinja/ettVsnoll.png");
        humanEtt = new Projektil("static/rolfVrNinja/ettVstva.png");
        likaEtt = new Projektil("static/rolfVrNinja/likaEtt.png");
        likaTvå = new Projektil("static/rolfVrNinja/likaTva.png");
        humanNoll = new Projektil("static/rolfVrNinja/nollVsett.png");
        ninjaNoll = new Projektil("static/rolfVrNinja/nollVstva.png");
        ninjaTvå = new Projektil("static/rolfVrNinja/tvaVsett.png");
        humanTvå = new Projektil("static/rolfVrNinja/tvaVsnoll.png");
        lika = new Projektil("static/rolfVrNinja/likaNoll.png");
        start = new Projektil("static/rolfVrNinja/start.png");
        //ninjaEtt = new Projektil(getImage("ettVsnoll.png"));
        //humanEtt = new Projektil(getImage("ettVstva.png"));
        //likaEtt = new Projektil(getImage("likaEtt.png"));
        //likaTvå = new Projektil(getImage("likaTva.png"));
        //humanNoll = new Projektil(getImage("nollVsett.png"));
        //ninjaNoll = new Projektil(getImage("nollVstva.png"));
        //ninjaTvå = new Projektil(getImage("tvaVsett.png"));
        //humanTvå = new Projektil(getImage("tvaVsnoll.png"));
        //lika = new Projektil(getImage("likaNoll.png"));
        //start = new Projektil(getImage("start.png"));
        //String url = "C:/BilderGrupp1/";
        //ninjaEtt = new Projektil(url+"/ettVsnoll.png");
        //humanEtt = new Projektil(url+"/ettVstva.png");
        //likaEtt = new Projektil(url+"/likaEtt.png");
        //likaTvå = new Projektil(url+"/likaTva.png");
        //humanNoll = new Projektil(url+"/nollVsett.png");
        //ninjaNoll = new Projektil(url+"/nollVstva.png");
        //ninjaTvå = new Projektil(url+"/tvaVsett.png");
        //humanTvå = new Projektil(url+"/tvaVsnoll.png");
        //lika = new Projektil(url+"/likaNoll.png");
        //start = new Projektil(url+"/start.png");

//        ninjaEtt = new Projektil(getImage("/ettVsnoll.png"));
//        humanEtt = new Projektil(getImage("/ettVstva.png"));
//        likaEtt = new Projektil(getImage("/likaEtt.png"));
//        likaTvå = new Projektil(getImage("/likaTvå.png"));
//        humanNoll = new Projektil(getImage("/nollVsett.png"));
//        ninjaNoll = new Projektil(getImage("/nollVstva.png"));
//        ninjaTvå = new Projektil(getImage("/tvaVsett.png"));
//        humanTvå = new Projektil(getImage("/tvaVsnoll.png"));
//        lika = new Projektil(getImage("/likanoll.png"));
//        start = new Projektil(getImage("/start.png"));
    }

    public Image getImage(String in){
        return new ImageIcon(this.getClass().getResource(in)).getImage();
    }

    public PaintWindow getWindow(){
        return window;
    }
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void humanNoll() {
    window.showImage(humanNoll.getImage(), humanNoll.getX(), humanNoll.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void humanEtt(){
    window.showImage(humanEtt.getImage(), humanEtt.getX(), humanEtt.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void humanTvå(){
    window.showImage(humanTvå.getImage(), humanTvå.getX(), humanTvå.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void nollvsTvå(){
    window.showImage(ninjaNoll.getImage(), ninjaNoll.getX(), ninjaNoll.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void ninjaEtt(){
    window.showImage(ninjaEtt.getImage(), ninjaEtt.getX(), ninjaEtt.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void ninjaTvå(){
    window.showImage(ninjaTvå.getImage(), ninjaTvå.getX(), ninjaTvå.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void likaNoll(){
    window.showImage(lika.getImage(), lika.getX(), lika.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void likaEtt(){
    window.showImage(likaEtt.getImage(), likaEtt.getX(), likaEtt.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void likaTvå(){
    window.showImage(likaTvå.getImage(), likaTvå.getX(), likaTvå.getY());
    initImg();
}
/**
 *Metoden bestämmer vilken bild som ska visas.
 */
public void start(){
    window.showImage(start.getImage(), start.getX(), start.getY());
    initImg();
}


public void run() {
     humanNoll();
    }

 
}
