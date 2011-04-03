
package rolfVrNinja;

public class Controller {

    private DuellViewer duellViewer;
    private GameBoard gameboard;
    private Player humPlayer;
    private Player cpuPlayer;

    /**
     *Konstruktor
     *
     */
    public Controller(DuellViewer duellViewer) {
        this.duellViewer = duellViewer;
//        this.gameboard = new GameBoard(this.duellViewer, this, new HighScore()); //gör så att controllern kan kalla på klassen viewern
        this.humPlayer = new Player(); // gör så att controllern kan kalla på klassen player
        this.cpuPlayer = new Player();
    }

    public void setGameBoard(GameBoard gameboard){
        this.gameboard = gameboard;
    }
    /**
     *Metod som avgör vem som får poäng när och skickar desutom highscore vidare
     * till classen gameboard.
     *
     */
    public void nyttVal(int hChoice) {
        int score = 0;
        String res = "";
        humPlayer.setChoice(hChoice);
        cpuPlayer.newChoice();
        // kallar på metoden newChoice i player klassen

        if ((cpuPlayer.getChoice() <= 2) && (humPlayer.getChoice() <= 2)) {

            if ((humPlayer.getChoice() == 0) && (cpuPlayer.getChoice() == 1)) {
                //rolf får ett poäng vid kickspark
                duellViewer.humanNoll();
                humPlayer.itScore();

            } else if ((humPlayer.getChoice() == 0)
                    && (cpuPlayer.getChoice() == 2)) {
                //ninja får poäng vid duck
                duellViewer.nollvsTvå();
                cpuPlayer.itScore();

            } else if ((humPlayer.getChoice() == 1)
                    && (cpuPlayer.getChoice() == 0)) {
                //ninja får poäng vid hammerpunch
                duellViewer.ninjaEtt();
                cpuPlayer.itScore();

            } else if ((humPlayer.getChoice() == 1)
                    && (cpuPlayer.getChoice() == 1)) {
                duellViewer.likaEtt();

            } else if ((humPlayer.getChoice() == 1)
                    && (cpuPlayer.getChoice() == 2)) {
                // rolf får ett poäng vid hammerpunch
                duellViewer.humanEtt();
                humPlayer.itScore();

            } else if ((humPlayer.getChoice() == 2)
                    && (cpuPlayer.getChoice() == 0)) {
                //rolf duckar mot en highkick och får poäng
                duellViewer.humanTvå();
                humPlayer.itScore();

            } else if ((humPlayer.getChoice() == 2)
                    && (cpuPlayer.getChoice() == 1)) {
                //ninja hammerpunchar mot en duckning och får poäng
                duellViewer.ninjaTvå();
                cpuPlayer.itScore();

            } else if ((humPlayer.getChoice() == 2)
                    && (cpuPlayer.getChoice() == 2)) {
                duellViewer.likaTvå();
            }
            if ((humPlayer.getScore() == 3) || (cpuPlayer.getScore() == 3)) {
                //om någon får värdet 3 så anropas disableButtons i userInput klassen och dimmar knapparna
                gameboard.disableButtons();
//                duellViewer.slut();
            }
            if (humPlayer.getScore() == 3) {

                score++; //Skickar higscore till
                gameboard.sendScore(score);
            }

            gameboard.human(humPlayer.getChoice());
            gameboard.computer(cpuPlayer.getChoice());
            gameboard.sethScore(humPlayer.getScore());
            gameboard.setcScore(cpuPlayer.getScore());
        }
    }
    /**
     * Metod som aktiveras när man trycker på nyttspel knappen.
     */
    public void nyttSpel() {
        //om man trycker på nytt spel så resettas poängen och metoden newGame anropas i klassen viewer
        //och då resettas värdena i viewern. metoden enableButtons i userInput klassen anropas och knapparna av dimmas.
        humPlayer.setScore(0);
        cpuPlayer.setScore(0);
        gameboard.newGame();
        gameboard.enableButtons();
    }
    /**
     * Metod som aktiveras när man trycker på avsluta knappen.
     */
    public void avsluta() {
        System.exit(0);
    }
}
