
public class QuickUnion implements UnionFind {
	
	protected int id[];
	protected int size[];
	protected int count; //number of connected components
	protected int length;
	
	public QuickUnion(int num) {
		length = num * num;
		id = new int[length];
		size = new int[length];
		
		initialize();
	}
	
	private void initialize() {
		for (int i = 0; i < length; i++) { //assigns all array elements to i value to begin
			id[i] = i; 
			size[i] = 1;
		}
	}

	@Override
	public void union(int p, int q) { //makes the two roots equal to each other, union two cells
		int pRoot = root(p);
		int qRoot = root(q);
		
		if(pRoot == qRoot)
			return;
		
		id[pRoot] = qRoot;
		
		count--;
		
	}

	protected int root(int value) { // if p == id[p] then it is the root; p is value of cell
		while (value != id[value]) {
			id[value] = id[id[value]];
			value = id[value];
		}
		return value;	
	}
	
	
	@Override
	public boolean isConnected(int node1, int node2) { //determines if p and q are in the same set
		int pRoot = root(node1);
		int qRoot = root(node2);
		return pRoot == qRoot;
	}

	@Override
	public int find(int p) { //finds the parent of p and returns the parent
		return root(p);
	}

	@Override
	public int getConnectedComponentCount() { //returns num of connected components
		return count;
	}
}
