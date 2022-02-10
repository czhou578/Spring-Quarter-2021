
public interface UnionFind {
	
	public void union(int value1, int value2);
	public boolean isConnected(int node1, int node2);
	public int find(int p);
	public int getConnectedComponentCount();

}
