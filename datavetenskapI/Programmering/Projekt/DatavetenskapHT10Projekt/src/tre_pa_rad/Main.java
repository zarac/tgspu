/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tre_pa_rad;

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
        Player pl1 = new Player();
        PlayerCPU pl2 = new PlayerCPU();
        Controller controller = new Controller(pl1, pl2);
        Viewer v = new Viewer(controller, pl1, pl2);
        controller.setViewer(v);
    }

}
