package Pong;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.util.*;

/**
 *
 * @author Rasmus
 * Created 9/27-2010
 *
 * Detta program skapar ett enkelt Pong spel.
 * Jag har försökt förklara bäst jag kan, men då jag inte är så van vid att använda rätt termer för allting och bara är en student på kursen så
 * vill jag poängtera att jag kan ha förklarat felaktigt vid vissa ställen. Om så är fallet så uppskattar jag ifall någon med mer erfarenhet av
 * Java kunnat rätta till mig :)
 *
 */
public class Pong implements ActionListener, MouseMotionListener {
    // Skapar fönstret
    JFrame frame = new JFrame("Pong, av Rasmus Ljunggren");
    // Deklarerar objektet drawScene som används för att rita i fönstret
    private DrawingComponent drawScene = new DrawingComponent();
    // Deklarerar och initierar ett tid objekt och sätter den på 1000 / 60 ms. dvs. 60 ggr per sekund
    Timer timer = new Timer(1000 / 60, this);
    // Deklarerar ett random objekt för att kunna skapa slumpnummer för bollens y-hastighet och vem som startar med bollen
    Random random = new Random();
    // Deklarerar varibler..
    // ballX och ballY är bollens kordinater
    double ballX, ballY;
    // brickY är ges 2 stycken int värde dvs. brickY[0] för spelarens brickas Y-värde och brickY[1] för datorns brickas Y-värde
    int[] brickY = new int[2];
    // score ges 2 stycken int värde dvs. score[0] för spelarens poäng och score[1] för datorns poäng
    int[] score = new int[2];
    // bollens X och Y hastighet
    double ballVelX, ballVelY;
    // Datorns brickas hastighet, reglerar även svårighetsgraden i spelet
    double compAIBrickSpeed = 4.0;

    public Pong() {
        // Sätter fönsterstorleken
        frame.setSize(415, 230);
        // Sätter fönster synligt
        frame.setVisible(true);
        // Visar programet att det ska avslutas om fönstret stängs
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Deklarerar objektet toolkit för att ta reda på skärmens upplösning
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Använder sig av toolkit för att kunna sätta fönstret i mitten av skärmen
        frame.setLocation((toolkit.getScreenSize().width / 2) - (frame.getWidth() / 2), (toolkit.getScreenSize().height / 2) - (frame.getHeight() / 2));
        // Fönstret skall inte kunna förstoras eller förminskas
        frame.setResizable(false);
        // Går till metoden startBall för att ge bollen alla startvärden den behöver
        startBall(random.nextInt(2));
        // Ger fönstret en lyssnare som den kommer att gå till så fort musen rör sig inom fönstret
        frame.getContentPane().addMouseMotionListener(this);
        // drawScene läggs till fönstret (Då drawScene är ett objekt av klassen DrawingComponent som har (extends JPanel) i sig, så är detta ungefär samma som att ett JFrame objekt addar ett JPanel objekt)
        frame.getContentPane().add(drawScene);
        // Visar ett fönster med texten starta pong
        JOptionPane.showMessageDialog(frame, "Starta pong");
        // Startar timer objektet så att 'actionPerformed' metoden kommer att kallas på 60 ggr i sekunden.
        timer.start();
    }

    // Hit går den varje gång bollen når en av utkanterna på vänster eller höger sida. Därmed får antingen spelaren poäng (poangTo == 0) eller datorn poäng (poangTo == 1)
    public void poang(int poangTo) {
        // Om poangTo är lika med 0. Då utförs instruktionerna inom klamrarna.
        if (poangTo == 0) {
            // Spelaren får +1 poäng
            score[0]++;
            // Visar att spelaren får poäng genom ett fönster
            JOptionPane.showMessageDialog(frame, "Poäng till vänster spelare!");
        }
        // Om poangTo är lika med 1. Då utförs instruktionerna inom klamrarna.
        if (poangTo == 1) {
            // Datorn får +1 poäng
            score[1]++;
            // Visar att datorn får poäng genom ett fönster
            JOptionPane.showMessageDialog(frame, "Poäng till höger spelare!");
        }
        // Går till metoden startBall för att ge bollen alla startvärden den behöver
        startBall(poangTo);
    }

