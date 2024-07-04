package hello;
import java.math.BigDecimal;
import java.util.Scanner;

public class HW2 {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
        	scanner.nextLine();
        	String x = scanner.next();
            String y = scanner.next();
            // 計算和並輸出
            String sum = add(x, y);
            System.out.println(sum);
        }
        scanner.close();
    }

    private static String add(String x, String y) {
    	// 取整數部分
    	String Xint = x.split("\\.")[0]; //string to integer
        String Yint = y.split("\\.")[0];
        BigDecimal XX = new BigDecimal(Xint);
        BigDecimal YY = new BigDecimal(Yint);
        BigDecimal sumXY = XX.add(YY); //整數部分相加的數值
        // 取小數部分
        String repeatx = x.split("\\.")[1];
        String repeaty = y.split("\\.")[1];
        // 找出循環的最小公倍數
        int lcm = findlcm(repeatx.length(),repeaty.length());
        // 循環部分便相同長度
        String xrepeat = samelen(repeatx, lcm / repeatx.length());
        String yrepeat = samelen(repeaty, lcm / repeaty.length());
        // 把兩個string轉BigDecimal加起來，此時為一個整數BigDecimal
        BigDecimal bd1 = new BigDecimal(xrepeat);
        BigDecimal bd2 = new BigDecimal(yrepeat);
        BigDecimal sumdecimal = bd1.add(bd2);
        // 將整數換回小數
        sumdecimal = sumdecimal.movePointLeft(xrepeat.length());
        // 如果有進位，就尾數+1
        if (sumdecimal.compareTo(new BigDecimal("1")) > 0) { //有進位 //加起來>1
        	sumdecimal = sumdecimal.add(BigDecimal.ONE.divide(BigDecimal.TEN.pow(xrepeat.length())));
        	sumXY = sumXY.add(BigDecimal.ONE);
        }
        //處理小數點後重複的問題
        //substring(begin,end)：（包括）开始,（不包括）结束
        String temp = sumdecimal.toString().split("\\.")[1];
        for(int i=1;i<=temp.length()/2;i++) {
        	if(temp.length()%i == 0) { //如果i為小數位數的因數
        		String grab = temp.substring(0,i); //抓我要看幾個幾個一組//System.out.println(grab);
        		int repeatornot = 1;
        		for(int j=i;j<temp.length();j+=i) {
        			String aim = temp.substring(j,j+i);
        			if(!aim.equals(grab)) {
        				repeatornot = 0;
        				break;
        			}
        		}
        		if(repeatornot==1) {
        			temp = grab;
        			break;
        		}
        	}
        }
      //處理小數是9的問題
        if(temp.equals("9")) {
        	temp = "0";
        	sumXY = sumXY.add(BigDecimal.ONE);
        	return sumXY.toString();
        }
        else {
        	BigDecimal ansdecimal = new BigDecimal(temp);
            ansdecimal = ansdecimal.movePointLeft(temp.length());
            BigDecimal ans = ansdecimal.add(sumXY);
            return ans.toString();
        }
    }
    
    private static int findlcm(int a, int b) {
        int max = Math.max(a, b); //取大
        int min = Math.min(a, b); //取小

        for (int i = 1; i <= min; i++) {
            if ((max * i) % min == 0) {
                return i * max;
            }
        }
        return -1;
    }
    
    private static String samelen(String s, int times) {
        StringBuilder repeated = new StringBuilder(); //開一個可變string
        for (int i = 0; i < times; i++) {
            repeated.append(s);
        }
        return repeated.toString();
    }
}