package hello;
import java.lang.Math;
import java.util.Scanner;

class Point {
    private int vertical;
    private int horizontal;

    public Point() {
        this.vertical = 0;
        this.horizontal = 0;
    }

    public void Set(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public void Move(int x, int y) {
        this.vertical += x;
        this.horizontal += y;
    }

    public void Rotate() {
        int temp = this.vertical;
        this.vertical = this.horizontal;
        this.horizontal = -temp;
    }

    public int RetrieveVertical() {
        return this.vertical;
    }

    public int RetrieveHorizontal() {
        return this.horizontal;
    }

    public int calculateManhattanDistance(Point other) {
        return Math.abs(this.vertical - other.vertical) + Math.abs(this.horizontal - other.horizontal);
    }

    public double ChebyshevDistance(Point other) {
        int dx = Math.abs(this.vertical - other.vertical);
        int dy = Math.abs(this.horizontal - other.horizontal);
        return Math.max(dx, dy);
    }
}

public class HW3 {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	int[] a = new int[6];
    	for(int i=0;i<6;i++) {
    		a[i] = scanner.nextInt();
    	}
        Point origin = new Point();
        Point other = new Point();
        origin.Set(a[0], a[1]);
        other.Set(a[2], a[3]);
        System.out.println(origin.RetrieveVertical() + " " + origin.RetrieveHorizontal());        
        origin.Move(a[4],a[5]);
        System.out.println(origin.RetrieveVertical() + " " + origin.RetrieveHorizontal()); 
        for(int i=0;i<4;i++) {
        	origin.Rotate();
        	System.out.println(origin.RetrieveVertical() + " " + origin.RetrieveHorizontal()); 
        }
        System.out.println(origin.calculateManhattanDistance(other));
        System.out.println(origin.ChebyshevDistance(other));
        scanner.close();
    }
}