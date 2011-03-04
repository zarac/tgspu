package p5;

public class TestDice
{
    public static void test(Dice p_dice, int numThrows)
    {
        int[] results = new int[p_dice.getSides()];
        for (int i = 0; i < numThrows; i++)
        {
            results[p_dice.throwDice() - 1]++;
        }

        for (int i = 0; i < p_dice.getSides(); i++)
        {
            System.out.println(i + 1 + " - " + results[i]);
        }
    }

    public static void test(Player p_player, int numThrows)
    {
        int[] results = new int[p_player.getDice().getSides()];
        for (int i = 0; i < numThrows; i++)
        {
            results[p_player.throwDice() - 1]++;
        }

        for (int i = 0; i < p_player.getDice().getSides(); i++)
        {
            System.out.println(i + 1 + " - " + results[i]);
        }
    }

    public static void main(String[] argv)
    {
        // 5c
        //TestDice.test( new SimpleDice( 6 ), 1000000 ); 
        //System.out.println(); 
        //TestDice.test( new SimpleDice( 4 ), 1000000 ); 

        // 5e
        TestDice.test( new OrdinaryPlayer( "Rut", new SimpleDice( 6 ) ), 1000000 ); 
        System.out.println(); 
        TestDice.test( new Cheater( "Fuffe", new SimpleDice( 6 ) ), 1000000 ); 
    }
}
