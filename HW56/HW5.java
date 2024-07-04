package hello;
import java.util.Scanner;

public class HW5 {
	public static void main(String[] args) {
		// Scan
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); //row
        int m = scanner.nextInt(); // col
        scanner.nextLine(); // consume newline character
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
        	String tempstr = scanner.nextLine().replaceAll(" ", "");
            for (int j = 0; j < m; j++) {
                board[i][j] = tempstr.charAt(j);
            }
        }
        String word = scanner.next();
        scanner.close();
        int truee = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, word, i, j, 0)) {
                	System.out.print("true");
                	truee = 0;
                }
            }
        }
        if(truee == 1)
        	System.out.print("false");
	}
	private static boolean dfs(char[][] board, String word, int i, int j, int k) {
        int n = board.length;
        int m = board[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != word.charAt(k)) {
            return false;
        }
        if (k == word.length() - 1) { 
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '/'; 
        boolean result = dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) ||
                dfs(board, word, i, j - 1, k + 1);

        board[i][j] = temp; // revert the board

        return result;
    }
}
