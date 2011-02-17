/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tre_pa_rad;

import java.util.List;
import java.util.Random;

/**
 *
 * @author wiik
 */
public class PlayerCPU extends Player {
    /**
     * Private instansvariabel av typen Random.
     */
    private Random r;
    /**
     * Konstrukor som sätter ny instans av random.
     */
    public PlayerCPU(){
        r = new Random();
    }
    /**
     * Kontrollera om CPUn kan göra ett drag för att vinna.
     * Kontrollera om den mänskliga spelaren håller på att
     * vinna och om draget kan blokeras.
     * Om inget av kraven uppfylls returneras ett random
     * värde av de möjliga dragen.
     *
     * @param hum   drag av den mänskliga spelaren.
     * @param cpu   drag av CPUn.
     * @param l     lista av drag som är möjliga.
     * @return      nästa drag.
     */
    public int getNextMove(List hum, List cpu, List l){

        int i = controllPos(cpu, l);
        if(i != -1){
            System.out.println("Found CPU"+i);
            return i;
        }
            i = controllPos(hum, l);
        if(i != -1){
            System.out.println("Found Human"+i);
            return i;
        }
            i = Integer.parseInt(l.get(r.nextInt(l.size())).toString());
            System.out.println("No match found"+i);
            return i;
    }
    /**
     * controllPos tar emot två listor. Den första listan innehåller
     * positionerna en spelare har valt och en lista över lediga positioner.
     * Metoden kontrollerar om spelaren har två positionern tagna och om ett
     * vinnande drag är möjligt.
     *
     * @param l1    Spelarens gjorda drag
     * @param l2    De möjliga dragen.
     * @return      Ett möjligt drag.
     */
    public int controllPos(List l1, List l2){
        if(containsSek(1, 2, l1)){ if(isPosFree(3, l2)){ return 3; }}
        if(containsSek(4, 5, l1)){ if(isPosFree(6, l2)){ return 6; }}
        if(containsSek(7, 8, l1)){ if(isPosFree(9, l2)){ return 9; }}

        if(containsSek(2, 3, l1)){ if(isPosFree(1, l2)){ return 1; }}
        if(containsSek(5, 6, l1)){ if(isPosFree(4, l2)){ return 4; }}
        if(containsSek(8, 9, l1)){ if(isPosFree(7, l2)){ return 7; }}

        if(containsSek(1, 4, l1)){ if(isPosFree(7, l2)){ return 7; }}
        if(containsSek(2, 5, l1)){ if(isPosFree(8, l2)){ return 8; }}
        if(containsSek(3, 6, l1)){ if(isPosFree(9, l2)){ return 9; }}

        if(containsSek(4, 7, l1)){ if(isPosFree(1, l2)){ return 1; }}
        if(containsSek(5, 8, l1)){ if(isPosFree(2, l2)){ return 2; }}
        if(containsSek(6, 9, l1)){ if(isPosFree(3, l2)){ return 3; }}

        if(containsSek(1, 7, l1)){ if(isPosFree(4, l2)){ return 4; }}
        if(containsSek(2, 8, l1)){ if(isPosFree(5, l2)){ return 5; }}
        if(containsSek(3, 9, l1)){ if(isPosFree(6, l2)){ return 6; }}

        if(containsSek(1, 3, l1)){ if(isPosFree(2, l2)){ return 2; }}
        if(containsSek(4, 6, l1)){ if(isPosFree(5, l2)){ return 5; }}
        if(containsSek(7, 9, l1)){ if(isPosFree(8, l2)){ return 8; }}

        if(containsSek(1, 9, l1)){ if(isPosFree(2, l2)){ return 2; }}
        if(containsSek(7, 3, l1)){ if(isPosFree(5, l2)){ return 5; }}

        if(containsSek(7, 5, l1)){ if(isPosFree(3, l2)){ return 3; }}
        if(containsSek(5, 3, l1)){ if(isPosFree(7, l2)){ return 7; }}
        if(containsSek(1, 5, l1)){ if(isPosFree(9, l2)){ return 9; }}
        if(containsSek(5, 9, l1)){ if(isPosFree(1, l2)){ return 1; }}

        return -1;
    }
    /**
     * Kontrollerar om tal 1 och två finns med i listan.
     *
     * @param o     position 1
     * @param t     position 2
     * @param l     lista med spelarens tagna positioner
     * @return      sant eller falskt
     */
    public boolean containsSek(int o, int t, List l){
        boolean on = false;
        boolean tw = false;
        for(int i = 0; i < l.size(); i++){
            if(Integer.parseInt(l.get(i).toString())==o){
                on = true;
            }
            if(Integer.parseInt(l.get(i).toString())==t){
                tw = true;
            }
        }
        if(on&&tw){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * isPosFree kontrollerar om ett tal finns med i listan.
     *
     * @param o     önskad position
     * @param l     lista med lediga positioner
     * @return      sant eller falskt
     */
    public boolean isPosFree(int o, List l){
        boolean on = false;
        for(int i = 0; i < l.size(); i++){
            if(Integer.parseInt(l.get(i).toString())==o){
                on = true;
            }
        }
        return on;
    }
}
