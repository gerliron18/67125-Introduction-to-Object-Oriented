package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the binary tree data structure.
 */
public class BinaryTree implements Iterable<Integer> {
	private static final int TWO_BASE = 2;
	private static final int ONE_DEDUCTION = 1;
	private static final int ONE_ADDITION = 1;
	private static final int NOT_FOUND = -1;

	/**
	 * The number of elements in the tree
	 */
	private int size = 0;
	/**
	 * The root of the tree
	 */
	TreeNode root = null;

	/**
	 * The default constructor.
	 */
	public BinaryTree() {
	}

	/**
	 * A copy constructor that creates a deep copy of the given AvlTree. The new tree contains all the
	 * values of the given tree, but not necessarily in the same structure.
	 *
	 * @param tree - The AVL tree to be copied.
	 */
	public BinaryTree(BinaryTree tree) {
		if (tree != null){
			Iterator<Integer> treeIterator = tree.iterator();
			while (treeIterator.hasNext()) {
				add(treeIterator.next());
			}
		}
	}

	/**
	 * A constructor that builds a new AVL tree containing all unique values in the input array.
	 *
	 * @param data - the values to add to tree.
	 */
	public BinaryTree(int[] data) {
		if (data != null){
			for (int value : data) {
				add(value);
			}
		}
	}

	/**
	 * @return - an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an
	 * ascending order, and does NOT implement the remove() method.
	 */
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			TreeNode node = null;
			boolean finished = false;

			@Override
			public boolean hasNext() {
				if (node == null && !finished && root != null){
					node = getSubTreeMinNode(root);
				}
				return node != null;
			}

			@Override
			public Integer next() {
				if (!hasNext()){
					throw new NoSuchElementException();
				}
				Integer value = node.getValue();
				node = getSuccessor(node);
				if (node == null){
					finished = true;
				}

				return value;
			}

