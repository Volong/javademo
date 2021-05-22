package github.io.ylongo.dsaa.chapter03;

import com.google.protobuf.Any;
import org.apache.commons.collections.iterators.ArrayListIterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize;
	
	private AnyType[] theItems;
	
	public MyArrayList() {
		doCLear();
	}
	
	public void clear() {
		doCLear();
	}
	
	private void doCLear() {
		theSize = 0;
		
	}
	
	public int size() {
		return theSize;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void trimToSize() {
		ensureCapacity(size());
	}
	
	public AnyType get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return theItems[idx];
	}
	
	public AnyType set(int idx, AnyType newVal) {
		if (idx < 0 || idx >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		AnyType old = theItems[idx];
		theItems[idx] = newVal;
		return old;
	}
	
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < theSize) {
			return;
		}
		
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];

		for (int i = 0; i < size(); i++) {
			theItems[i] = old[i];
		}
	}
	
	public boolean add (AnyType x) {
		add(size(), x);
		return true;
	}
	
	public void add(int idx, AnyType x) {
		if (theItems.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for (int i = theSize; i > idx; i--) {
			theItems[i] = theItems[i -1];
		}
		
		theItems[idx] = x;
		theSize++;
	}
	
	public AnyType remove(int idx) {
		AnyType removedItem = theItems[idx];
		for (int i = idx; i < size() - 1; i++) {
			theItems[i] = theItems[i + 1];
		}
		theSize--;
		return removedItem;
	}
	
	
	@Override
	public Iterator<AnyType> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public void forEach(Consumer<? super AnyType> action) {
		Iterable.super.forEach(action);
	}

	@Override
	public Spliterator<AnyType> spliterator() {
		return Iterable.super.spliterator();
	}
}
