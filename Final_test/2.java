package test2;

import java.util.Scanner;

public class ans {
	public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	String n1 = scanner.next();
    	String n2 = scanner.next();
    	int n = n1.length();
    	int a = 0;int b = 0;
    	for(int i=0;i<n;i++) {
    		if(n1.charAt(i)==n2.charAt(i)) {
    			a++;
    			continue;
    		}
    		for(int j=0;j<n;j++) {
    			if(i!=j && (n1.charAt(i)==n2.charAt(j))) {
    				b++;
    				break;
    			}
    		}
    	}
    	System.out.println(a + "A" + b + "B");
    	scanner.close();
    }
}
/*
package test;
//1A2B
public class XAXB {
  public static void main(String[] args) {
      String s1 = args[0];
      String s2 = args[1];
      char[] c1 = s1.toCharArray();
      char[] c2 = s2.toCharArray();
      int [] count = new int[128];
      int A = 0, B = 0;
      for(int i = 0; i < c1.length; i++)
      {
          count[c1[i]] ++;
      }
      for(int i = 0; i < c1.length; i++)
      {
          if(c2[i] == c1[i]) {
              A++;
              count[c2[i]]--;
          }
          else if(count[c2[i]] > 0) {
              B++;
              count[c2[i]]--;
          }
      }
      System.out.print(A+"A"+B+"B");
  }
}
*/