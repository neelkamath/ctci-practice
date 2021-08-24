import java.util.HashMap;

/**
 * Question:
 * Remove Dups: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 * <p>
 * Solution:
 * Using a buffer.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var node = new LinkedListNode(
                1,
                new LinkedListNode(
                        2,
                        new LinkedListNode(
                                1,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(
                                                        1,
                                                        new LinkedListNode(
                                                                4,
                                                                new LinkedListNode(
                                                                        5,
                                                                        new LinkedListNode(1, null)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        System.out.print("Before: ");
        node.display();
        System.out.print("\nAfter: ");
        removeDuplicates(node);
        node.display();
        System.out.println();
    }

    private static void removeDuplicates(LinkedListNode node) {
        final var map = new HashMap<Integer, Boolean>();
        while (node != null) {
            map.put(node.getData(), true);
            while (node.getNext() != null) {
                final var data = node.getNext().getData();
                if (map.containsKey(data)) node.setNext(node.getNext().getNext());
                else break;
            }
            node = node.getNext();
        }
    }
}

final class LinkedListNode {
    private final int data;
    private LinkedListNode next;

    LinkedListNode(final int data, final LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    int getData() {
        return data;
    }

    LinkedListNode getNext() {
        return next;
    }

    void setNext(LinkedListNode next) {
        this.next = next;
    }

    void display() {
        for (var node = this; node != null; node = node.getNext()) System.out.printf("%d ", node.getData());
    }
}
