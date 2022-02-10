/**
 * The test cases I ran was to determine the expected points that were going to be present in the 
 * convex hull. I ran assertions to determine whether my expected stack of points was going to be 
 * equal to the actual returned stack. I have included screenshots for each test case. The CommentedOutTestCase
 * requires for you to uncomment the sections in this file that are uncommented.
 */

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;

import org.junit.jupiter.api.Test;

class ConvexHullBuilderTest {

	@Test
	void test() {
		ArrayList<Point2D> testlist = new ArrayList<Point2D>();
//		Point2D point1 = new Point2D(3588.0, 7563.0);
//		Point2D point2 = new Point2D(5050.0, 6975.0);
//		Point2D point3 = new Point2D(3575.0, 6450.0);
		
		Point2D point1 = new Point2D(3950.0, 4800.0);
		Point2D point2 = new Point2D(5713.0, 5438.0);
		Point2D point3 = new Point2D(4250.0, 6775.0);
		Point2D point4 = new Point2D(3300.0, 7150.0);
		Point2D point5 = new Point2D(2650.0, 7638.0);
		

		testlist.add(point1);
		testlist.add(point2);
		testlist.add(point3);
		
		testlist.add(point4); //uncomment 35 and 36 if testing first case.
		testlist.add(point5);

		
		Stack<Point2D> stack = new Stack<Point2D>();
//		stack.push(point1);
//		stack.push(point3);
		stack.push(point1);
		stack.push(point2);
		stack.push(point3);
		stack.push(point5);
		
		System.out.println("Test stack:" + stack);
		
		ConvexHullBuilder junit = new ConvexHullBuilder(testlist);
		Iterable<Point2D> hull = testlist;
		
		junit.sortY(hull);
		junit.polar(hull);
		
		System.out.println("Test returned stack:" + junit.leftOrRight(hull, point1).toString());
		
		assertEquals(stack.toString(), junit.leftOrRight(hull, point1).toString());
		
		
	}

}
