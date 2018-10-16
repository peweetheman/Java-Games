import java.util.LinkedList;
import java.util.Queue;

public class Lab8P1 {
	
	public static void level_order_print(BTNode root) {
		Queue<BTNode> q = new LinkedList<BTNode>();

		q.offer(root);
		while(!q.isEmpty()) {
			int numNodes = q.size();
			while(numNodes > 0) {
				BTNode current = q.poll();
				System.out.print(current.data + " ");
				numNodes--;
				if(current.left != null) {
					q.offer(current.left);

					
				}

				if(current.right != null) {

					q.offer(current.right);

				}

			}
			System.out.println();
		}

	}

	public static void main (String[] args) {
		BTNode root = new BTNode(1);
		root.setLeft(new BTNode(2));
		root.setRight(new BTNode(3));
		root.getLeft().setLeft(new BTNode(4));
		root.getLeft().setRight(new BTNode(5));
		root.getRight().setRight(new BTNode(6));
		level_order_print(root);
		
	}
}



