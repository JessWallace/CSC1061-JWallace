package edu.frcc.csc1061jsp25.MyTreeMap;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MyTreeMap<K, V> implements Map<K, V>, Iterable<V> {
	private Node root = null;
	private int size = 0;

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
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
			return null;
		}

		Node parent = null;
		Node current = root;
		Comparable<K> k = (Comparable<K>) key;
		while (current != null) {
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
		size++;
		return null;
	}

	@Override
	public V remove(Object key) {
		Comparable<K> k = (Comparable<K>) key;
		Deque<Node> stack = new ArrayDeque<>(1);
		root = remove(root, k, stack);
		if (!stack.isEmpty()) {
			size--;
			return stack.peek().value;
		} else {
			return null;
		}
	}

	public Node remove(Node node, Comparable<K> k, Deque<Node> stack) {
		if (node == null) {
			return null;
		} else if (k.compareTo(node.key) < 0) {
			node.left = remove(node.left, k, stack);
		} else if (k.compareTo(node.key) > 0) {
			node.right = remove(node.right, k, stack);
		} else {
			stack.offer(node);

			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;

			Node successor = findMin(node.right);

			K origKey = node.key;
			node.key = successor.key;
			V origVal = node.value;
			node.value = successor.value;

			node.right = remove(node.right, (Comparable<K>) successor.key, new ArrayDeque<>(1));

			stack.clear();
			stack.offer(new Node(origKey, origVal));
		}
		return node;
	}

	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
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
	public Iterator<V> iterator() {

		return new RecursiveIterator();
	}
//	private class NonRecursiveIterator implements Iterator<V>{
//		private Deque<Node> stack = new ArrayDeque();
//		
//		public NonRecrusiveIterator() {
//			
//		}
//	}

	private class RecursiveIterator implements Iterator<V> {
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
		public V next() {
			return list.remove();
		}
	}
}
