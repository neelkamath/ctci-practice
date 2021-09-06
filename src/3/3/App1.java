import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Optional;

/**
 * Question:
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real
 * life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure
 * {@code SetOfStacks} that mimics this. {@code SetOfStacks} should be composed of several stacks and should create a
 * new stack once the previous one exceeds capacity. {@code SetOfStacks.push()} and {@code SetOfStacks.pop()} should
 * behave identically to a single stack (that is, {@code pop()} should return the same values as it would if there were
 * just a single stack).
 * FOLLOW UP
 * Implement a function {@code popAt(int index)} which performs a pop operation on a specific sub-stack.
 * <p>
 * Solution:
 * Without {@code popAt(int index}.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var set = new SetOfStacks<Integer>(3);
        System.out.println("***NEW SET***");
        System.out.printf("isEmpty(): %b\n", set.isEmpty());
        final var numElements = 10;
        System.out.println("***PUSHING ELEMENTS***");
        for (var index = 0; index < numElements; ++index) {
            System.out.printf("push(%d)\n", index);
            set.push(index);
            System.out.printf("peek(): %d\nisEmpty(): %b\n", set.peek(), set.isEmpty());
            set.display();
            System.out.println();
        }
        System.out.println("***POPPING ELEMENTS***");
        for (var index = 0; index < numElements; ++index) {
            System.out.printf("peek(): %d\npop(): %d\nisEmpty(): %b\n", set.peek(), set.pop(), set.isEmpty());
            set.display();
            System.out.println();
        }
    }
}

final class SetOfStacks<T> {
    private final ArrayList<Stack<T>> list = new ArrayList<>();
    /**
     * The capacity of each stack (minimum {@code 0}).
     */
    private final int capacity;
    /**
     * The index in the {@code list} of the current stack ({@code -1} if no stacks exist).
     */
    private int index = -1;

    /**
     * @param capacity The capacity of each stack.
     * @throws IllegalArgumentException - If the {@code capacity} was less than {@code 0}.
     */
    SetOfStacks(final int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        this.capacity = capacity;
    }

    boolean isEmpty() {
        return index == -1;
    }

    /**
     * Removes the object at the top of this stack, and returns that object as the value of this function.
     *
     * @throws EmptyStackException – if this stack is empty.
     */
    T pop() {
        if (index == -1) throw new EmptyStackException();
        final var stack = list.get(index);
        final var value = stack.pop();
        if (stack.isEmpty()) {
            list.remove(index);
            --index;
        }
        return value;
    }

    /**
     * Pushes an item onto the top of this stack.
     */
    void push(final T data) {
        if (index == -1 || list.get(index).getSize() == capacity) {
            list.add(new Stack<>());
            ++index;
        }
        list.get(index).push(data);
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException – if this stack is empty.
     */
    T peek() {
        if (index == -1) throw new EmptyStackException();
        return list.get(index).peek();
    }

    void display() {
        System.out.print("{ ");
        for (var stackIndex = 0; stackIndex <= index; ++stackIndex) {
            list.get(stackIndex).display();
            if (stackIndex < index) System.out.print(", ");
        }
        System.out.print(" }");
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
     * @throws EmptyStackException – if this stack is empty.
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
        top = Optional.of(new StackNode(data, top));
        ++size;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException – if this stack is empty.
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