package oop.ex4.data_structures;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BinaryTreeTest {
	@Test
	public void delete() throws Exception {
		Integer[] basicArray = {3, 4, 5, 5, 5, 2, 6, 1, 1};
		BinaryTree tree = getTree(basicArray);

		tree.delete(1);
		tree.delete(4);


		Integer[] basicArray2 = {10, 8, 17, 4, 1, 15, 22, 9};
		tree = getTree(basicArray2);
		tree.delete(4);
		tree.delete(8);
		tree.delete(17);
		tree.delete(10);
		tree.delete(9);
		tree.delete(22);
		tree.delete(15);
		tree.delete(1);
	}

	@Test
	public void checkForNullSuccessor() throws Exception {
		Integer[] basicArray = {1, 2, 3};
		BinaryTree tree = getTree(basicArray);
		tree.delete(3);
	}


	private BinaryTree getTree(Integer[] array) {
		BinaryTree tree = new BinaryTree();
		for (Integer i : array) {
			tree.add(i);
		}
		return tree;
	}

	@Test
	public void add() throws Exception {
		Integer[] basicArray = {3, 4, 5, 5, 5, 2, 6, 1, 1};
		BinaryTree tree = getTree(basicArray);
		assertEquals(tree.size(), 6);
		assertEquals(0, tree.contains(3));
		assertEquals(1, tree.contains(2));
		assertEquals(3, tree.contains(6));
	}

	@Test
	public void findMaxNodes() throws Exception {
		assertEquals(1, BinaryTree.findMaxNodes(0));
		assertEquals(3, BinaryTree.findMaxNodes(1));
		assertEquals(15, BinaryTree.findMaxNodes(3));
	}

	@Test
	public void IteratorTest() throws Exception {
		Integer[] basicArray = {3, 4, 5, 5, 5, 2, 6, 1, 1};
		BinaryTree tree = getTree(basicArray);

		Iterator<Integer> iterator = tree.iterator();
		Integer i = 0;
		while (iterator.hasNext()) {
			i++;
			Integer value = iterator.next();
			assertEquals(i, value);
			System.out.println(value);
		}
	}

	@Test
	public void testDataConstructor() throws Exception {
		Integer[] basicArray = {3, 4, 5, 2, 6, 1};
		int[] basicArray2 = {3, 4, 5, 2, 6, 1};
		BinaryTree tree = getTree(basicArray);
		BinaryTree tree2 = new BinaryTree(basicArray2);

		Iterator<Integer> treeIterator = tree.iterator();
		Iterator<Integer> tree2Iterator = tree2.iterator();

		for (int i = 0; i < basicArray.length; i++) {
			assertEquals(treeIterator.next(), tree2Iterator.next());
		}
	}

	@Test
	public void testTreeConstructor() throws Exception {
		Integer[] basicArray = {3, 4, 5, 2, 6, 1};
		BinaryTree tree = getTree(basicArray);
		BinaryTree tree2 = new BinaryTree(tree);

		Iterator<Integer> treeIterator = tree.iterator();
		Iterator<Integer> tree2Iterator = tree2.iterator();

		for (int i = 0; i < basicArray.length; i++) {
			Integer value1 = treeIterator.next();
			Integer value2 = tree2Iterator.next();
			if (value1 != null) {
				assertEquals(value1, value2);
			} else {
				assertNull(value2);
			}
		}
	}

	@Test
	public void testDataConstructorNull() throws Exception {
		{
			int[] basicArray = null;
			BinaryTree tree = new BinaryTree(basicArray);
			tree.add(3);
			tree.add(3);
		}
	}

	@Test
	public void testTreeConstructorNull() throws Exception {
		BinaryTree tree_2 = null;
		BinaryTree tree = new BinaryTree(tree_2);
		tree.add(2);
		tree.add(1);
	}

	/*@Test
	public void testTreeCheckIterWithoutHasNext() throws Exception {
		Integer[] basicArray = {3, 4, 5, 5, 5, 2, 6, 1, 1};
		BinaryTree tree = getTree(basicArray);

		Iterator<Integer> iterator = tree.iterator();
		while (true) {
			Integer value = iterator.next();
			}
	}*/

}