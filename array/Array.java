public class Array<T> {
	
	private T[] data;
	private int size;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private static final int NO_FIND_FLAG = -1;
	
	@SuppressWarnings("unchecked")
	public Array(int capacity) {
		data = (T[]) new Object[capacity];
		size = 0;
	}
	
	public Array() {
		this(DEFAULT_CAPACITY);
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addElement(T elem, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index is error!");
		}
		if (size == data.length) {
			resize(data.length * 2);
		}
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = elem;
		size++;
	}
	
	public void addElementAtLast(T elem) {
		addElement(elem, size);
	}
	
	public void addElementAtFirst(T elem) {
		addElement(elem, 0);
	}
	
	public T removeElement(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index is error!");
		}
		T elem = data[index];
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		data[size] = null;
		if (size == data.length / 4 && data.length / 2 != 0) {
			resize(data.length / 2);
		}
		return elem;
	}
	
	public T removeElementAtFirst() {
		return removeElement(0);
	}
	
	public T removeElementAtLast() {
		return removeElement(size - 1);
	}
	
	public void removeAllElement(T elem) {
		int index = getElementIndexFromFirst(elem);
		while (index != NO_FIND_FLAG) {
			removeElement(index);
			index = getElementIndexFromFirst(elem);
		}
	}
	
	public void setElement(T elem, int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index is error!");
		}
		data[index] = elem;
	}
	
	public T getElement(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index is error!");
		}
		return data[index];
	}
	
	public boolean contains(T elem) {
		if (elem == null) {
			throw new IllegalArgumentException("Element is null!");
		}
		for (int i = 0; i < size; i++) {
			if (elem.equals(data[i])) {
				return true;
			}
		}
		return false;
	}
	
	public int getElementIndexFromFirst(T elem) {
		if (elem == null) {
			throw new IllegalArgumentException("Element is null!");
		}
		for (int i = 0; i < size; i++) {
			if (elem.equals(data[i])) {
				return i;
			}
		}
		return NO_FIND_FLAG;
	}
	
	public int getElementIndexFromLast(T elem) {
		if (elem == null) {
			throw new IllegalArgumentException("Element is null!");
		}
		for (int i = size - 1; i > 0; i--) {
			if (elem.equals(data[i])) {
				return i;
			}
		}
		return NO_FIND_FLAG;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Capacity: " + data.length + "\n");
		sb.append("Size: " + size + "\n");
		sb.append("Array: [");
		for (int i = 0; i < size; i++) {
			sb.append(data[i]);
			if (i != size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		T[] newData = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
	
}