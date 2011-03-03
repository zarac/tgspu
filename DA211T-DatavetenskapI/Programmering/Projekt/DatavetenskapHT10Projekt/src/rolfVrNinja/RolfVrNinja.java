/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rolfVrNinja;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author wiik
 */
public class RolfVrNinja {
private GameBoard gameboard;

    public RolfVrNinja(){
        PaintWindow window = new PaintWindow();
        DuellViewer race = new DuellViewer(window);

        Controller  controller = new Controller(race);
        gameboard = new GameBoard(race, controller, new HighScore());
        controller.setGameBoard(gameboard);
        race.start();
    }

    public GameBoard getGameBoard(){
        return gameboard;
    }

    public void setGameBoardCards(JPanel cards, CardLayout cardlayout){
        gameboard.setCards(cards, cardlayout);
    }
}
