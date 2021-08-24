/**
 * Question:
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all
 * nodes greater than or equal to x. If x is contained within the list, the values of x only need to be after the
 * elements less than x (see below). The partition element x can appear anywhere in the "right partition"; it does not
 * need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 * <p>
 * Solution:
 * Creating two linked lists.
 */
public final class App1 {
    public static void main(final String[] args) {
        test(
                new LinkedListNode(
                        3,
                        new LinkedListNode(
                                5,
                                new LinkedListNode(
                                        8,
                                        new LinkedListNode(
                                                5,
                                                new LinkedListNode(
                                                        10,
                                                        new LinkedListNode(
                                                                2,
                                                                new LinkedListNode(1, null)
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                5
        );
    }

    private static void test(final LinkedListNode node, final int value) {
        System.out.print("Before: ");
        node.display();
        System.out.print("\nAfter: ");
        partition(node, value).display();
        System.out.println();
    }

    private static LinkedListNode partition(LinkedListNode node, final int value) {
        LinkedListNode smallHead = null, smallTail = null, bigHead = null, bigTail = null;
        for (; node != null; node = node.getNext()) {
            final var newNode = new LinkedListNode(node.getData(), null);
            if (node.getData() < value)
                if (smallHead == null) smallTail = smallHead = newNode;
                else if (smallHead == smallTail) smallHead.setNext(smallTail = newNode);
                else {
                    smallTail.setNext(newNode);
                    smallTail = smallTail.getNext();
                }
            else if (bigHead == null) bigTail = bigHead = newNode;
            else if (bigHead == bigTail) bigHead.setNext(bigTail = newNode);
            else {
                bigTail.setNext(newNode);
                bigTail = bigTail.getNext();
            }
        }
        if (smallHead == null) return bigHead;
        smallTail.setNext(bigHead);
        return smallHead;
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
