
public class BSTreeTest {

	public static void inOrderTraversal(BSTNode root) {
		if(root == null) {
			return;
		}
		inOrderTraversal(root.left);
		System.out.print(root + " ");
		inOrderTraversal(root.right);
	}
	public static void main(String[] args) {
		
		//PROBLEM A
		BSTree bst = new BSTree();
		bst.insert(5);
		bst.insert(18);
		bst.insert(3);
		bst.insert(25);
		bst.insert(27);
		bst.insert(45);
		bst.insert(97);
		bst.insert(88);
		bst.insert(26);
		bst.insert(15);
		bst.insert(17);
		bst.insert(16);
		
		//PROBLEM B
		inOrderTraversal(bst.root);
		System.out.println();
		
		//PROBLEM C
		if(bst.search(3)) {
			System.out.println("Search Successful");
		}
		else {
			System.out.println("Search not Successful");
		}
		if(bst.search(88)) {
			System.out.println("Search Successful");
		}
		else {
			System.out.println("Search not Successful");
		}
		if(bst.search(27)) {
			System.out.println("Search Successful");
		}
		else {
			System.out.println("Search not Successful");
		}
		if(bst.search(28)) {
			System.out.println("Search Successful");
		}
		else {
			System.out.println("Search not Successful");
		}

		//PROBLEM D AND E
		if(!bst.remove(88)) {
			System.out.println("Item not Found");
		}
		inOrderTraversal(bst.root);
		System.out.println();
		if(!bst.remove(18))  {
			System.out.println("Item not Found");
		}
		inOrderTraversal(bst.root);
		System.out.println();
		if(!bst.remove(5))  {
			System.out.println("Item not Found");
		}
		inOrderTraversal(bst.root);
		System.out.println();
		if(!bst.remove(30))  {
			System.out.println("Item not Found");
		}
		inOrderTraversal(bst.root);
		System.out.println();


	}

}


