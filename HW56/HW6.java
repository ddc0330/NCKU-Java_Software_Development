package hello;
import java.util.Scanner;

public class HW6 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (Validornot(input)) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }
        scanner.close();
    }
    
    public static boolean Validornot(String gameState) {
    	int countX = 0;
        int countO = 0;
        for (char c : gameState.toCharArray()) {
            if (c == 'X') {
                countX++;
            } else if (c == 'O') {
                countO++;
            }
        }
        if(Xlinenum(gameState)==0 && (Olinenum(gameState) == 0) && (countX==countO || countX==countO+1)) {
        	return true;
        }
        if(Xlinenum(gameState)==1 && (Olinenum(gameState) == 0) && (countX==countO+1)) {
        	return true;
        }
        if((Olinenum(gameState)) == 1 && (Xlinenum(gameState) == 0) && countX==countO) {
        	return true;
        }
        // find last round end or not ,end means not invalid
        if(Xlinenum(gameState)==2 && Olinenum(gameState)==0 && (countX==countO+1)) {
        	int notend=0;
        	for(int i=0;i<9;i++) {
        		char[] temp = gameState.toCharArray(); // 將字串轉換為字符數組
        	    temp[i] = '#';
        	    String tempstr = new String(temp);
        	    if(Xlinenum(tempstr)==0) {
        	    	notend=1;
        	    	break;
        	    }
        	}
        	if(notend==1) {
        		return true;
        	}
        }
        if(Olinenum(gameState)==2 && Xlinenum(gameState)==0 && countX==countO) {
        	int notend=0;
        	for(int i=0;i<9;i++) {
        		char[] temp = gameState.toCharArray(); // 將字串轉換為字符數組
        	    temp[i] = '#';
        	    String tempstr = new String(temp);
        	    if(Olinenum(tempstr)==0) {
        	    	notend=1;
        	    	break;
        	    }
        	}
        	if(notend==1) {
        		return true;
        	}
        }
        return false;
    }
    public static int Xlinenum(String gameState) {
    	int num=0;
        for (int i = 0; i < 3; i++) {
            int startIndex = i * 3;
            if (gameState.charAt(startIndex) == 'X' &&
                gameState.charAt(startIndex) == gameState.charAt(startIndex + 1) &&
                gameState.charAt(startIndex) == gameState.charAt(startIndex + 2)) {
            	num++;	
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameState.charAt(i) == 'X' &&
                gameState.charAt(i) == gameState.charAt(i + 3) &&
                gameState.charAt(i) == gameState.charAt(i + 6)) {
            	num++;
            }
        }
        if (gameState.charAt(0) == 'X' &&
            gameState.charAt(0) == gameState.charAt(4) &&
            gameState.charAt(0) == gameState.charAt(8)) {
        	num++;
        }
        if (gameState.charAt(2) == 'X' &&
            gameState.charAt(2) == gameState.charAt(4) &&
            gameState.charAt(2) == gameState.charAt(6)) {
        	num++;
        }
        return num;
    }
    public static int Olinenum(String gameState) {
    	int num=0;
        for (int i = 0; i < 3; i++) {
            int startIndex = i * 3;
            if (gameState.charAt(startIndex) == 'O' &&
                gameState.charAt(startIndex) == gameState.charAt(startIndex + 1) &&
                gameState.charAt(startIndex) == gameState.charAt(startIndex + 2)) {
            	num++;	
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameState.charAt(i) == 'O' &&
                gameState.charAt(i) == gameState.charAt(i + 3) &&
                gameState.charAt(i) == gameState.charAt(i + 6)) {
            	num++;
            }
        }
        if (gameState.charAt(0) == 'O' &&
            gameState.charAt(0) == gameState.charAt(4) &&
            gameState.charAt(0) == gameState.charAt(8)) {
        	num++;
        }
        if (gameState.charAt(2) == 'O' &&
            gameState.charAt(2) == gameState.charAt(4) &&
            gameState.charAt(2) == gameState.charAt(6)) {
        	num++;
        }
        return num;
    }
}
