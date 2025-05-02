package edu.frcc.csc1061jsp25.MyAVLTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MyAVLTree<K, V> implements Map<K, V>, Iterable<edu.frcc.csc1061jsp25.MyAVLTree.MyAVLTree.Node> {
	private Node root = null;
	private int size = 0;
	private List<Node> path = new ArrayList<>();

	protected class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		private int height;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			height = 0;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (get(key) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		Node current = root;
		Comparable<K> k = (Comparable<K>) key;

		while (current != null) {
			if (k.compareTo(current.key) < 0) {
				current = current.left;
			} else if (k.compareTo(current.key) > 0) {
				current = current.right;
			} else {
				return current.value;
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (root == null) {
			Node newNode = new Node(key, value);
			root = newNode;
			size++;
			updateHeight(root);
			return null;
		}

		Node parent = null;
		Node current = root;
		path.clear();

		Comparable<K> k = (Comparable<K>) key;
		while (current != null) {
			path.add(current);
			if (k.compareTo(current.key) < 0) {
				parent = current;
				current = current.left;
			} else if (k.compareTo(current.key) > 0) {
				parent = current;
				current = current.right;
			} else {
				V oldVal = current.value;
				current.value = value;
				return oldVal;
			}
		}

		Node newNode = new Node(key, value);
		if (k.compareTo(parent.key) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		updateHeight(newNode);
		balancePath();
		size++;

		return null;
	}

	private void updateHeight(Node node) {
		if (node.left == null && node.right == null) {
			node.height = 0;
		} else if (node.left == null) {
			node.height = node.right.height + 1;
		} else if (node.right == null) {
			node.height = node.left.height + 1;
		} else {
			node.height = Math.max(node.left.height, node.right.height) + 1;
		}
	}

	private int balanceFactor(Node current) {
		int balanceFactor = 0;
		if (current == null) {
			balanceFactor = 0;
		} else if (current.left == null && current.right == null) {
			balanceFactor = 0;
		} else if (current.left == null) {
			balanceFactor = current.right.height + 1;
		} else if (current.right == null) {
			balanceFactor = -(current.left.height + 1);
		} else {
			balanceFactor = current.right.height - current.left.height;
		}
		return balanceFactor;
	}

	private void balancePath() {
		for (int i = path.size() - 1; i >= 0; i--) {
			Node current = path.get(i);
			updateHeight(current);
			Node parent = null;
			if (i > 0) {
				parent = path.get(i - 1);
			}
			switch (balanceFactor(current)) {
			case -2:
				if (balanceFactor(current.left) <= 0) {
					// LL imbalance
					balanceLL(current, parent);
				} else {
					// LR imbalance
					balanceLR(current, parent);
				}
				break;
			case 2:
				if (balanceFactor(current.right) >= 0) {
					// RR imbalance
					balanceRR(current, parent);
				} else {
					// RL imbalance
					balanceRL(current, parent);
				}
				break;
			}
		}
	}

	private void balanceLL(Node node, Node parent) {
		Node ggp = parent;
		Node gp = node;
		Node par = gp.left;
		Node ch = par.left;

		if (gp == root) {
			root = par;
		} else {
			if (ggp.left == gp) {
				ggp.left = par;
			} else {
				ggp.right = par;
			}
		}
		gp.left = par.right;
		par.right = gp;

		updateHeight(gp);
		updateHeight(par);
		updateHeight(ch);
	}

	// Homework
	private void balanceRR(Node gp, Node ggp) {

		Node par = gp.right;
		Node ch = par.right;

		if (gp == root) {
			root = par;
		} else {
			if (ggp.left == gp) {
				ggp.left = par;
			} else {
				ggp.right = par;
			}
		}

		gp.right = par.left;
		par.left = gp;

		updateHeight(gp);
		updateHeight(par);
		updateHeight(ch);
	}

	private void balanceLR(Node gp, Node ggp) {

		Node par = gp.left;
		Node ch = par.right;

		if (gp == root) {
			root = ch;
		} else {
			if (ggp.left == gp) {
				ggp.left = ch;
			} else {
				ggp.right = ch;
			}
		}

		par.right = ch.left;
		gp.left = ch.right;

		ch.left = par;
		ch.right = gp;

		updateHeight(gp);
		updateHeight(par);
		updateHeight(ch);

	}

	// Homework
	private void balanceRL(Node gp, Node ggp) {

		Node par = gp.right;
		Node ch = par.left;

		if (gp == root) {
			root = ch;
		} else {
			if (ggp.left == gp) {
				ggp.left = ch;
			} else {
				ggp.right = ch;
			}
		}

		par.left = ch.right;
		gp.right = ch.left;

		ch.left = gp;
		ch.right = par;

		updateHeight(gp);
		updateHeight(par);
		updateHeight(ch);

	}

	@Override
	public V remove(Object key) {

		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterator() {

		return new NonRecursiveIterator();
	}

	private class NonRecursiveIterator implements Iterator<Node> {
		private Deque<Node> stack = new ArrayDeque<>();

		public NonRecursiveIterator() {
			pushOnStack(root);
		}

		public void pushOnStack(Node node) {
			Node current = node;
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public MyAVLTree<K, V>.Node next() {
			Node node = stack.pop();
			V value = node.value;
			pushOnStack(node.right);
			return node;
		}

	}

	private class RecursiveIterator implements Iterator<Node> {
		private Queue<V> list = new ArrayDeque<>();

		public RecursiveIterator() {
			inorder(root);
		}

		private void preorder(Node node) {
			if (node == null) {
				return;
			}
			list.add(node.value);
			preorder(node.left);
			preorder(node.right);
		}

		private void postorder(Node node) {
			if (node == null) {
				return;
			}
			postorder(node.left);
			postorder(node.right);
			list.add(node.value);
		}

		private void inorder(Node node) {
			if (node == null) {
				return;
			}
			inorder(node.left);
			list.add(node.value);
			inorder(node.right);
		}

		@Override
		public boolean hasNext() {
			return !list.isEmpty();
		}

		@Override
		public Node next() {
			return (MyAVLTree<K, V>.Node) list.remove();
		}
	}

}
