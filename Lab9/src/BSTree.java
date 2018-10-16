
public class BSTree {

	BSTNode root;


	public BSTree(BSTNode root) {
		this.root = root;
	}
	public BSTree() {
	}

	public BSTNode minimum(BSTNode root) {
		if(root.left!=null) {
			return minimum(root.left);
		}
		return root;
	}
	public BSTNode maximum(BSTNode root) {
		if(root.right!=null) {
			return maximum(root.right);
		}
		return root;
	}
	public BSTNode successor(BSTNode root) {
		if(root.right!=null) {
			return minimum(root.right);
		}
		else {
			return ancestorSuccessor(root);
		}
	}

	private BSTNode ancestorSuccessor(BSTNode root) {
		if(root.parent.left!=null) {
			return root.parent;
		}
		else {
			return ancestorSuccessor(root.parent);
		}
	}
	public BSTNode predecessor(BSTNode root) {
		if(root.left!=null) {
			return maximum(root.left);
		}
		else {
			return ancestorPredecessor(root);
		}
	}

	private BSTNode ancestorPredecessor(BSTNode root) {
		if(root.parent.right!=null) {
			return root.parent;
		}
		else {
			return ancestorSuccessor(root.parent);
		}
	}
	public boolean insert(int key) {
		if (root == null) {
			root = new BSTNode(key);
			return true;
		}
		return recursiveInsert(root, key);
	}

	public boolean recursiveInsert(BSTNode n, int key) {
		if(n.key == key) {
			return false;
		}		
		if(key < n.key) {
			if(n.left == null) {
				n.left = new BSTNode(key);
				n.left.parent = n;
				return true;
			}
			return recursiveInsert(n.left, key);
		}else if(key >= n.key) {
			if(n.right == null) {
				n.right = new BSTNode(key);
				n.right.parent = n;
				return true;
			}
			return recursiveInsert(n.right, key);
		}
		return true;
	}

	public boolean remove(int key) {
		if(root == null) {
			return false;
		}
		return removeRecursive(root, key);
	}

	public boolean removeRecursive(BSTNode n, int key) {
		if(n.key == key) {
			if(n.left == null && n.right == null) {
				if(n.parent.left.key == key) {
					n.parent.left = null;
				}
				else {
					n.parent.right = null;
				}
				return true;
			}
			else if(n.left == null) {
				if(n.parent.key > key) {
					n.parent.left = n.right;
					n.right.parent = n.parent;
				}
				else {
					n.parent.right = n.right;
					n.right.parent = n.parent;
				}
			}
			else if(n.right == null) {
				if(n.parent.key > key) {
					n.parent.left = n.left;
					n.left.parent = n.parent;
				}
				else {
					n.parent.right = n.left;
					n.left.parent = n.parent;
				}
			}
			else {
				int temp = successor(n).key;
				removeRecursive(n, successor(n).key);
				n.key = temp;
			}
			return true;
		}
		if(key < n.key) {
			if(n.left == null) {
				return false;
			}
			return removeRecursive(n.left, key);
		}
		if(key > n.key) {
			if(n.right == null) {
				return false;
			}
			return removeRecursive(n.right, key);
		}
		return true;
	}

	public boolean search(int key) {
		return recursiveSearch(key, root);
	}
	
	private boolean recursiveSearch(int key, BSTNode n) {
		if(n.key == key) {
			return true;
		}
		if(key < n.key) {
			if(n.left == null) {
				return false;
			}
			return recursiveSearch(key, n.left);
		}
		if(key > n.key) {
			if(n.right == null) {
				return false;
			}
			return recursiveSearch(key, n.right);
		}
		return true;
	}
	public void preorder(BSTNode n) {
		if(n == null) {
			return;
		}
		System.out.print(n + " ");
		preorder(n.left);
		preorder(n.right);
	}

}


