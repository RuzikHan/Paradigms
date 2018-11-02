import java.util.function.Predicate;
import java.util.function.Function;

public interface Queue {
    // (element != null) && (size < 2^31 - 1)
	void enqueue(Object element);
	// (last = element) && (size' = size + 1) && (elements' = elements, elements:first to last - 1)
	
	// size > 0
    Object element();
	// (R = first in queue) && (elements' = elements, elements:first to last) && (size' = size)
	
	// size > 0
	Object dequeue();
    // (R = first in queue) && (elements = next.elements: first' = second, second' = third...) && (элемент удаляется из начала очереди) 
	// && (size' = size - 1) && (elements' = elements, elements:second to last)
	
	int size();
	// (R = size) && (elements' = elements, elements:first to last) && (size' = size);
	
    boolean isEmpty();
	// (R = size == 0) && (elements' = elements, elements:first to last) && (size' = size)
	
	void clear();
	// (size = 0) && (elements' = null)
	
	public Object[] toArray();
	// (R = queue) && (elements' = elements, elements:first to last)
	
	Queue filter(Predicate<Object> predicate);
	// (R = filter(array)) && (elements' = elements, elements:first to last) && (size' = size)
	
	Queue map(Function<Object, Object> function);
	// (R = map(array)) && (elements' = elements, elements:first to last) && (size' = size)
}