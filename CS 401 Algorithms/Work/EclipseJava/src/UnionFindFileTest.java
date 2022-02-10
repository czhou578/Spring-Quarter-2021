import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import edu.princeton.cs.algs4.In;

public class UnionFindFileTest {

	@Test
	public void testQuickFindCount() {
		In in = new In("UFDataSet3.txt"); //edit this file with the different inputs, save, and run from here
		
		int[] reading = in.readAllInts();
		
		int numElements = reading.length;
		
		int n = (int) Math.sqrt(numElements);
		int [][]array = new int[n][n];
		
		in.close();
		
		In in2 = new In("UFDataSet3.txt");
		while(in2.hasNextLine()) {
			for (int i = 0; i < array.length; i++) {
	            String[] line = in2.readLine().trim().split(" ");
	            for (int j=0; j < array.length; j++) {
	                array[i][j] = Integer.parseInt(line[j]);
	            }
			}
		}
		
		WeightedQuickUnion uf = new WeightedQuickUnion(array);
		uf.iterateArray(array);
		uf.doesSoilHold(array);
	}
}
