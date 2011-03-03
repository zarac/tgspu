/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tre_pa_rad;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author wiik
 */
public class Controller {
    /**
     * list inehåller alla lediga positioner på spelplanen.
     * viewer är JFramen.
     * Pl1 är den mänskliga spelaren.
     * Pl2 är CPUn.
     * winner beskriver om någon av spelarna har vunnit.
     * headl skickas till viewern när spelet är slut.
     */
    private List <Integer> list;
    private Viewer viewer;
    private Player pl1;
    private PlayerCPU pl2;
    private boolean winner = false;
    private String headl = "";
    /**
     * Controller konstruktorn initerar pl1 och pl2 instansvariablerna.
     */
    public Controller(Player pl1, PlayerCPU pl2){
            this.pl1 = pl1;
            this.pl2 = pl2;
            initList();
    }
    /**
     * initList metoden initerar list med 9 positioner, samma antal som
     * positioner på brädet.
     */
    public void initList(){
        list = new LinkedList();
        for(int i = 1; i <= 9; i++){
            list.add(i);
        }
    }
    /**
     * sätter ett viewer objekt till instansvariablen viewer.
     *
     * @param viewer    instans av viewer objekt.
     */
    public void setViewer(Viewer viewer){
        this.viewer = viewer;
    }
    /**
     * humanPlayer kallas från viewer objektet när den mänskliga spelaren
     * gör ett drag.
     *
     * @param i     positionen användaren valde.
     */
    public void humanPlayer(int i){
        if(!winner){
            playerMove(i, "X", pl1);
            if(!winner && list.size() > 0){
                playerMove(pl2.getNextMove(pl1.getArr(), pl2.getArr(), list), "O", pl2);
            }
            System.out.println("winner: "+winner);
            if(winner){
                viewer.setEnabling(false);
                viewer.setText(headl, true);
            }
        }
    }
    /**
     * playerMove tar bort den valda positionen från listan med de lediga positionerna.
     * Skickar den valda positionen till listan i player objektet.
     * Sätter positionen i viewern som tagen.
     * Kontrollerar om draget var ett vinnande drag.
     * Om det var ett vinnande drag sätts vinnarens namn till viewern.
     *
     * @param i     positionen som spelaren valde.
     * @param s     texten som ska skickas till viewern och sättas i knappen.
     * @param p     vilken spelare som gör draget.
     */
    public void playerMove(int i, String s, Player p){
        
        for(int j = 0; j < list.size(); j++){
            if(list.get(j)==i){
                list.remove(j);
            }
        }
        p.addNext(i);
        viewer.setTextInButton(i, s);
        winner = isWinner(p.getArr());
        if(winner&&p.equals(pl1)){ headl="You win!"; pl1.addToWins(); }
        else if(winner && p.equals(pl2)){ headl="CPU win!"; pl2.addToWins();  }
    }
    /**
     * isWinner kontrollerar om listan som skickas in innehåller
     * en sekvens som vinner spelet.
     *
     * @param l     listan med spelarens positioner.
     * @return      sant eller falskt
     */
    public boolean isWinner(List l){
        if(containsSek(1, 2, 3, l)){
            return true; }
        else if(containsSek(4, 5, 6, l)) {
            return true; }
        else if(containsSek(7, 8, 9, l)) {
            return true; }
        else if(containsSek(1, 4, 7, l)) {
            return true; }
        else if(containsSek(2, 5, 8, l)) {
            return true; }
        else if(containsSek(3, 6, 9, l)) {
            return true; }
        else if(containsSek(1, 5, 9, l)) {
            return true; }
        else if(containsSek(7, 5, 3, l)) {
            return true; }
        else{ 
            return false;}
    }
    /**
     * containSek kontrollerar om tre tal finns med i en lista.
     * Sedan returneras true eller falskt beroende på om alla
     * talen finns med.
     *
     * @param on    tal 1
     * @param tw    tal 2
     * @param th    tal 3
     * @param l     listan som talen kontrolleras mot.
     * @return      sant eller falskt
     */
    public boolean containsSek(int on, int tw, int th, List l){
        boolean one = false;
        boolean two = false;
        boolean thr = false;
        for(int i = 0; i < l.size(); i++){
            if(Integer.parseInt(l.get(i).toString())==on){
                one = true;
            }
            if(Integer.parseInt(l.get(i).toString())==tw){
                two = true;
            }
            if(Integer.parseInt(l.get(i).toString())==th){
                thr = true;
            }
        }
        if(one&&two&&thr){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * reSet startar en ny runda av spelet.
     */
    public void reSet(){
        pl1 = new Player();
        pl2 = new PlayerCPU();
        initList();
        headl="";
        viewer.setText(headl, true);
        winner = false;
    }

}
