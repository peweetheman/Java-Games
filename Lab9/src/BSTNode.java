public class BSTNode
{
	int key;
	BSTNode parent;
	BSTNode left;
	BSTNode right;
	
	public BSTNode(int key) {
		this.key = key;
	}
	
	public BSTNode(int key, BSTNode left, BSTNode right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return "" + this.key;
	}
}
