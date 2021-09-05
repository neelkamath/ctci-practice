import java.util.EmptyStackException;

/**
 * Question:
 * Three in One: Describe how you could use a single array to implement three stacks.
 * <p>
 * Solution:
 * Using fixed size stacks.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var numStacks = 3;
        final var capacity = 3;
        final var array = new ArrayOfStacks<Integer>(numStacks, capacity);
        System.out.print("peek(1): ");
        try {
            System.out.printf("%d\n", array.peek(1));
        } catch (EmptyStackException exception) {
            System.out.println("EmptyStackException thrown");
        }
        try {
            for (var index = 0; index < 3; ++index) {
                System.out.printf("push(1, %d)\n", index);
                array.push(1, index);
                System.out.print("Stack 2: ");
                array.display(1);
                System.out.println();
            }
        } catch (FullStackException exception) {
            System.out.println("FullStackException thrown");
        }
        System.out.printf("pop(1): %d\n", array.pop(1));
        try {
            System.out.println("push(1, 2)");
            array.push(1, 2);
            System.out.print("Stack 2: ");
            array.display(1);
            System.out.println();
        } catch (FullStackException exception) {
            System.out.println("FullStackException thrown");
        }
        System.out.printf("peek(): %d\n", array.peek(1));
    }
}

final class ArrayOfStacks<T> {
    /**
     * Elements are stored left-to-right. Let's consider an example. There are three stacks each with a capacity of
     * three elements. The first stack has the elements {@code 1} and {@code 2}. The second stack has the elements
     * {@code 10}, {@code 20}, and {@code 30}. The third stack is empty. This array would look like
     * {@code [1, 2, null, 10, 20, 30, null, null, null]}.
     */
    private final T[] array;
    /**
     * The number of elements in the stack at the specified index. For example, the number of elements in the second
     * stack would be at index 1.
     */
    private final int[] stackSizes;
    private final int numStacks;
    private final int capacity;

    /**
     * Creates a number of stacks each having the same capactity.
     *
     * @param numStacks Number of stacks to create.
     * @param capacity  Capacity of each stack.
     * @throws IllegalArgumentException - If the {@code numStacks} or {@code capacity} is less than {@code 0}.
     */
    ArrayOfStacks(final int numStacks, final int capacity) {
        if (numStacks < 0 || capacity < 0) throw new IllegalArgumentException();
        this.array = (T[]) new Object[numStacks * capacity];
        stackSizes = new int[numStacks];
        this.numStacks = numStacks;
        this.capacity = capacity;
    }

    /**
     * @param stackIndex Zero-based stackIndex indicating the stack to check.
     * @return Whether the specified stack is empty.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    boolean isEmpty(final int stackIndex) {
        validateIndex(stackIndex);
        return stackSizes[stackIndex] == 0;
    }

    /**
     * Removes the object at the top of the specified stack, and returns that object as the value of this function.
     *
     * @param stackIndex - The zero-based stackIndex of the stack being referred to.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     * @throws EmptyStackException            – If the specified stack is empty.
     */
    T pop(final int stackIndex) {
        validateIndex(stackIndex);
        final var index = getTopIndex(stackIndex);
        if (index == -1) throw new EmptyStackException();
        final var value = array[index];
        array[index] = null;
        --stackSizes[stackIndex];
        return value;
    }

    /**
     * @param stackIndex - The zero-based stackIndex of the stack being referred to.
     * @return The index of the top-most element in the specified stack, or {@code -1} if the stack is empty.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    private int getTopIndex(final int stackIndex) {
        return stackSizes[stackIndex] == 0 ? -1 : (stackIndex * capacity) + stackSizes[stackIndex] - 1;
    }

    /**
     * Pushes an item onto the top of the specified stack.
     *
     * @param stackIndex - The zero-based stackIndex of the specified stack.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    void push(final int stackIndex, final T data) throws FullStackException {
        validateIndex(stackIndex);
        if (isFull(stackIndex)) throw new FullStackException();
        final var index = getTopIndex(stackIndex);
        array[index == -1 ? stackIndex * capacity : index + 1] = data;
        ++stackSizes[stackIndex];
    }

    /**
     * @param stackIndex - The zero-based stackIndex of the specified stack.
     * @return Whether the specified stack is full.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    private boolean isFull(final int stackIndex) {
        validateIndex(stackIndex);
        return stackSizes[stackIndex] == capacity;
    }

    /**
     * @param stackIndex The zero-based index of the stack being referred to.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    private void validateIndex(final int stackIndex) {
        if (stackIndex < 0 || stackIndex >= numStacks) throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Looks at the object at the top of the specified stack without removing it.
     *
     * @param stackIndex The zero-based index of the stack being referred to.
     * @return the object at the top of this stack.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     * @throws EmptyStackException            – if this stack is empty.
     */
    T peek(final int stackIndex) {
        validateIndex(stackIndex);
        final var index = getTopIndex(stackIndex);
        if (index == -1) throw new EmptyStackException();
        return array[index];
    }

    /**
     * Prints the specified stack to stdout.
     *
     * @param stackIndex The zero-based index of the stack being referred to.
     * @throws ArrayIndexOutOfBoundsException - If the {@code stackIndex} is invalid.
     */
    void display(final int stackIndex) {
        for (var index = stackIndex * capacity; index < (stackIndex + 1) * capacity; ++index)
            System.out.printf("%s ", array[index]);
    }
}

/**
 * An element was attempted to be pushed onto a stack in the {@link ArrayOfStacks} but the specified stack was full.
 */
final class FullStackException extends Exception {
}
