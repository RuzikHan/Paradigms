public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 100; i++) {
            ArrayQueueModule.push(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                ArrayQueueModule.size() + " " +
                ArrayQueueModule.peek() + " " +
                ArrayQueueModule.remove()
            );
        }
    }

    public static void main(String[] args) {
        fill();
        dump();
    }
}
