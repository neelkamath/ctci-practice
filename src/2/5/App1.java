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
 * Supposing the integers are in reverse order.
 */
public final class App1 {
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

    private static void test(final LinkedListNode node1, final LinkedListNode node2) {
        System.out.print("node1: ");
        if (node1 != null) node1.display();
        System.out.print("\nnode 2: ");
        if (node2 != null) node2.display();
        System.out.print("\nSum: ");
        add(node1, node2).display();
        System.out.println();
    }

    private static LinkedListNode add(LinkedListNode node1, LinkedListNode node2) {
        LinkedListNode head = null, tail = null;
        var hasCarry = false;
        for (
                ;
                node1 != null || node2 != null;
                node1 = node1 == null ? null : node1.getNext(), node2 = node2 == null ? null : node2.getNext()
        ) {
            final var sum = (node1 == null ? 0 : node1.getData())
                    + (node2 == null ? 0 : node2.getData())
                    + (hasCarry ? 1 : 0);
            final var node = new LinkedListNode(sum % 10, null);
            hasCarry = sum > 10;
            if (head == null) head = tail = node;
            else {
                tail.setNext(node);
                tail = node;
            }
        }
        if (hasCarry) tail.setNext(new LinkedListNode(1, null));
        return head;
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
