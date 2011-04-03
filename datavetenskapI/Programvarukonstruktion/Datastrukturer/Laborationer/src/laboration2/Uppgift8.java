package laboration2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Uppgift8
{
    public static void main (String [] args)
    {
        Laboration2 lab2 = new Laboration2();
        ArrayList<Integer> intArrayList = lab2.fillInteger(100000, 10000, 50000);

        ArrayList<RealNbr> realNbrArrayList = new ArrayList<RealNbr>();

        for (int integer : intArrayList) 
            realNbrArrayList.add(new RealNbr(integer));

        Collections.sort(realNbrArrayList);

        System.out.println(Collections.binarySearch(realNbrArrayList, new RealNbr(10000)));
        System.out.println(Collections.binarySearch(realNbrArrayList, new RealNbr(20000)));
        System.out.println(Collections.binarySearch(realNbrArrayList, new RealNbr(30000)));
        System.out.println(Collections.binarySearch(realNbrArrayList, new RealNbr(40000)));
        System.out.println(Collections.binarySearch(realNbrArrayList, new RealNbr(50000)));
    }
}
