/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tre_pa_rad;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author wiik
 */
public class TrePaRad extends JPanel {
    private JPanel cards;
    private CardLayout cardlayout;
    private Player pl1;
    private PlayerCPU pl2;
    private Controller controller;
    private Viewer v;

    public TrePaRad(JPanel cards, CardLayout cardlayout){
        this.cards = cards;
        this.cardlayout = cardlayout;
        pl1 = new Player();
        pl2 = new PlayerCPU();
        controller = new Controller(pl1, pl2);
        v = new Viewer(controller, pl1, pl2);
        v.setCards(cards, cardlayout);
        controller.setViewer(v);
    }

    public JPanel getTrePaRad(){
        return v.getTrePaRad();
    }

}
