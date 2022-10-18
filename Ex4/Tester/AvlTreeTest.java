package oop.ex4.data_structures;

import org.junit.Test;
import sun.security.x509.AVA;

import java.util.Iterator;

import static org.junit.Assert.*;

public class AvlTreeTest {
	@Test
	public void findMinNodes() throws Exception {
		assertEquals(1, AvlTree.findMinNodes(0));
		assertEquals(2, AvlTree.findMinNodes(1));
		assertEquals(4, AvlTree.findMinNodes(2));
		assertEquals(7, AvlTree.findMinNodes(3));
		assertEquals(12, AvlTree.findMinNodes(4));
		assertEquals(33, AvlTree.findMinNodes(6));
		assertEquals(54, AvlTree.findMinNodes(7));
	}


	@Test
	public void findMaxNodes() throws Exception {
		assertEquals(1, AvlTree.findMaxNodes(0));
		assertEquals(3, AvlTree.findMaxNodes(1));
		assertEquals(15, AvlTree.findMaxNodes(3));
	}

/*	@Test
	public void rotateLeft() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91};
		AvlTree tree = new AvlTree(basicArray);
		tree.leftRotate(tree.getRoot().getLeftSon());

	}*/

/*	@Test
	public void rotateLeftTwo() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91, 12};
		AvlTree tree = new AvlTree(basicArray);
		tree.leftRotate(tree.getRoot().getLeftSon().getLeftSon());

	}*/

/*	@Test
	public void rotateRightTwo() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91, 12, 100, 70};
		AvlTree tree = new AvlTree(basicArray);
		tree.rightRotate(tree.getRoot().getRightSon().getRightSon());

	}*/
	@Test
	public void checkTree() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91, 15, 92, 70,18,19,21,22,23,16,17,12,13,14, 93,94,95,96,54,
				97,98,99,71,72,73,74};
		AvlTree tree = new AvlTree(basicArray);
	}

	@Test
	public void checkRemoveIterator() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91, 15, 92, 70,18,19,21,22,23,16,17,12,13,14, 93,94,95,96,54,
				97,98,99,71,72,73,74};
		AvlTree tree = new AvlTree(basicArray);
		Iterator<Integer> treeIterator = tree.iterator();
		treeIterator.remove();
	}

	@Test
	public void checkRemove() throws Exception{
		int[] basicArray = {41,20,29,65,11,50,91, 15, 92, 70,18,19,21,22,23,16,17,12,13,14, 93,94,95,96,54,
				97,98,99,71,72,73,74};
		AvlTree tree = new AvlTree(basicArray);
		tree.delete(65);
		tree.delete(70);
		tree.delete(71);
		tree.delete(72);
		tree.delete(73);
		tree.delete(74);
		tree.delete(91);
		tree.delete(99);
		tree.delete(11);
		tree.delete(15);
		tree.delete(22);
		tree.delete(95);
		tree.delete(50);
		tree.delete(13);

	}

	@Test
	public void checkRemoveTwo() throws Exception{
		int[] basicArray = {92,19,97,14,21,93,98,94,12,16,20,23};
		AvlTree tree = new AvlTree(basicArray);
		tree.delete(97);
		tree.delete(94);
		tree.delete(98);
		tree.delete(16);
		tree.delete(14);
		tree.delete(21);
		tree.delete(93);
		tree.delete(23);
		tree.delete(19);
		tree.delete(92);
		tree.delete(20);
		tree.delete(12);
		tree.add(12);
	}
}