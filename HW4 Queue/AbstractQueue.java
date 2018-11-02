import java.util.function.Predicate;
import java.util.function.Function;

public abstract class AbstractQueue {
	protected int size;
	
	public void enqueue(Object value) {
		assert value != null;
		
		enqueueImpl(value);
		size++;
	}
    
    public Object element() {
		assert size > 0;
		
		return elementImpl();
	}
	
	public Object dequeue() {
		assert size > 0;
		
		Object result = element();
		size--;
		dequeueImpl();
		return result;
	}
	
	public int size() {
        return size;
    }
	
    public boolean isEmpty() {
		return size == 0;
    }
	
	public void clear() {
		size = 0;
		clearImpl();
	}
	
	public Object[] toArray() {
		assert size >= 0;
		
		Object[] result = new Object[size];
		return toArrayImpl(result);
	}
	
	public Queue filter(Predicate<Object> predicate) {
        Queue buff = copy();
        for (int i = 0; i < size; i++) {
            Object element = buff.dequeue(); 
            if (predicate.test(element))
                buff.enqueue(element);
        }
        return buff;    
    }

    public Queue map(Function<Object, Object> function) {
        Queue buff = copy();
        for (int i = 0; i < size; i++) {
            Object element = buff.dequeue(); 
            buff.enqueue(function.apply(element));
        }
        return buff;       
    }
	
	protected abstract void enqueueImpl(Object value);
	protected abstract Object elementImpl();
	protected abstract void dequeueImpl();
	protected abstract void clearImpl();
    protected abstract Object[] toArrayImpl(Object[] result);
	protected abstract Queue copy();
}