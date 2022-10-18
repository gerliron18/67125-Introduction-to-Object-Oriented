package oop.ex4.data_structures;

/**
 * An implementation of the AVL tree data structure.
 */
public class AvlTree extends BinaryTree {
	private static final int UNBALANCED_VALUE = 1;
	private static final int BALANCED = 0;
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private static final int NO_SON_HEIGHT = -1;
	private static final int EMPTY_DIRECTION_CELL = 0;
	/**
	 * Act as a stack for the steps of the climbing interruptions
	 */
	private int[] direction;

	/**
	 * The default constructor.
	 */
	public AvlTree() {
		super();
	}

	/**
	 * A copy constructor that creates a deep copy of the given AvlTree.
	 *
	 * @param tree - The AVL tree to be copied.
	 */
	public AvlTree(BinaryTree tree) {
		super(tree);
	}

	/**
	 * A constructor that builds a new AVL tree containing all unique values in the input array.
	 *
	 * @param data - the values to add to tree.
	 */
	public AvlTree(int[] data) {
		super(data);
	}

	/**
	 * Go over the tree height from the node to the root and check that it is balanced as defined by the
	 * AVL protocol, if not, fix the tree by using left and right rotation.
	 *
	 * @param node - the node to fix the notation
	 */
	@Override
	void additionFixNotation(TreeNode node) {
		direction = new int[2];
		while (node != root && node != null && node.getParent() != null) {
			int balance = checkBalance(node.getParent());
			boolean isLeftSon = node.getParent().getLeftSon() == node;
			updateDirection(isLeftSon);
			TreeNode nodeParent = node.getParent();

			if (balance == LEFT && direction[0] == RIGHT && direction[1] == RIGHT) {
				rightRotate(nodeParent);
			} else if (balance == LEFT && direction[0] == RIGHT && direction[1] == LEFT) {
				leftRotate(node);
				rightRotate(nodeParent);
			} else if (balance == RIGHT && direction[0] == LEFT && direction[1] == RIGHT) {
				rightRotate(node);
				leftRotate(nodeParent);
			} else if (balance == RIGHT && direction[0] == LEFT && direction[1] == LEFT) {
				leftRotate(nodeParent);
			}
			node = node.getParent();
		}
	}


	/**
	 * /**
	 * Go over the tree height from the node to the root and check that it is balanced as defined by the
	 * AVL protocol, if not, fix the tree by using left and right rotation.
	 *
	 * @param node - the node to check the notation
	 */
	@Override
	void deletionFixNotation(TreeNode node) {
		while (node != null) {
			int balance = checkBalance(node);

			// Left sub tree is higher
			if (balance == LEFT) {
				int childrenDifference = checkChildrenHeights(node.getLeftSon());
				if (childrenDifference != LEFT) {
					rightRotate(node);
				} else {
					leftRotate(node.getLeftSon());
					rightRotate(node);
				}
				//Right sub tree is higher
			} else if (balance == RIGHT) {
				int childrenDifference = checkChildrenHeights(node.getRightSon());
				if (childrenDifference != RIGHT) {
					leftRotate(node);
				} else {
					rightRotate(node.getRightSon());
					leftRotate(node);
				}
			}
			node = node.getParent();
		}
	}

	/**
	 * Act like a stack of the direction - Update the direction array by advancing the previous direction to
	 * the next index and add the new direction
	 *
	 * @param isLeftSon - is the direction we update is due to a left son movement
	 */
	private void updateDirection(boolean isLeftSon) {
		// If the cell is not empty, push the previous direction to the next index
		if (direction[0] != EMPTY_DIRECTION_CELL) {
			direction[1] = direction[0];
		}
		// Update the first index to be the direction of the movement between the children and the parent
		direction[0] = isLeftSon ? RIGHT : LEFT;
	}


	/**
	 * Make a right rotation to a node to keep the notation of an avl tree
	 *
	 * @param node - the node which should be rotated right
	 */
	private void rightRotate(TreeNode node) {
		// Get pointers to all the nodes which will be moved during the rotation
		TreeNode leftSonNode = node.getLeftSon();
		TreeNode parentNode = node.getParent();
		flipSonParent(leftSonNode, parentNode);

		node.setLeftSon(leftSonNode.getRightSon());
		leftSonNode.setRightSon(node);
		node.updateHeight();
	}


	/**
	 * Make a right rotation to a node to keep the notation of an avl tree
	 *
	 * @param node - the node which should be rotated right
	 */
	private void leftRotate(TreeNode node) {
		// Get pointers to all the nodes which will be moved during the rotation
		TreeNode rightSonNode = node.getRightSon();
		TreeNode parentNode = node.getParent();
		flipSonParent(rightSonNode, parentNode);

		node.setRightSon(rightSonNode.getLeftSon());
		rightSonNode.setLeftSon(node);
		node.updateHeight();
	}

	/**
	 * Connect a child of a node to it's parent by changing the pointers
	 *
	 * @param son    - the son of the node
	 * @param parent - the parent of the node
	 */
	private void flipSonParent(TreeNode son, TreeNode parent) {
		if (parent == null) {
			son.setParent(null);
			root = son;
		} else if (parent.getValue() > son.getValue()) {
			parent.setLeftSon(son);
		} else if (parent.getValue() < son.getValue()) {
			parent.setRightSon(son);
		}
	}

	/**
	 * Check if a node is balanced, meaning if the difference between it's children is less than 1.
	 * If not, return the disruption direction
	 *
	 * @param node - the node to check it's balance
	 * @return - -1 if left disruption, 1 if right and 0 if the tree is balanced
	 */
	private int checkBalance(TreeNode node) {
		int childrenDifference = checkChildrenHeights(node);
		if (Math.abs(childrenDifference) <= UNBALANCED_VALUE) {
			return BALANCED;
		} else if (childrenDifference > 0) {
			return LEFT;
		} else {
			return RIGHT;
		}
	}

	/**
	 * Check the difference between the left and right son height
	 *
	 * @param node - the node to check it's children height difference
	 * @return - the difference between the children height
	 */
	private int checkChildrenHeights(TreeNode node) {
		int leftSonHeight = node.getLeftSon() != null ? node.getLeftSon().getHeight() : NO_SON_HEIGHT;
		int rightSonHeight = node.getRightSon() != null ? node.getRightSon().getHeight() : NO_SON_HEIGHT;
		return leftSonHeight - rightSonHeight;

	}

	/**
	 * Calculates the minimum number of nodes in an AVL tree of height h.
	 *
	 * @param h - the height of the tree (a non-negative number) in question.
	 * @return - the minimum number of nodes in an AVL tree of the given height.
	 */
	public static int findMinNodes(int h) {
		// The minimal number of nodes for an avl tree with height h is S(h-1) + S(h-2) + 1
		//
		int firstValue = 1;
		int secondValue = 2;
		int minNodes = 0;

		if (h == 0) {
			return firstValue;
		} else if (h == 1) {
			return secondValue;
		} else {
			for (int i = 2; i <= h; i++) {
				minNodes = firstValue + secondValue + 1;
				firstValue = secondValue;
				secondValue = minNodes;
			}
			return minNodes;
		}
	}

}