    // I denna metoden får bollen ett startvärde, vilken sida bollen ska börja på beror på vad startside har för värde (0 eller 1)
    public void startBall(int startside) {
        // Ger bollen ett Y-värde som får den att börja i mitten på y linjen
        ballY = 100;
        // Om startside är lika med 0. Då utförs instruktionerna inom klamrarna.
        if (startside == 0) {
            // Bollen får ett X-värde som sätter den långt ut till vänster
            ballX = 50;
            // Bollens hastighet får ett X-värde som rör den åt höger. Hastigheten den startar med beror också på hur många poäng datorn och spelaren har tillsammans
            ballVelX = 2 + (score[0] + score[1]) / 6;
        }
        // Om startside är lika med 1. Då utförs instruktionerna inom klamrarna.
        if (startside == 1) {
            // Bollen får ett X-värde som sätter den långt ut till höger
            ballX = 350;
            // Bollens hastighet får ett X-värde som rör den åt vänster. Hastigheten den startar med beror också på hur många poäng datorn och spelaren har tillsammans
            ballVelX = -2 - (score[0] + score[1]) / 6;
        }
        // randsize får samma värde som bollens x-hastighet
        int randsize = (int)ballVelX;
        // här kontrolleras och rättas randsize och görs positivt ifall det är så att dess värde är negativt
        if (randsize < 0) randsize = -randsize;
        // Ger bollens Y-hastighet ett slumpartat värde med max av vad bollens X-hastighet är
        ballVelY = random.nextInt((randsize * 2) + 1) - randsize;
    }

    // I denna metoden updateras bollens rörelser, samt att det kontrolleras så att bollen inte hamnar utanför fönstret (denna metod kallas 60 ggr per sekund)
    public void ballUpdate() {
        // Ökar på bollens hastighet lite varje gång dessa instruktioner genomförs.
        if (ballVelX > 0) ballVelX+=0.001;
        if (ballVelX < 0) ballVelX-=0.001;
        // Bollens X och Y kordinater förflyttar sig med bollens X och Y hastigheter.
        ballX += ballVelX;
        ballY += ballVelY;
        // Om bollens X kordinat är mindre än 0, så ge datorn poäng
        if (ballX < 0) {
            // Datorn ges poäng +1 när denna metod kallas med värdet (1)
            poang(1);
            // Lämnar denna metod då bollen inte skall updateras mer denna gång
            return;
        }
        // Om bollens X kordinat är högre än 399, så ge spelaren poäng
        if (ballX > 399) {
            // Spelaren ges poäng +1 när denna metod kallas med värdet (0)
            poang(0);
            // Lämnar denna metod då bollen inte skall updateras mer denna gång
            return;
        }
        // Om bollens Y kordinat är mindre än 0, så vänd på dess Y-hastighet
        if (ballY < 0) {
            // Bollens negativa värde blir positivt så att den kommer ut i fönstret igen. (Detta görs för att skapa mer "flyt" i studsen)
            ballY = -ballY;
            // Y-hastighet vänds på
            ballVelY = -ballVelY;
        }
        // Om bollens Y kordinat är högre än 199, så vänd på dess Y-hastighet
        if (ballY > 199) {
            // Bollens värde ändras så att det värdet över 199 dras istället av från 199 så att den kommer ut i fönstret igen. (Detta görs för att skapa mer "flyt" i studsen)
            ballY -= ballY - 199;
            // Y-hastighet vänds på
            ballVelY = -ballVelY;
        }
        // Varibeln hitY deklareras med värdet 0.0
        double hitY = 0.0;
        // Om bollen är innanför spelarens brickas område, så skall den studsa i datorns riktning. (Brickan har storleken X = 5 och Y = 30, bollen har en radie på 5, därför skall bollens centrum ligga X < 10 och Y < 20 från brickans centrum)
        if ((ballX >= 0) && (ballX < 15) && (ballY >= brickY[0] - 20) && (ballY <= brickY[0] + 20)) {
            // Om bollens X-hastighet är negativ så skall den inventeras
            if (ballVelX < 0) {
                // Bollens X-hastighet inventeras
                ballVelX = -ballVelX;
                // hitY får ett värde beroende på hur långt ifrån brickans Y centrum den träffar
                hitY = brickY[0] - ballY;
            }
        }
        // Om bollen är innanför datorns brickas område, så skall den studsa i spelarens riktning. (Brickan har storleken X = 5 och Y = 30, bollen har en radie på 5, därför skall bollens centrum ligga X < 10 och Y < 20 från brickans centrum)
        if ((ballX >= 385) && (ballX < 400) && (ballY >= brickY[1] - 20) && (ballY <= brickY[1] + 20)) {
            // Om bollens X-hastighet är positiv så skall den inventeras
            if (ballVelX > 0) {
                // Bollens X-hastighet inventeras
                ballVelX = -ballVelX;
                // hitY får ett värde beroende på hur långt ifrån brickans Y centrum den träffar
                hitY = brickY[1] - ballY;
            }
        }
        // Om hitY INTE är lika med 0.0 så genomförs instruktionerna innanför klamrarna
        if (hitY != 0.0) {
            // Bollens Y hastighet förändras beroende på vad hitY har fått för värde efter att ha träffat en av brickorna
            ballVelY = -hitY / 3.0;
        }
    }

