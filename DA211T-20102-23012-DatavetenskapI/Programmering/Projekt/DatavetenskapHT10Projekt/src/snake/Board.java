/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author wiik
 */
public class Board extends JPanel implements Runnable, KeyListener {
    /**
     * Instans variabler som används i Board klassen
     */
    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    private ScoreBoard score;
    private Sprite sprite;
    private Point2D applePoint;
    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];

    private Thread thread;
    private Image ball;
    private Image apple;
    private Image head;
    private JPanel cards;
    /**
     * Board konstruktor med inparameter av typen ScoreBoard
     * @param score
     */
    public Board(ScoreBoard score, JPanel cards) {
        this.cards = cards;
        this.score = score;
        this.addKeyListener(this);
        setBackground(Color.black);

        ball = getImage("static/snake/dot.png");
        apple = getImage("static/snake/apple.png");
        head = getImage("static/snake/head.png");

        //ball = getImage("dot.png");
        //apple = getImage("apple.png");
        //head = getImage("head.png");

        setFocusable(true);
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)"Snake");
        initGame();
    }
    /**
     * Metod för att hämta bilderna som används i spelet.
     *
     * @param in    namnet på bilden
     * @return      Image objektet som ska sättas till instans variablen.
     */
    public Image getImage(String in){
        return new ImageIcon(in).getImage();
        //return new ImageIcon(this.getClass().getResource(in)).getImage();
    }
    /**
     * initGame initierar spelet.
     */
    public void initGame() {
        score.zeroApples();
        sprite = new Sprite();
        for (int z = 0; z < sprite.getDots(); z++) {
            x[z] = 50 - z*10;
            y[z] = 50;
        }
        score.subName(false);
        this.requestFocus();
        locateApple();
        start();
    }
    /**
     * start metoden sätter en ny instans till tråden.
     * Tråden startas sedan.
     */
    public void start(){
        if( thread == null ) {
            thread = new Thread( this );
            thread.start();
        }
    }
    /**
     * stop metoden avslutar tråden och sätter den till null.
     */
    public void stop(){
        if( thread != null ) {
            thread.interrupt();
            thread = null;
        }
    }
    /**
     * paint metoden används för att rita om fönstret varje iteration.
     * @param g     graphics objectet
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (sprite.isInGame()) {

            g.drawImage(apple, applePoint.X(), applePoint.Y(), this);

            for (int z = 0; z < sprite.getDots(); z++) {
                if (z == 0){
                    g.drawImage(head, x[z], y[z], this); }
                else{
                    g.drawImage(ball, x[z], y[z], this); }
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else { gameOver(g); }
    }
    /**
     * gameOver metoden kallas från paint metoden om spelet är slut.
     * Den skriver ut att spelet är över och möjliggör för användaren
     * att skriva sitt namn och skicka sin poäng till en databas.
     * @param g
     */
    public void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
        stop();
        score.subName(true);
    }
    /**
     * checkApple kontrollerar om äpplet är på samma position som
     * ormens huvud och om så sätts ett nytt äpple till skärmen
     * och plus ett sätts till poängen.
     */
    public void checkApple() {
        if ((x[0] == applePoint.X()) && (y[0] == applePoint.Y())) {
            sprite.itDots();
            locateApple();
            score.itScore();
        }
    }
    /**
     * move metoden flyttar alla positioner i ormen ett steg fram och kontrollerar
     * sedan åt vilken position ormen rör sig och flyttar ormens huvud åt
     * det hållet.
     */
    public void move() {

        for (int z = sprite.getDots(); z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (sprite.isLeft()) { x[0]-=DOT_SIZE; }

        if (sprite.isRight()) { x[0]+=DOT_SIZE; }

        if (sprite.isUp()) { y[0]-=DOT_SIZE; }

        if (sprite.isDown()) { y[0]+=DOT_SIZE; }
    }
    /**
     * checkCollision metoden kontrollerar om ormen har krockat med sig själv
     * eller om ormen rör sig utanför spelplanens gräns.
     */
    public void checkCollision() {

        for (int z = sprite.getDots(); z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) { sprite.setInGame(false); }
        }

        if (y[0] > HEIGHT) { sprite.setInGame(false); }

        if (y[0] < 0) { sprite.setInGame(false); }

        if (x[0] > WIDTH) { sprite.setInGame(false); }

        if (x[0] < 0) { sprite.setInGame(false); }
    }
    /**
     * locateApple metoden sätter ett nytt äpple på någonstans på spelplanen.
     */
    public void locateApple() {
        applePoint = new Point2D(((((int) (Math.random() * RAND_POS)) * DOT_SIZE)),
                                 ((((int) (Math.random() * RAND_POS)) * DOT_SIZE)));
    }
    /**
     * run metoden itererar spelet.
     */
    public void run() {
        while(thread!=null){
            try{
                thread.sleep(DELAY);
                if (sprite.isInGame()) {
                    checkApple();
                    checkCollision();
                    move();
                }
                repaint();
            }
            catch(InterruptedException e){ }
        }
    }

    public void keyTyped(KeyEvent ke) { }
    /**
     * keyPressed sätter åt vilket håll som ormen ska röra sig.
     * @param e     KeyEvent
     */
    public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!sprite.isRight())) {
                sprite.setToFalse(); sprite.setLeft();
            }

            if ((key == KeyEvent.VK_RIGHT) && (!sprite.isLeft())) {
                sprite.setToFalse(); sprite.setRight();
            }

            if ((key == KeyEvent.VK_UP) && (!sprite.isDown())) {
                sprite.setToFalse(); sprite.setUp();
            }

            if ((key == KeyEvent.VK_DOWN) && (!sprite.isUp())) {
                sprite.setToFalse(); sprite.setDown();
            }
            if ((key == KeyEvent.VK_ENTER) && sprite.isInGame() == false){
                initGame();
                sprite.setInGame(true);
                sprite.setToFalse(); sprite.setRight();
            }
    }

    public void keyReleased(KeyEvent ke) { }

}
