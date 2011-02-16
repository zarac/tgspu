package f2;
import java.util.*;

public class Decrease implements Comparator<RealNbr> {
    public int compare( RealNbr nbr1, RealNbr nbr2 ) {
        if( nbr1.getValue() > nbr2.getValue() )     // return -nbr1.compareTo( nbr2 );
            return -1;
        else if( nbr1.getValue() == nbr2.getValue() )
            return 0;
        else
            return 1;
    }
}
