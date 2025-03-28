
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyDoubleLinkedList<T extends Song> implements List<T> {
	private class Node {
		public T data;
		public Node next;
		public Node prev; // Doubly

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null; // Doubly
		}

		@SuppressWarnings("unused")
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
			next.prev = this; // Doubly
		}

		public String toString() {
			return "Node(" + data.toString() + ")";
		}
	}

	private int size; // keeps track of the number of elements
	private Node head; // reference to the first node
	private Node tail; // Doubly reference to the last node

	/**
	 *
	 */
	public MyDoubleLinkedList() {
		head = null;
		tail = null; // Doubly
		size = 0;
	}

	// IMPLEMENT
	@Override
	public boolean add(T element) {
		Node newNode = new Node(element);
		if (head == null) {
			head = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;

//			Node node = head;
//			// loop until the last node
//			for ( ; node.next != null; node = node.next) {}
//			node.next = new Node(element);
		}
		tail = newNode; // Doubly
		size++;
		return true;
	}

	// IMPLEMENT
	@Override
	public void add(int index, T element) {
		Node newNode = new Node(element);
		if (index == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else {
			Node node = getNode(index - 1);
			if (tail == node) {
				tail = newNode;
			}
			newNode.next = node.next;
			newNode.prev = node;
			if (node.next != null) {
				node.next.prev = newNode;
			}
			node.next = newNode;
		}
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean flag = true;
		for (T element : collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj : collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public T get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	/**
	 * Returns the node at the given index.
	 * 
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		// TODO: FILL THIS IN!
		Node node = head;
		for (int i = 0; i < size; i++) {
			if (equals(target, node.data)) {
				return i;
			}
			node = node.next;
		}
		return -1;
	}

	/**
	 * Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (equals(target, node.data)) {
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<T> listIterator() {
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return null;
	}

	// IMPLEMENT
	@Override
	public boolean remove(Object obj) {
		Node node = head;
		for (int i = 0; i < size; i++) {
			if (equals(obj, node.data)) {
				break;
			}
			node = node.next;
		}
		if (node == null) {
			return false;
		}
		if (head == node) {
			if (tail == head) {
				tail = null;
			}
			head = head.next;
			head.prev = null;
		} else {
			if (node == tail) {
				tail = node.prev;
			}
			node.prev.next = node.next;
			if (node.next != null) {
				node.next.prev = node.prev;
			}
		}
		size--;
		return true;
	}

	// IMPLEMENT
	@Override
	public T remove(int index) {
		// TODO: FILL THIS IN!
		T element = get(index);
		if (index == 0) {
			if (tail == head) {
				tail = null;
			}
			head = head.next;
			head.prev = null;
		} else {
			Node node = getNode(index - 1);
			if (node.next == tail) {
				tail = node;
			}
			node.next = node.next.next;
			if (node.next != null) {
				node.next.prev = node;
			}
		}
		size--;
		return element;

		// return null;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj : collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		Node node = getNode(index);
		T old = node.data;
		node.data = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyDoubleLinkedList<T> list = new MyDoubleLinkedList<T>();
		for (Node node = head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add(node.data);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node = head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.data;
			i++;
		}
		return array;
	}
	public int count() {
		return size;
	}

	public void reverse() {
		Node current = head;
		Node temp = null;

		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}

		if (temp != null) {
			head = temp.prev;
			tail = head;
			while (tail != null && tail.next != null) {
				tail = tail.next;
			}
		}
	}

	public void assignIndexes() {
		Node current = head;
		int index = 0;
		while (current != null) {
			current.data.setIndex(index);
			current = current.next;
			index++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}
