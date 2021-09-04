import java.util.HashSet;

/**
 * Question:
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the
 * loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make
 * a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 * <p>
 * Solution:
 * Iterating over the list, and comparing each node to a copy stored in a {@link HashSet}.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var list = new LinkedList<Character>();
        list.append('A');
        list.append('B');
        final var loop = list.append('C');
        list.append('D');
        list.append('E');
        list.getTail().setNext(loop);
        test(list);
    }

    private static <T> void test(final LinkedList<T> list) {
        System.out.print("Input: ");
        list.display();
        final var loop = findLoop(list);
        System.out.printf("\nOutput: %s\n", loop == null ? null : loop.getData());
    }

    private static <T> LinkedList.Node<T> findLoop(final LinkedList<T> list) {
        final var set = new HashSet<LinkedList.Node<T>>();
        for (LinkedList.Node<T> node = list.getHead(); node != null; node = node.getNext()) {
            if (set.contains(node)) return node;
            set.add(node);
        }
        return null;
    }
}

final class LinkedList<T> {
    private Node<T> head = null, tail = null;

    Node<T> getHead() {
        return head;
    }

    Node<T> getTail() {
        return tail;
    }

    /**
     * Computes the length of the list in O(n) time, and O(n) space, where "n" is the size of the list (excluding
     * loops).
     *
     * @return the list's length (-1 if it contains a loop).
     */
    int getLength() {
        var length = 0;
        var set = new HashSet<Node<T>>();
        for (var node = getHead(); node != null; node = node.getNext(), ++length) {
            if (set.contains(node)) return -1;
            set.add(node);
        }
        return length;
    }

    /**
     * @return the head node.
     */
    Node<T> prepend(final T data) {
        if (head == null) head = tail = new Node<>(data, null);
        else if (head == tail) head = new Node<>(data, tail);
        else head = new Node<>(data, head);
        return head;
    }

    /**
     * @return the tail node.
     */
    Node<T> append(final T data) {
        final var node = new Node<>(data, null);
        if (head == null) head = node;
        else tail.setNext(node);
        tail = node;
        return tail;
    }

    void display() {
        if (head == null) {
            System.out.print("null");
            return;
        }
        final var set = new HashSet<Node<T>>();
        for (var node = getHead(); ; node = node.getNext()) {
            if (set.contains(node)) {
                System.out.printf("[loops back to %s]", node.getData());
                return;
            }
            set.add(node);
            System.out.printf("%s ", node.getData());
        }
    }

    static final class Node<T> {
        private T data;
        private Node<T> next;

        Node(final T data, final Node<T> next) {
            this.data = data;
            this.next = next;
        }

        T getData() {
            return data;
        }

        void setData(final T data) {
            this.data = data;
        }

        Node<T> getNext() {
            return next;
        }

        void setNext(final Node<T> next) {
            this.next = next;
        }
    }
}
