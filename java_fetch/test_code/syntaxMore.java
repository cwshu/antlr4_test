import java.util.List;
import java.io.*;

public class syntaxMore {
    public static void main(String[] args) {
        System.out.println("hello world!");
    }

    public syntaxMore(){}
    public syntaxMore(int a){}
    public syntaxMore(int a, int b){}
    public syntaxMore(int a, int b, double... c){}

    void funcRet1(){}
    int funcRet2(){ return 0; }
    String funcRet3(){ return ""; }
    int[] funcRet4(){ return null; }
    String[][][] funcRet5(){ return null; }
    List<String> funcRet6(){ return null; }
    List<String[]> funcRet7(){ return null; }

    String[] funcPara1(){ return null; }
    String[] funcPara2(double p1){ return null; }
    String[] funcPara3(String p1){ return null; }
    String[] funcPara4(String[] p1){ return null; }
    String[] funcPara5(String p1[]){ return null; }

    double member1;
    String member2;
    double[][] member3;
    String member4[];
    List<boolean> member5;
    List<String> member6;
    List<boolean[][]> member7;
    List<String[]> member8;
    List<String[]> member9[][];
    List<String> member10, member11, member12[], member13[][][], member14;
}

