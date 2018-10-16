import java.util.LinkedList;
import java.util.Queue;

public class BTNode {
	int data;
	BTNode left;
	BTNode right;
	
	BTNode(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	
	public BTNode getLeft() {
		return this.left;
	}
	public BTNode getRight() {
		return this.right;
	}
	public int getData() {
		return this.data;
	}
	public void setLeft(BTNode left) {
		this.left = left;
	}
	public void setRight(BTNode right) {
		this.right = right;
	}
	public void setData(int data) {
		this.data = data;
	}
}