    // I denna metod updateras spelarens bricka och får en Y-position som är lika med input värdet yPos
    public void brickPlayerUpdate(int yPos) {
        // Spelarens bricka får samma värde som input värdet yPos
        brickY[0] = yPos;
        // Om brickans Y värde är mindre än 0 så sätt dess värde till 0
        if (brickY[0] < 0) {
            brickY[0] = 0;
        }
        // Om brickans Y värde är högre än 199 så sätt dess värde till 199
        if (brickY[0] > 199) {
            brickY[0] = 199;
        }
    }

    // I denna metod hanteras datorns brickas AI
    public void brickComputerUpdate() {
        // Om brickans Y-värde är mindre än bollens Y-värde, så adderas brickans Y-värde med compAIBrickSpeed
        if (ballY > brickY[1]) {
            // Brickans Y värde adderas med compAIBrickSpeed
            brickY[1] += compAIBrickSpeed;
            // Om brickans Y-värde nu är större än bollens Y-värde så skall den istället bli lika med bollens Y-värde
            if (ballY < brickY[1]) brickY[1] = (int)ballY;
        }
        // Om brickans Y-värde är större än bollens Y-värde, så subtraheras brickans Y-värde med compAIBrickSpeed
        if (ballY < brickY[1]) {
            // Brickans Y värde subtraheras med compAIBrickSpeed
            brickY[1] -= compAIBrickSpeed;
            // Om brickans Y-värde nu är mindre än bollens Y-värde så skall den istället bli lika med bollens Y-värde
            if (ballY > brickY[1]) brickY[1] = (int)ballY;
        }
        // Om brickans Y värde är mindre än 0 så sätt dess värde till 0
        if (brickY[1] < 0) {
            brickY[1] = 0;
        }
        // Om brickans Y värde är högre än 199 så sätt dess värde till 199
        if (brickY[1] > 199) {
            brickY[1] = 199;
        }
    }

    // Denna metod kallas så fort musen rör sig i fönstret
    public void mouseMoved(MouseEvent e) {
        // metoden 'brickPlayerUpdate' kallas med värdet 'e.getY()' som är musens Y-kordinat inne i fönstret
        brickPlayerUpdate(e.getY());
    }

    // Denna metod används inte i detta program, men kallas ändå så fort musens knapp är nedtryckt och rör sig inom fönstret
    public void mouseDragged(MouseEvent e) {
    }

    // Som jag förstår följande klass (är lite osäker på detta men ska försöka förklara bäst jag kan.)
    // 'extends JPanel' gör så att klassen själv är av objektet JPanel. Dvs. att när man skriver i klassen behöver man inget JPanel objekt för att komma åt JPanel:s metoder.
    // Det är som när vi skriver egna klasser och vill komma åt någonting i klassen från en annan klass. Vi måste skapa ett objekt då, och sedan kalla på metoden eller variblerna vi vill komma åt.
    // Men i detta fallet när vi gör en klass som har 'extends JPanel (eller någon annan klass)' så smälter dessa klasser liksom samman, och man kan använda motoderna i JPanel som om de skulle vara i den nya klassen vi skapat.
    //
    // Detta kanske låter lite flummigt. Men då 'paintComponent' redan är en metod som finns i JPanel. Och en ny görs i klassen DrawingComponent undertill, så skrivs JPanel:s vanliga klass över med den nya som jag skapat undertill.
    // Därför anropas paintComponent metoden så fort vi använder oss av metoden 'repaint()' som också är en metod som finns i JPanel.

