import java.util.EmptyStackException;
import java.util.Optional;

/**
 * Question:
 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the
 * minimum element? Push, pop and min should all operate in 0(1) time.
 * <p>
 * Solution:
 * Storing the current min in each node.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var stack = new Stack();
        for (final var element : new int[]{5, 6, 3, 7}) {
            System.out.printf("push(%d): ", element);
            stack.push(element);
            stack.display();
            System.out.println();
        }
    }
}

final class Stack {
    private Optional<StackNode> top = Optional.empty();

    boolean isEmpty() {
        return top.isEmpty();
    }

    /**
     * Removes the object at the top of this stack, and returns that object as the value of this function.
     *
     * @throws EmptyStackException – if this stack is empty.
     */
    int pop() {
        if (top.isEmpty()) throw new EmptyStackException();
        final var value = top.get();
        top = value.getNext();
        return value.getData();
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    void push(final int data) {
        final var min = top.isEmpty() ? data : Math.min(top.get().getData(), data);
        top = Optional.of(new StackNode(data, min, top));
    }

    /**
     * @return The minimum element in the stack.
     * @throws EmptyStackException - if this stack is empty.
     */
    int getMin() {
        if (top.isEmpty()) throw new EmptyStackException();
        return top.get().getMin();
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException – if this stack is empty.
     */
    int peek() {
        if (top.isEmpty()) throw new EmptyStackException();
        return top.get().getData();
    }

    void display() {
        System.out.print("[");
        for (var node = top; node.isPresent(); node = node.get().getNext()) {
            final var value = node.get();
            System.out.printf("{data: %d, min: %d}", value.getData(), value.getMin());
            if (value.getNext().isPresent()) System.out.print(", ");
        }
        System.out.print("]");
    }
}

/**
 * A node in a stack that keeps track of the minimum element upto it. For example, a stack using these nodes might look
 * like {data: 2, min: 2} -> {data: 3, min: 2} -> {data: 6, min: 2} -> {data: 1, min: 1} -> {data: 7, min: 1}.
 */
final class StackNode {
    private final int data;
    private final int min;
    private Optional<StackNode> next;

    StackNode(final int data, final int min, final Optional<StackNode> next) {
        this.data = data;
        this.min = min;
        this.next = next;
    }

    StackNode(final int data, final int min) {
        this(data, min, Optional.empty());
    }

    int getData() {
        return data;
    }

    int getMin() {
        return min;
    }

    Optional<StackNode> getNext() {
        return next;
    }

    void setNext(final StackNode next) {
        this.next = Optional.of(next);
    }
}
