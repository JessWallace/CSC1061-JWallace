package com.frcc.SI.ArrayListLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T>{
	
	private T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.array = (T[]) new Object[4];
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void doubleArr(int newLength) {
		T[] array = (T[]) new Object [newLength];
		for(int i = 0; i < size; i++) {
			array[i] = this.array[i];
		}
		
		this.array = array;
	}
	
	private boolean isInBounds(int index) {
		return index >= 0 || index < size;
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
	public boolean contains(Object o) {
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		if(size >= array.length) {
			doubleArr(size * 2);
		}
		
		array[size] = e;
		size ++;
		
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int indexOf = indexOf(o);
		if(indexOf < 0) {
			return false;
		}
		
		for(int i = indexOf; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		
		size--;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public T get(int index) {
		if(!isInBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		return array[index];
	}

	@Override
	public T set(int index, T element) {
		if(!isInBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		T old = array[index];
		array[index] = element;
	
		return old;
	}

	@Override
	public void add(int index, T element) {
		if(!isInBounds(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		add(element);
		
		for(int i = size - 1; i > index; i--) {
			array[i] = array[i - 1];
		}
		
		array[index] = element;
	}

	@Override
	public T remove(int index) {
		T element = get(index);
		for(int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		
		size--;
		return element;
	}

	@Override
	public int indexOf(Object o) {
		for(int i = 0; i < size; i++) {
			if(array[i].equals(o)){
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for(int i = size - 1; i >= 0; i--) {
			if(array[i].equals(o)){
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