			@Override
			public void remove(){
				throw new java.lang.UnsupportedOperationException();
			}
		};
	}

	/**
	 * Add a new node with the given key to the tree.
	 *
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added, false
	 * otherwise.
	 */
	public boolean add(int newValue) {
		if (size == 0) {
			root = new TreeNode();
			root.setValue(newValue);
		} else {
			if (contains(newValue) != NOT_FOUND) {
				return false;
			} else {
				TreeNode parentNode = placementFounder(newValue, root);
				TreeNode newNode = new TreeNode(newValue, parentNode);
				if (parentNode.getValue() > newValue) {
					parentNode.setLeftSon(newNode);
				} else {
					parentNode.setRightSon(newNode);
				}
				newNode.updateHeight();
				additionFixNotation(newNode);
			}
		}
		size++;
		return true;
	}

	/**
	 * Check whether the tree contains the given input value.
	 *
	 * @param searchVal - value to search for
	 * @return if val is found in the tree, return the depth of the node (0 for the root) with the given
	 * value if it was found in the tree, -1 otherwise.
	 */
	public int contains(int searchVal) {
		if (size == 0) {
			return -1;
		} else {
			TreeNode node = placementFounder(searchVal, root);
			if (searchVal == node.getValue()) {
				return node.getDepth();
			} else {
				return NOT_FOUND;
			}
		}
	}

	/**
	 * Removes the node with the given value from the tree, if it exists.
	 *
	 * @param toDelete - the value to remove from the tree.
	 * @return - true if the given value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		if (contains(toDelete) == NOT_FOUND) {
			return false;
		}

		TreeNode node = placementFounder(toDelete, root);
		removeNode(node);
		size--;
		return true;
	}

	/**
	 * Getter for the size param
	 *
	 * @return - the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Calculates the maximum number of nodes in an AVL tree of height h.
	 *
	 * @param h - the height of the tree (a non-negative number) in question.
	 * @return - the maximum number of nodes in an AVL tree of the given height.
	 */
	public static int findMaxNodes(int h) {
		// The minimal number of nodes for a tree with height h is 2^(h+1) - 1, explanation in the readme
		return (int) Math.pow(TWO_BASE, h + ONE_ADDITION) - ONE_DEDUCTION;
	}

	/**
	 * Find a node with the needed value, if none exists find the parent of that possible value
	 *
	 * @param searchVal - The value to search
	 * @return the node with the value or the node that should have a son with this value
	 */
	private TreeNode placementFounder(int searchVal, TreeNode node) {
		if (searchVal == node.getValue()) {
			return node;
		} else if (searchVal < node.getValue() && node.getLeftSon() != null) {
			return placementFounder(searchVal, node.getLeftSon());
		} else if (searchVal > node.getValue() && node.getRightSon() != null) {
			return placementFounder(searchVal, node.getRightSon());
		} else {
			return node;
		}
	}

	/**
	 * Remove the node from the tree and fix the notation
	 *
	 * @param node - The node to remove
	 */
	private void removeNode(TreeNode node) {
		TreeNode parentNode = node.getParent();

		if (node.isLeaf()) {
			removeLeaf(node, parentNode);
		} else {
			// Has two sons
			if (node.getLeftSon() != null && node.getRightSon() != null) {
				TreeNode successor = getSuccessor(node);
				node.setValue(successor.getValue());
				removeNode(successor);
			} else {
				removeSingledSonNode(node, parentNode);
			}
		}
		if (parentNode != null) {
			parentNode.updateHeight();
		}
		deletionFixNotation(node.getParent());
	}

	/**
	 * Remove a node which is a leaf, all the is required is to remove it from it's parent.
	 *
	 * @param node       - The node to remove
	 * @param parentNode - The parent of the node
	 */
	private void removeLeaf(TreeNode node, TreeNode parentNode) {
		if (parentNode == null) {
			root = new TreeNode();
		} else if (parentNode.getLeftSon() == node) {
			parentNode.setLeftSon(null);
		} else {
			parentNode.setRightSon(null);
		}
	}

	/**
	 * Remove a node with a single son. Change the notation of the tree.
	 *
	 * @param node       - The node to remove
	 * @param parentNode - The parent of the node
	 */
	private void removeSingledSonNode(TreeNode node, TreeNode parentNode) {
		TreeNode sonNode = node.getLeftSon() != null ? node.getLeftSon() : node.getRightSon();
		sonNode.setParent(node.getParent());
		if (parentNode == null) {
			root = sonNode;
		} else if (parentNode.getLeftSon() == node) {
			parentNode.setLeftSon(sonNode);
		} else {
			parentNode.setRightSon(sonNode);
		}
	}

	/**
	 * Get tne successor node for the node
	 *
	 * @param node - The node to find a successor to
	 * @return the successor
	 */
	private TreeNode getSuccessor(TreeNode node) {
		TreeNode successor;
		// If the node has a right son we need to find the min node in this subtree
		if (node.getRightSon() != null) {
			successor = getSubTreeMinNode(node.getRightSon());
		}
		// If the node doesn't have a right son, the successor will be the first parent which contains the
		// current node in the subtree of his left son.
		else {
			successor = findSuccessorInParent(node);
		}
		return successor;
	}

	/**
	 * Find a successor for a node by looking to the parents, the successor will be a node which
	 *
	 * @param node - The node to find a successor to
	 * @return the successor
	 */
	private TreeNode findSuccessorInParent(TreeNode node) {
		TreeNode nodeParent = node.getParent();
		if (nodeParent == null || nodeParent.getLeftSon() == node) {
			return nodeParent;
		} else {
			return findSuccessorInParent(nodeParent);
		}
	}

	/**
	 * Get the min node in a subtree of node by going left until no more left sons
	 *
	 * @param node - The subtree root o find it's min node
	 * @return The min node in the subtree
	 */
	private TreeNode getSubTreeMinNode(TreeNode node) {
		if (node.getLeftSon() != null) {
			return getSubTreeMinNode(node.getLeftSon());
		}
		return node;
	}

	/**
	 * Fix the notation of the tree by the defined tree type protocol.
	 * A BST has no implementation of this, but classes which extend this data structure might use this to
	 * keep a specific notation
	 * @param node - the node that was added
	 */
	void additionFixNotation(TreeNode node){
	}

	/**
	 * Fix the notation of the tree by the defined tree type protocol.
	 * A BST has no implementation of this, but classes which extend this data structure might use this to
	 * keep a specific notation
	 * @param node - the parent of the node that was deleted
	 */
	void deletionFixNotation(TreeNode node){
	}
}
