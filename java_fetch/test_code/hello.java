import java.util.*;

public class hello{
  public static void main(String[] args){
    System.out.println("Hello!");
    System.out.println("Add 1 + 3 = " + Add(1, 3));
  }

  static int Add(int a, int b){
    int result = 0;
    result = a + b;
    return result;
  }
}
