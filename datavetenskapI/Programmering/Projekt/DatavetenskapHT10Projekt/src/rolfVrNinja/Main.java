/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rolfVrNinja;

/**
 *
 * @author wiik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PaintWindow window = new PaintWindow();
        DuellViewer race = new DuellViewer(window);

        Controller  controller = new Controller(race);
        GameBoard gameboard = new GameBoard(race, controller, new HighScore());
        controller.setGameBoard(gameboard);
        race.start();
    }

}
