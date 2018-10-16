import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//Patrick Phillips
//Partner: Brandon Toops
public class Lab8P2 {

	public static BTNode reconstruct_tree(int[] inOrder, int[] preOrder) {
		if(inOrder.length!=preOrder.length) {
			return null;
		}
	    return reconstructTreeRecursive(inOrder, preOrder, 0, inOrder.length-1, 0, preOrder.length-1);
	}
	 
	public static BTNode reconstructTreeRecursive(int[] inOrder, int[] preOrder, int inOrderStart, int inOrderEnd, int preOrderStart, int preOrderEnd){
		if(preOrderStart>preOrderEnd||inOrderStart>inOrderEnd){
	        return null;
	    }
		//Base Case
		
	    int nodeValue = preOrder[preOrderStart];
	    BTNode root = new BTNode(nodeValue);
	    int seperation=0;
	    for(int i=0; i<inOrder.length; i++){
	        if(nodeValue == inOrder[i]){
	            seperation=i;
	            break;
	        }
	    }
	    root.left = reconstructTreeRecursive(inOrder, preOrder, inOrderStart, seperation-1, preOrderStart+1, preOrderStart+(seperation-inOrderStart));
	    root.right= reconstructTreeRecursive(inOrder, preOrder, seperation+1, inOrderEnd, preOrderStart+(seperation-inOrderStart+ 1) , preOrderEnd);
	    return root;
	}

	public static void main(String[] args) {
		Lab8P1 hi = new Lab8P1();
		int[] pre = {5,4,3,0,8,7,1,2,9};
		int[] in = {3,4,8,7,0,1,5,9,2};
		BTNode root = reconstruct_tree(in, pre);
		hi.level_order_print(root);
	}

}
