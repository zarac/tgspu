package p2;
import java.lang.reflect.*;
import javax.swing.*;
/*
TestProgP2.java
Testprogram för metoderna och programmet i Programmeringsuppgift 2
Rolf Axelsson
 */
public class TestProgP2 {
    
    public static void invokeMethod(String className, String methodName, Class[] argumentTypes, Object[] arguments) throws Exception {
        Class cls = Class.forName(className);
        Object prog2 = cls.getConstructor(new Class[]{}).newInstance(new Object[]{});
        Method method = cls.getDeclaredMethod(methodName, argumentTypes);
        method.invoke(prog2, arguments);
    }
    
    public void runMain(String classname) throws Exception {
        Class cls = Class.forName(classname);
        Method method = cls.getDeclaredMethod("main",new Class[]{String[].class});
        method.invoke(null,new Object[]{new String[]{"dummy"}}); 
    }
    
    public void programLoop() {
        int val = -1;
        String meny = "Välj den metod som ska anropas / det program som ska exekveras:\n\n" +
                "1. uppgift2a()\n" +
                "2. uppgift2b()\n" +
                "3. uppgift2c()\n" +
                "4. sparande(double,duble,int)\n" +
                "5. Race\n" +
                "6. siffersumma(int)\n" +
                "7. delbartMed(int,int,int)\n" +
                "8. fakultet(int)\n" +
                "0. Avsluta programmet\n\n" +
                "Ange ditt val";
        do {
            try {
                switch(val) {
                    case 1 : invokeMethod("p2.Prog2","uppgift2a",null,null); break;
//                    case 1 : invokeMethod("p2.Prog2","uppgift2a",new Class[]{},new Object[]{}); break;
                    case 2 : invokeMethod("p2.Prog2","uppgift2b",new Class[]{},new Object[]{}); break;
                    case 3 : invokeMethod("p2.Prog2","uppgift2c",new Class[]{},new Object[]{}); break;
                    case 4 : invokeMethod("p2.Prog2","sparande",new Class[]{double.class,double.class,int.class},new Object[]{new Double(1000),new Double(0.04),new Integer(9)}); 
                             System.out.println("\n----------------------------------------------------");
                             invokeMethod("p2.Prog2","sparande",new Class[]{double.class,double.class,int.class},new Object[]{new Double(1000),new Double(0.08),new Integer(12)});
                             break;
                    case 5 : runMain("p2.StartRace"); break;
                    case 6 : invokeMethod("p2.Prog2","siffersumma",new Class[]{int.class},new Object[]{new Integer(500)});
                             invokeMethod("p2.Prog2","siffersumma",new Class[]{int.class},new Object[]{new Integer(504)});
                             invokeMethod("p2.Prog2","siffersumma",new Class[]{int.class},new Object[]{new Integer(74424)}); 
                             break;
                    case 7 : invokeMethod("p2.Prog2","delbartMed",new Class[]{int.class,int.class,int.class},new Object[]{new Integer(20),new Integer(100),new Integer(200)});
                             invokeMethod("p2.Prog2","delbartMed",new Class[]{int.class,int.class,int.class},new Object[]{new Integer(4),new Integer(21),new Integer(30)});
                             break;
                    case 8 : invokeMethod("p2.Prog2","fakultet",new Class[]{int.class},new Object[]{new Integer(4)});
                             invokeMethod("p2.Prog2","fakultet",new Class[]{int.class},new Object[]{new Integer(7)});
                             invokeMethod("p2.Prog2","fakultet",new Class[]{int.class},new Object[]{new Integer(-2)});
                             break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            val = val = Integer.parseInt( JOptionPane.showInputDialog(meny) );
        }while(val!=0);
    }
    
    public static void main(String[] args) {
        TestProgP2 prog = new TestProgP2();
        prog.programLoop();
    }
}
