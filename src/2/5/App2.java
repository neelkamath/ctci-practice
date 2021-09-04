/**
 * Question:
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits are
 * stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two
 * numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 * <p>
 * Solution:
 * Supposing the integers are in forward order.
 */
public final class App2 {
    private static LinkedListNode head = null;

    public static void main(final String[] args) {
        {
            final var node1 = new LinkedListNode(
                    7,
                    new LinkedListNode(
                            1,
                            new LinkedListNode(6, null)
                    )
            );
            final var node2 = new LinkedListNode(
                    5,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(2, null)
                    )
            );
            test(node1, node2);
        }
        {
            final var node1 = new LinkedListNode(
                    7,
                    new LinkedListNode(1, null)
            );
            final var node2 = new LinkedListNode(
                    5,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(2, null)
                    )
            );
            test(node1, node2);
        }
        {
            final var node1 = new LinkedListNode(
                    7,
                    new LinkedListNode(
                            1,
                            new LinkedListNode(6, null)
                    )
            );
            final var node2 = new LinkedListNode(
                    5,
                    new LinkedListNode(9, null)
            );
            test(node1, node2);
        }
        {
            final var node1 = new LinkedListNode(7, null);
            final var node2 = new LinkedListNode(
                    5,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(2, null)
                    )
            );
            test(node1, node2);
        }
        {
            final var node1 = new LinkedListNode(
                    7,
                    new LinkedListNode(
                            1,
                            new LinkedListNode(6, null)
                    )
            );
            final var node2 = new LinkedListNode(5, null);
            test(node1, node2);
        }
        {
            final var node = new LinkedListNode(
                    5,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(2, null)
                    )
            );
            test(null, node);
        }
        {
            final var node = new LinkedListNode(
                    7,
                    new LinkedListNode(
                            1,
                            new LinkedListNode(6, null)
                    )
            );
            test(node, null);
        }
        {
            final var node1 = new LinkedListNode(
                    9,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(9, null)
                    )
            );
            final var node2 = new LinkedListNode(
                    9,
                    new LinkedListNode(
                            9,
                            new LinkedListNode(9, null)
                    )
            );
            test(node1, node2);
        }
    }

    private static void test(LinkedListNode node1, LinkedListNode node2) {
        head = null;
        if (node1 != node2) {
            final var node1Length = getLength(node1);
            final var node2Length = getLength(node2);
            if (node1Length < node2Length)
                for (var index = 0; index < node2Length - node1Length; ++index) node1 = new LinkedListNode(0, node1);
            else if (node2Length < node1Length)
                for (var index = 0; index < node1Length - node2Length; ++index) node2 = new LinkedListNode(0, node2);
        }
        System.out.print("node 1: ");
        if (node1 != null) node1.display();
        System.out.print("\nnode 2: ");
        if (node2 != null) node2.display();
        System.out.print("\nSum: ");
        final var partialSum = add(node1, node2);
        if (partialSum.hasCarry) head = new LinkedListNode(1, head);
        head.display();
        System.out.print("\n\n");
    }

    private static int getLength(LinkedListNode node) {
        var length = 0;
        for (; node != null; ++length, node = node.getNext()) ;
        return length;
    }

    private static PartialSum add(final LinkedListNode node1, final LinkedListNode node2) {
        if (node1 == null && node2 == null) return new PartialSum(0, false);
        final var partialSum = add(node1 == null ? null : node1.getNext(), node2 == null ? null : node2.getNext());
        final var currentSum = (node1 == null ? 0 : node1.getData())
                + (node2 == null ? 0 : node2.getData())
                + (partialSum.hasCarry ? 1 : 0);
        head = new LinkedListNode(currentSum % 10, head);
        return new PartialSum(currentSum % 10, currentSum >= 10);
    }

    private static final record PartialSum(int sum, boolean hasCarry) {
    }
}

final class LinkedListNode {
    private Integer data;
    private LinkedListNode next;

    LinkedListNode(final Integer data, final LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    Integer getData() {
        return data;
    }

    void setData(final int data) {
        this.data = data;
    }

    LinkedListNode getNext() {
        return next;
    }

    void setNext(final LinkedListNode next) {
        this.next = next;
    }

    void display() {
        for (var node = this; node != null; node = node.getNext()) System.out.printf("%d ", node.getData());
    }
}
