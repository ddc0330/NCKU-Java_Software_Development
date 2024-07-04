package test1;
import java.util.Scanner;

public class ans {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	int p = scanner.nextInt();
    	int q = scanner.nextInt();
    	int[] a = new int[p];
    	for(int i=0;i<p;i++) {
    		a[i] = scanner.nextInt();
    	}
    	int temp = 0;
    	int ans = 0;
    	for(int i=0;i<p;i++) {
    		temp=a[i];
    		for(int j=0;j<q-1;j++) {
    			temp *= a[i];
    		}
    		ans+=temp;
    	}
    	System.out.println(ans);
    	scanner.close();
    }
}