    // Denna klass används för att rita upp bakgrunden, brickorna, bollen och resultaten i fönstret
    class DrawingComponent extends JPanel {
        // Metoden kallas varje gång JPanel ombeds ritas om. (drawScene.repaint())
        protected void paintComponent(Graphics g) {
            // Bollens radie sätts till 5

            int ballR = 5;
            // Vet inte riktigt vad 'super' kommandot står för eller vad det gör än :S
            super.paintComponent(g);
            // Kallar på metoden setColor med värdet 'Color.black' som kommer att ge allt ritande frammåt svart färg
            g.setColor(Color.black);
            // Ritar en svart rektangel som fyller hela bakgrunden i fönstret
            g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
            // Kallar på metoden setColor med värdet 'Color.white' som kommer att ge allt ritande frammåt vit färg
            g.setColor(Color.white);
            // Ritar bollen med radien ballR (5). Det är viktigt att tänka på här att de första två input värderna inte ger bollens centrum kordinater utan istället upp vänster kordinater av cirkeln. De två sista ger cirkelns bredd och höjd.
            g.fillOval((int) ballX - ballR, (int) ballY - ballR, ballR * 2, ballR * 2);
            // Ritar spelarens bricka med bredden 5 och höjden 30. Detta från X-värdet 5 och ett Y värde som är lika med 'brickY[0]'
            g.fillRect(5, brickY[0] - 15, 5, 30);
            // Ritar datorns bricka med bredden 5 och höjden 30. Detta från X-värdet 390 och ett Y värde som är lika med 'brickY[1]'
            g.fillRect(390, brickY[1] - 15, 5, 30);
            // Ritar upp centrum strecken som avskärmar skärmens bredd på mitten.
            // 'for' kommandot gör i detta fall så att variblen 'i' deklareras med värdet '0', och allting inom klamrarna kommer att repeteras så länge 'i' är mindre eller lika med '180'. För varje loop så adderas 'i' med 20.
            for (int i = 0; i <= 180; i += 20) {
                // Ritar upp en linje med x-kordinaterna 199 och med y kordinater som går ifrån 'i-värdet' till 10 + 'i-värdet'
                g.drawLine(199, i, 199, i + 10);
            }
            // Kallar på metoden setFont med typsnittet Serif och storleken 26. Detta kommer att sparas i objektet 'g' och användas när text skall skrivas ut i fönstret
            g.setFont(new Font("Serif", Font.PLAIN, 26));
            // Ritar upp spelarens poäng (score[0]) med kordinaterna X=90 och Y=30
            g.drawString("" + score[0], 90, 30);
            // Ritar upp datorns poäng (score[1]) med kordinaterna X=300 och Y=30
            g.drawString("" + score[1], 300, 30);
        }
    }

    // Denna metod anropas när någonting händer i programmet som programeraren har bett programmet "lyssna på". I detta fallet så har jag bett den lyssna på tiden, dvs att metoden skall kallas på 60 ggr per sekund.
    public void actionPerformed(ActionEvent e) {
        // Ifall den metoden kallades på av objektet 'timer' så genomförs instruktionerna innanför klamrarna.
        if (e.getSource() == timer) {
            // brickComputerUpdate metoden kallas som används för datorns AI (dvs att styra brickan)
            brickComputerUpdate();
            // ballUpdate metoden kallas som används för att förflytta bollen och att kontrollera ifall någon gjort poäng
            ballUpdate();
            // drawScene objektet som är av typen 'DrawingComponent' eller 'JPanel' med en klass som fortföljs kallas på. Precis som att JPanel objekt kan bes ritas om, kan detta objekt också göra det.
            drawScene.repaint();
        }
    }

    public static void main(String[] arg) {
        // Detta kan vi ;)
        Pong progPong = new Pong();
    }
}