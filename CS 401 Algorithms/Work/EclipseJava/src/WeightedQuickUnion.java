/**
 * Colin Zhou
 * Fatma Serce
 * Assignment 1
 * 05/03/2021
 * Uses weighted quick union with path compression to determine if the grid of nxn values can
 * hold soil or not. 	
 * 
 */


import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion{
	
	public WeightedQuickUnion(int[][] array) {
		super(array.length); //calls superclass constructor
		
	}
	
	public void iterateArray(int[][] array) { //iterate through array and find where the components are connected
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array[0].length - 1; j++) {
				if (array[i][j] == array[i + 1][j] && array[i + 1][j] == 1) {
					union(createMarker(i, j, array.length), createMarker(i + 1, j, array.length));
				}
				
				if (array[i][j] == array[i][j + 1] && array[i][j + 1] == 1) {
					union(createMarker(i, j, array.length), createMarker(i, j + 1, array.length));
				}
			}
		}
	}
	
	public int createMarker(int row, int column, int totalLength) {
		return row * column + totalLength;
	}

	@Override
	public void union(int value1, int value2) { //attaches smaller component to bigger component's root
		int root1 = root(value1);
		int root2 = root(value2);

		if (size[root1] < size[root2]) {
			id[root1] = root2;
			size[root2] += size[root1];
		} else if (size[root1] > size[root2]) {
			id[root2] = root1;
			size[root1] += size[root1];
		}
		System.out.println("Size after: " + Arrays.toString(size));
	}
	
	public void doesSoilHold(int[][]array) {
		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array[array.length - 1].length; j++) {
				if (isConnected(i, j) && array[0][j] == 1) {
					System.out.println("Allows water to drain");
					return;
				}
			}
		}
		System.out.println("Doesn't allow water to drain");
	}

}
