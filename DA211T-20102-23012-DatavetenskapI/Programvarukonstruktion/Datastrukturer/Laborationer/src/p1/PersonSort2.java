package p1;

import java.util.*;

public class PersonSort2 {

    public static void main(String[] args) {
        Person[] personer = { new Person( "Bo", "Al", 52 ), new Person( "Anna", "Bos", 17 ),
                              new Person( "Anders", "Al", 44 ), new Person( "Eva", "Bok", 33 ) };
        Arrays.sort( personer, new Youngest() );
        System.out.println( "EFTER SORTERING" );
        for( int i = 0; i < personer.length; i++ )
            System.out.println( personer[ i ] );
//        Comparator[] compArray = {new LastName(), new FirstName(), new Youngest()};
//        Person[] personer2 = {new Person("Eva", "Asp", 29),
//            new Person("Bo", "Asp", 52),
//            new Person("Eva", "Asp", 23),
//            new Person("Eva", "Ask", 29)};
//        Arrays.sort(personer2, new ComparatorN(compArray));
//        for (int i = 0; i < personer2.length; i++) {
//            System.out.println(personer2[i]);
//        }
    }
}

