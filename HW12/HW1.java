package hello;
import java.util.Scanner;
import java.math.BigDecimal;

public class HW1{
    public static String addFloatingPointNumbers(String num1, String num2) {
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        BigDecimal sum = bd1.add(bd2);
        return sum.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.next(); //讀取下一個字串，以空白字符為分割符
        String num2 = scanner.next();
        String result = addFloatingPointNumbers(num1, num2);
        System.out.println(result);
        scanner.close();
    }
}