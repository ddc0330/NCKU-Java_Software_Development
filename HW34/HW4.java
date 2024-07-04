package hello;
import java.util.Scanner;

public class HW4 {
	public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
    	int m = 0;
    	double[][] a = new double[100][100];
    	out:
    	for(int i=1;i<=n+1;i++) {
    		for(int j=1;j<=n+1;j++) {
    			a[i][j]=scanner.nextInt();
    			if(j==1 && a[i][j]==-999) {
    				m = i-1;
    				break out;    				    			
    			}
    		}
    	}
    	n+=1;
    	String ans = solve(a,m,n);
        System.out.println(ans);
        scanner.close();
    }
	private static String solve(double[][] a,int m,int n) {
		int line = 1;
		for (int i = 1; i <= m; i++) {
			//change
			out:
			while(a[i][line]==0&&i!=m&&line<n) {
            	for(int j=i+1;j<=m;j++) {
        			if(a[j][line]!=0) {
        				double temp[] = new double [100];
        				temp = a[j];
        	            a[j] = a[i];
        	            a[i] = temp;
        	            break out;
        			}
        		}
            	line++;
			}
			//cut
	        for (int j = i + 1; j <= m; j++) {
                double ratio = a[j][line] / a[i][line];
                if(a[i][line]!=0) {
                	for (int k = line; k <= n; k++) {
                		a[j][k] -= ratio * a[i][k];
                	}
                }
	        }
	        //judge
		 	boolean allZero = true;
			for (int j = 1; j <= n-1; j++) { 
                if (a[i][j] != 0) {
                    allZero = false;
                }
            }
			if (allZero && a[i][n] != 0) {
                return "No solution"; 
            } 
			else if(i==m) {
				if((allZero && a[i][n] == 0) || m<(n-1)) {
					return "Infinite solutions";
				}
				else {
					return "The only solution";
				}
			}
		}
		return "0";
	}
}