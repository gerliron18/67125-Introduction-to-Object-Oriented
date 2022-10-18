package oop.ex4.data_structures;

/**
 * Represent a node in a tree which hold the value, the left and right sons and parent.
 * Furthermore, hold the depth and height of it inside a tree and hold the methods to update those values
 */
public class TreeNode {
	/**
	 * The height of the node
	 */
	private int height;
	/**
	 * The depth of the node
	 */
	private int depth;
	/**
	 * The value inside the node
	 */
	private Integer value;
	/**
	 * The left and right sons of the node, and the parent
	 */
	private TreeNode leftSon, rightSon, parent;

	/**
	 * The default constructor.
	 */
	public TreeNode() {
		this.value = null;
		this.parent = null;
	}

	/**
	 * Create a node tree with a specific value which is connected to a parent node
	 *
	 * @param value  - the value for the node
	 * @param parent - the parent of the node in the tree
	 */
	public TreeNode(Integer value, TreeNode parent) {
		this.value = value;
		this.parent = parent;
		updateDepth();
	}

	/**
	 * @return the value of the node
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value - the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the height of the node
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Update the height of the current node and it's parent based on the number of children
	 */
	public void updateHeight() {
		// Calculate the new height based on the height of the left and right nodes
		int newHeight = 0;
		if (leftSon != null) {
			newHeight = leftSon.getHeight() + 1;
		}
		if (rightSon != null) {
			newHeight = Math.max(newHeight, rightSon.getHeight() + 1);
		}
		this.height = newHeight;

		// Update the parent
		if (parent != null) {
			parent.updateHeight();
		}
	}

	/**
	 * @return the depth of the node
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Update the depth of the node and it's sons, a depth of a node is the depth of it's parent + 1
	 */
	private void updateDepth() {
		depth = parent != null ? parent.getDepth() + 1 : 0;

		if (leftSon != null) {
			leftSon.updateDepth();
		}
		if (rightSon != null) {
			rightSon.updateDepth();
		}
	}

	/**
	 * @return the left son of a node
	 */
	public TreeNode getLeftSon() {
		return leftSon;
	}

	/**
	 * @param leftSon - set the given node as a left son
	 */
	public void setLeftSon(TreeNode leftSon) {
		this.leftSon = leftSon;
		if (leftSon != null){
			leftSon.setParent(this);
		}
	}

	/**
	 * @return the right son of anode
	 */
	public TreeNode getRightSon() {
		return rightSon;
	}

	/**
	 * @param rightSon - set the given node as a right son
	 */
	public void setRightSon(TreeNode rightSon) {
		this.rightSon = rightSon;
		if (rightSon != null){
			rightSon.setParent(this);
		}
	}

	/**
	 * @return the parent of the node
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * Change the parent of the node to the given parent and update the depth of all the nodes
	 *
	 * @param parent - the node to set as parent
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
		updateDepth();
	}

	/**
	 * @return true iff the node is a leaf, meaning no children
	 */
	public boolean isLeaf() {
		return leftSon == null && rightSon == null;
	}
}
