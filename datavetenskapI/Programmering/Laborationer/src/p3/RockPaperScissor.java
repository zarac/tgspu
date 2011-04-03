package p3;

public class RockPaperScissor
{
    public static void main(String[] args)
    { 
        RPSViewer viewer = new RPSViewer(); 
        RPSPlayer player = new RPSPlayer(); 
        RPSPlayerAI computer = new RPSPlayerAI(); 
        RPSController controller = new RPSController(player, computer, viewer); 
        RPSUserInput userInput = new RPSUserInput(controller); 
    } 
}
