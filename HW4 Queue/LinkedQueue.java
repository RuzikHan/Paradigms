public class LinkedQueue extends AbstractQueue implements Queue {
    private Node head, tail;
	
    public LinkedQueue() {
        head = new Node(null, null);
        tail = head;
        size = 0;
    }
	
    public void enqueueImpl(Object element) {
		tail.value = element;
        Node tmp = new Node(null, null);
        tail.next = tmp;
        tail = tmp;
    }
	
	public Object elementImpl() {
		return head.value;
    }
	
    public void dequeueImpl() {
        head = head.next;
    }
	
    public void clearImpl() {
        head = new Node(null, null);
        tail = head;
    }	
	
	public Object[] toArrayImpl(Object[] result) {
        Node tmp = head;
        int i = 0;
        while (tmp.value != null && tmp.next != null) {
            result[i] = tmp.value;
            i++;
            tmp = tmp.next;
        }
        return result;
    }
	
	protected LinkedQueue copy() {
    	LinkedQueue buff = new LinkedQueue();
    	for (int i = 0; i < size; i++) {
    		Object res = dequeue();
    		buff.enqueue(res);
    		enqueue(res);
    	}
    	return buff;
    }
			
}