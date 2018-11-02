public class ArrayQueueADT {
	private /*static*/ int head, size;
	private /*static*/ Object[] elements = new Object[10];
	
	// I: (size >= 0) && (0 <= head < elements.length)
	
	// (element != null) && (queue != null)
	public static void enqueue(ArrayQueueADT queue, Object element) {
		assert (element != null) && (queue != null);
		
		if (queue.size == queue.elements.length) {
			ensureCapacity(queue);
		}
		queue.elements[(queue.head + queue.size) % queue.elements.length] = element;
		queue.size++;
	}
	// if (size + 1 == elements.length) { (size' = size + 1) && (head' = 0) (elements.length' = 2 * elements.length) && 
	// && (elements[(head + size) % elements.length] = element) && (Порядок элементов = const)
	// else (size' = size + 1) && (head' = head) && (elements[(head + size) % elements.length] = element) && (Порядок элементов = const)
	
	// (size == elements.length) && (queue != null)
	private static void ensureCapacity(ArrayQueueADT queue) {
		assert queue != null;
		Object[] newElements = copy(queue, 2 * queue.elements.length);
		queue.head = 0;
        queue.elements = newElements;
    }
	// (size' = size) && (head' = 0) && (elements.length' = 2 * elements.length) && (Порядок элементов = const)
	
	// (size > 0) && (queue != null)
	public static Object element(ArrayQueueADT queue) {
		assert (queue.size > 0) && (queue != null);
		
		return queue.elements[queue.head];
	}
	// (R = elements[head]) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
 
	// (size > 0)
	public static Object dequeue(ArrayQueueADT queue) {
		assert (queue.size > 0) && (queue != null);
		
		Object ans = element(queue);
		queue.size--;
		queue.elements[queue.head] = null;
		queue.head = (queue.head + 1) % queue.elements.length;
		return ans;
	}
	// (R = elements[head]) && (head' = (head + 1) % elements.length) && (size' = size - 1) && (Порядок элементов = const)
	
	// (queue != null)
	public static int size(ArrayQueueADT queue) {
		assert queue != null;
		return queue.size;
	}
	// (R = size) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)  
	
	// (queue != null)
	public static boolean isEmpty(ArrayQueueADT queue) {
		assert queue != null;
		return queue.size == 0;
	}
	// (R = size == 0) && (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
	
	// (queue != null)
	public static void clear(ArrayQueueADT queue) {
		assert queue != null;
		queue.size = 0;
		queue.head = 0;
		queue.elements = new Object[10];
	}
	// (size' = 0) && (head' = 0) && (elements' = new Object[10])
	
	// (queue != null)
	public static Object[] toArray(ArrayQueueADT queue) {
	    return copy(queue, queue.size);
	}
	// (R = elements) && (size' = size) && (head' = head) && (elements' = elements)
	
	// (arraySize >= 0) && (queue != null)
	private static Object[] copy(ArrayQueueADT queue, int arraySize) {
		Object[] result = new Object[arraySize];
		if (queue.size != 0) {
		    if (queue.head < (queue.head + queue.size) % queue.elements.length) {
				System.arraycopy(queue.elements, queue.head, result, 0, queue.size);
			} else { 
				System.arraycopy(queue.elements, queue.head, result, 0, queue.elements.length - queue.head);
				System.arraycopy(queue.elements, 0, result, queue.elements.length - queue.head, (queue.head + queue.size) % queue.elements.length);
			}
		}
	    return result;
	}
    // (R = elements) && (size' = size) && (head' = head) && (elements' = elements)
	
	// (element != null) && (queue != null)
	public static void push(ArrayQueueADT queue, Object element) {
		assert (element != null) && (queue != null);;
		
		if (queue.size == queue.elements.length) {
			ensureCapacity(queue);
		}
		if (queue.head == 0) {
		    queue.head = queue.elements.length - 1;
		} else {
			queue.head--;
		}
		queue.elements[queue.head] = element;
		queue.size++;
	}
	// if (size + 1 == elements.length) (size' = size + 1) && (head' = 0) (elements.length' = 2 * elements.length) && 
	// && (elements[0] = element) && (Порядок элементов = const) 
	// else (size' = size + 1) && (if (head == 0) head' = elements.length - 1; elements[elements.length - 1] = element else head' = head - 1; elements[head] = element)
	
	// (size > 0) && (queue != null)
	public static Object peek(ArrayQueueADT queue) {
		assert (queue.size > 0) && (queue != null);
		
		if ((queue.head + queue.size) % queue.elements.length > 0) {
			return queue.elements[(queue.head + queue.size) % queue.elements.length - 1];
		} else {
			return queue.elements[queue.elements.length - 1];
		}
	}
	// if ((head + size) % elements.length > 0) R = elements[(head + size) % elements.length - 1] else R = elements[elements.length - 1];
	// (size' = size) && (head' = head) && (elements[i]' = elements[i], i:head..(head + size) % elements.length - 1)
	
	// (size > 0) && (queue != null)
	public static Object remove(ArrayQueueADT queue) {
		assert (queue.size > 0) && (queue != null);
		
		Object ans;
		if ((queue.head + queue.size) % queue.elements.length > 0) { 
		    ans = peek(queue);
			queue.elements[(queue.head + queue.size) % queue.elements.length - 1] = null;
		} else {
			ans = peek(queue);
			queue.elements[queue.elements.length - 1] = null;
		}
		queue.size--;
		return ans;
	}
	// if ((head + size) % elements.length > 0) R = elements[(head + size) % elements.length - 1] else R = elements[elements.length - 1];
	// (size' = size - 1) && (head' = head) && (Порядок элементов = const)
}