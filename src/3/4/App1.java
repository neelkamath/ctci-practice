import java.util.EmptyStackException;
import java.util.Optional;

/**
 * Question:
 * Queue via Stacks: Implement a {@code MyQueue} class which implements a queue using two stacks.
 * <p>
 * Solution:
 * Lazy computation.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var queue = new MyQueue<Integer>();
        final var count = 5;
        for (var index = 0; index < count; ++index) {
            System.out.printf("getSize(): %d\n", queue.getSize());
            queue.enqueue(index);
            System.out.printf("enqueue(%d)\npeek(): %d\n", index, queue.peek().get());
            queue.display();
            System.out.println();
        }
        for (var index = 0; index < count; ++index) {
            System.out.printf("dequeue(): %d\n", queue.dequeue().get());
            queue.display();
            System.out.println();
        }
    }
}

final class MyQueue<T> {
    private final Stack<T> stack = new Stack<>();
    private final Stack<T> reversedStack = new Stack<>();

    int getSize() {
        final var size = stack.getSize();
        return size == 0 ? reversedStack.getSize() : size;
    }

    /**
     * @return The first object, or empty if there are no objects in this queue.
     */
    Optional<T> peek() {
        while (!stack.isEmpty()) reversedStack.push(stack.pop());
        return reversedStack.isEmpty() ? Optional.empty() : Optional.of(reversedStack.peek());
    }

    void enqueue(final T data) {
        while (!reversedStack.isEmpty()) stack.push(reversedStack.pop());
        stack.push(data);
    }

    /**
     * Removes the first object in this queue, and returns it. The returned value will be empty if the queue is empty.
     */
    Optional<T> dequeue() {
        while (!stack.isEmpty()) reversedStack.push(stack.pop());
        return reversedStack.isEmpty() ? Optional.empty() : Optional.of(reversedStack.pop());
    }

    void display() {
        while (!stack.isEmpty()) reversedStack.push(stack.pop());
        reversedStack.display();
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
     * @return the object at the top of this stack.
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
