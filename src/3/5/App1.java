import java.util.EmptyStackException;
import java.util.Optional;

/**
 * Question:
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use an additional
 * temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack
 * supports the following operations: {@code push}, {@code pop}, {@code peek}, and {@code isEmpty}.
 * <p>
 * Solution:
 * Sorting during insertion.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var stack = new SortedStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(15);
        stack.push(1);
        stack.push(100);
        stack.display();
        System.out.println();
        while (!stack.isEmpty()) System.out.printf("pop(): %d\n", stack.pop());
        stack.display();
        System.out.println();
    }
}

final class SortedStack {
    private final Stack<Integer> descendingStack = new Stack<>();
    private final Stack<Integer> ascendingStack = new Stack<>();

    boolean isEmpty() {
        return descendingStack.isEmpty() && ascendingStack.isEmpty();
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @throws EmptyStackException – If this stack is empty.
     */
    int peek() {
        ascend();
        if (ascendingStack.isEmpty()) throw new EmptyStackException();
        return ascendingStack.peek();
    }

    /**
     * Removes the object at the top of this stack, and returns that object as the value of this function.
     *
     * @throws EmptyStackException – If this stack is empty.
     */
    int pop() {
        ascend();
        if (ascendingStack.isEmpty()) throw new EmptyStackException();
        return ascendingStack.pop();
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    void push(final int data) {
        ascend();
        while (!ascendingStack.isEmpty() && ascendingStack.peek() < data) descendingStack.push(ascendingStack.pop());
        ascendingStack.push(data);
    }

    /**
     * Moves all elements from the {@code descendingStack} to the {@code ascendingStack}.
     */
    private void ascend() {
        while (!descendingStack.isEmpty()) ascendingStack.push(descendingStack.pop());
    }

    void display() {
        ascend();
        ascendingStack.display();
    }
}

final class Stack<T> {
    private Optional<StackNode<T>> top = Optional.empty();
    private int size = 0;

    boolean isEmpty() {
        return top.isEmpty();
    }

    int getSize() {
        return size;
    }

    /**
     * Removes the object at the top of this stack, and returns that object as the value of this function.
     *
     * @throws EmptyStackException – If this stack is empty.
     */
    T pop() {
        if (top.isEmpty()) throw new EmptyStackException();
        final var value = top.get();
        top = value.getNext();
        --size;
        return value.getData();
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    void push(final T data) {
        top = Optional.of(new StackNode<>(data, top));
        ++size;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @throws EmptyStackException – If this stack is empty.
     */
    T peek() {
        if (top.isEmpty()) throw new EmptyStackException();
        return top.get().getData();
    }

    void display() {
        System.out.print("[ ");
        for (var node = top; node.isPresent(); node = node.get().getNext()) {
            final var value = node.get();
            System.out.printf("%d", value.getData());
            if (value.getNext().isPresent()) System.out.print(" ← ");
        }
        System.out.print(" ]");
    }
}

final class StackNode<T> {
    private final T data;
    private Optional<StackNode<T>> next;

    StackNode(final T data, final Optional<StackNode<T>> next) {
        this.data = data;
        this.next = next;
    }

    StackNode(final T data) {
        this(data, Optional.empty());
    }

    T getData() {
        return data;
    }

    Optional<StackNode<T>> getNext() {
        return next;
    }

    void setNext(final StackNode<T> next) {
        this.next = Optional.of(next);
    }
}
