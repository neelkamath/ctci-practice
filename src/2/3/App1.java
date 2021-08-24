/**
 * Question:
 * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but the first and last
 * node, not necessarily the exact middle) of a singly linked list, given only access to that node.
 * EXAMPLE
 * Input: the node c from the linked list a->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 * <p>
 * Solution:
 */
public final class App1 {
    public static void main(final String[] args) {
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(3, null)
                        )
                ),
                1
        );
        test(
                new LinkedListNode(
                        1,
                        new LinkedListNode(
                                2,
                                new LinkedListNode(
                                        3,
                                        new LinkedListNode(4, null)
                                )
                        )
                ),
                1
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
    }

    private static void test(final LinkedListNode node, final int skip) {
        System.out.print("Before: ");
        node.display();
        System.out.print("\nAfter: ");
        var middle = node;
        for (var index = 0; index < skip; ++index, middle = middle.getNext()) ;
        deleteMiddle(middle);
        node.display();
        System.out.println();
    }

    private static void deleteMiddle(final LinkedListNode node) {
        node.setData(node.getNext().getData());
        node.setNext(node.getNext().getNext());
    }
}

final class LinkedListNode {
    private int data;
    private LinkedListNode next;

    LinkedListNode(final int data, final LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    int getData() {
        return data;
    }

    void setData(int data) {
        this.data = data;
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
