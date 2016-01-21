import java.util.*;

public class cf{
  public static void main(String[] args){
    System.out.println("Hello!");
    System.out.println("func 1 " + func(1));
  }

  static int func(int a){
    int result = 0;
    if(a > 0){
        result = 1;
    }
    return result;
  }
}
