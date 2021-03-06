public class ArrayQueue {
	private int head, size;
	private Object[] elements = new Object[10];
	
	// I: (size >= 0) && (0 <= head < elements.length)
	
	// (element != null) && (существует экземпляр класса)
	public void enqueue(Object element) {
		assert element != null;
		
		if (size == elements.length) {
			ensureCapacity();
		}
		elements[(head + size) % elements.length] = element;
		size++;	
	}
	// if (size + 1 == elements.length) { (size' = size + 1) && (head' = 0) (elements.length' = 2 * elements.length) && 
	// && (elements[(head + size) % elements.length] = element) && (Порядок элементов = const)
	// else (size' = size + 1) && (head' = head) && (elements[(head + size) % elements.length] = element) && (Порядок элементов = const)
	
	// (size == elements.length) && (существует экземпляр класса)
	private void ensureCapacity() {
		Object[] newElements = copy(2 * elements.length);
		head = 0;
        elements = newElements;
    }
	// (size' = size) && (head' = 0) && (elements.length' = 2 * elements.length) && (Порядок элементов = const)
	
	// (size > 0) && (существует экземпляр класса)
	public Object element() {
		assert size > 0;
		
		return elements[head];
	}
	// (R = elements[head]) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
 
	// (size > 0) && (существует экземпляр класса)
	public Object dequeue() {
		assert size > 0;
		
		Object ans = element();
		size--;
		elements[head] = null;
		head = (head + 1) % elements.length;
		return ans;
	}
	// (R = elements[head]) && (head' = (head + 1) % elements.length) && (size' = size - 1) && (Порядок элементов = const)
	
	// (существует экземпляр класса)
	public int size() {
		return size;
	}
	// (R = size) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1) 
	
	// (существует экземпляр класса)
	public boolean isEmpty() {
		return size == 0;
	}
	// (R = size == 0) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
	
	// (существует экземпляр класса)
	public void clear() {
		size = 0;
		head = 0;
		elements = new Object[10];
	}
	// (size' = 0) && (head' = 0) && (elements' = new Object[10])
	
	// (существует экземпляр класса)
	public Object[] toArray() {
	    return copy(size);
	}
	// (R = elements) && (size' = size) && (head' = head) && (elements' = elements)
	
	// (arraySize >= 0)
	private Object[] copy(int arraySize) {
		Object[] result = new Object[arraySize];
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
    // (R = elements) && (size' = size) && (head' = head) && (elements' = elements)
	
	// (element != null) && (существует экземпляр класса)
	public void push(Object element) {
		assert element != null;
		
		if (size == elements.length) {
			ensureCapacity();
		}
		if (head == 0) {
		    head = elements.length - 1;
		} else {
			head--;
		}
		elements[head] = element;
		size++;
	}
	// if (size + 1 == elements.length) (size' = size + 1) && (head' = 0) (elements.length' = 2 * elements.length) && 
	// && (elements[0] = element) && (Порядок элементов = const) 
	// else (size' = size + 1) && (if (head == 0) head' = elements.length - 1; elements[elements.length - 1] = element else head' = head - 1; elements[head] = element)
	
	// (size > 0) && (существует экземпляр класса)
	public Object peek() {
		assert size > 0;
		
		if ((head + size) % elements.length > 0) {
			return elements[(head + size) % elements.length - 1];
		} else {
			return elements[elements.length - 1];
		}
	}
	// if ((head + size) % elements.length > 0) R = elements[(head + size) % elements.length - 1] else R = elements[elements.length - 1];
	// (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
	
	// (size > 0) && (существует экземпляр класса)
	public Object remove() {
		assert size > 0;
		
		Object ans;
		if ((head + size) % elements.length > 0) { 
		    ans = peek();
			elements[(head + size) % elements.length - 1] = null;
		} else {
			ans = peek();
			elements[elements.length - 1] = null;
		}
		size--;
		return ans;
	}
	// if ((head + size) % elements.length > 0) R = elements[(head + size) % elements.length - 1] else R = elements[elements.length - 1];
	// (size' = size - 1) && (head' = head) && (Порядок элементов = const)
}