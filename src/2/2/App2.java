/**
 * Question:
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 * <p>
 * Solution:
 * Iterating over the list once.
 */
public final class App2 {
    public static void main(final String[] args) {
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(5, null)
                                        )
                                )
                        )
                ),
                5
        );
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(5, null)
                                        )
                                )
                        )
                ),
                4
        );
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(5, null)
                                        )
                                )
                        )
                ),
                3
        );
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(5, null)
                                        )
                                )
                        )
                ),
                2
        );
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(
                                                4,
                                                new LinkedListNode(5, null)
                                        )
                                )
                        )
                ),
                1
        );
    }

    private static void test(final LinkedListNode node, final int k) {
        System.out.print("List: ");
        node.display();
        System.out.printf("\nk: %d\n", k);
        final var result = findLast(node, k);
        System.out.printf("kth last: %d\n", result == null ? null : result.getData());
    }

    private static LinkedListNode findLast(LinkedListNode node, final int k) {
        var runner = node;
        for (var index = 0; index < k; ++index, runner = runner.getNext()) ;
        for (; runner != null; node = node.getNext(), runner = runner.getNext()) ;
        return node;
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
