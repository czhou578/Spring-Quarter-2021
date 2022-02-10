/**
 * Colin Zhou
 * Fatma Serce
 * Assignment 2 / CS 401
 * 5/14/2021
 * This class is the main implementation for this assignment of drawing the convex hull.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class ConvexHullBuilder {
	
	Iterable<Point2D> finalHull;
	ArrayList<Point2D> tempArray;
	
	public ConvexHullBuilder(ArrayList<Point2D> arraylist) {
		finalHull = arraylist;
		tempArray = new ArrayList<Point2D>(arraylist);
	}
	
	public Iterable<Point2D> sortY(Iterable<Point2D> list) {
		
		Collections.sort(tempArray);		
		list = tempArray;
		
		return list;
	}
	
	public Iterable<Point2D> polar(Iterable<Point2D> hull) { //done
		ArrayList<Point2D> list = new ArrayList<Point2D>();
		Point2D startPoint;
		Iterator<Point2D> iter = hull.iterator();
		startPoint = iter.next();
		
		while (iter.hasNext()) {
			Point2D temp = iter.next();
			list.add(temp);			
			Collections.sort(list, startPoint.polarOrder());
		}
		
		Collections.reverse(list);
		
		hull = list;
		
		return hull;
	}
	
	public static Point2D next_to_top(Stack<Point2D> stack) {
		Iterator iter = stack.iterator();
		int counter = 0;
		Point2D nextTop = null;
		while (iter.hasNext()) {
			if (counter == 2) {
				break;
			}
			nextTop = (Point2D) iter.next();
			counter++;
		}
		
		return nextTop;
	}
	
	public Iterable<Point2D> leftOrRight(Iterable<Point2D> hull, Point2D start) {
		Stack<Point2D> stack = new Stack<Point2D>();
		Iterator iter = finalHull.iterator();
		Iterator<Point2D> stackIter = stack.iterator();
		ArrayList<Point2D> finalList = new ArrayList<Point2D>();
		
		for (Point2D point: hull) {
			while (stack.size() > 1 && point.ccw(next_to_top(stack), stack.peek(), point) < 0) {
				stack.pop();
			}	
			stack.push(point);
		}
		
		return stack;		
	}

	public Iterable<Point2D> hull() {
		Iterator<Point2D> iter = finalHull.iterator();
		Point2D startPoint = iter.next();
		
		sortY(finalHull);
		polar(finalHull);
		return leftOrRight(finalHull, startPoint);
				
	}	
}
