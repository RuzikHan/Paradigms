public class ArrayQueue extends AbstractQueue implements Queue {
	private int head;
	private Object[] elements = new Object[10];
	
	public void enqueueImpl(Object element) {
		if (size == elements.length) {
			ensureCapacity();
		}
		elements[(head + size) % elements.length] = element;
	}

	private void ensureCapacity() {
		int arraySize;
		if (Integer.MAX_VALUE / 2 < size) {
			arraySize = Integer.MAX_VALUE;
		} else {
			arraySize = 2 * elements.length;
		}
		Object[] newElements = new Object[arraySize];
		newElements = toArrayImpl(newElements);
		head = 0;
        elements = newElements;
    }

	public Object elementImpl() {
		return elements[head];
	}

	public void dequeueImpl() {
		elements[head] = null;
		head = (head + 1) % elements.length;
	}

	public void clearImpl() {
		head = 0;
		elements = new Object[10];
	}
	
	public Object[] toArrayImpl(Object[] result) {
		if (size != 0) {
		    if (head < (head + size) % elements.length) {
				System.arraycopy(elements, head, result, 0, size);
			} else {
				System.arraycopy(elements, head, result, 0, elements.length - head);
				System.arraycopy(elements, 0, result, elements.length - head, (head + size) % elements.length);
			}
		}
		return result;
	}
	
	protected ArrayQueue copy() {
    	ArrayQueue buff = new ArrayQueue();
    	for (int i = 0; i < size; i++) {
    		Object res = dequeue();
    		buff.enqueue(res);
    		enqueue(res);
    	}
    	return buff;
    } 
}