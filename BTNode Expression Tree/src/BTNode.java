public class BTNode<E> {
	private E data;
	private BTNode<E> left;
	private BTNode<E> right;

public BTNode(E initialData, BTNode initialRight, BTNode initialLeft){

data = initialData;
right = initialRight;
left = initialLeft;

}
//Gets Data from Node in tree
public E getData( )
{
return data;
}

	public BTNode getLeft() {
		return left;
	}

	public BTNode getRight() {
		return right;
	}

	public E getLeftmostData() {
		if (left == null)
			return data;
		else
			return left.getLeftmostData();
	}

	public E getRightmostData() {
		if (left == null)
			return data;
		else
			return left.getRightmostData();
	}
//Method Sets Data for node at given point
	public void setData(E x) {
		data = x;
	}
//Method Sets Data for node at left point and right point

	public void setLeft(BTNode<E> x) {
		left = x;
	}
	public void setRight(BTNode<E> x) {
		right = x;
	}
//Prints order of tree in preorder
	public void preorderPrint() {
		System.out.println(data);
		if (left != null)
			left.preorderPrint();
		if (right != null)
			right.preorderPrint();
	}
//Post Order Print
	public void postorderPrint() {
		if (left != null)
			left.postorderPrint();
		if (right != null)
			right.postorderPrint();
		System.out.println(data);
	}
// In Order print
	public void inorderPrint() {
		if (left != null) {
			System.out.print("(");
			left.inorderPrint();
		}
		System.out.print(" " + data + " ");
		if (right != null) {
			right.inorderPrint();
			System.out.print(")");
		}
	}

}