public class AssignOperation{
  public static void main(String[] args){
    System.out.println("Hello!");
    System.out.println("func: " + func(1, 3));
  }

  static int func(int a, int b){
    int result = 0;
    result = a + b;
    result += a + b;
    result -= a - b;
    result *= a * b;
    result /= a / b;
    result &= a & b;
    result |= a | b;
    result ^= a ^ b;
    result %= a % b;
    result <<= a | b;
    result >>= a | b;
    result >>>= a | b;
    return result;
  }
}
