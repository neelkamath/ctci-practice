import java.util.HashSet;

/**
 * Question:
 * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
 * Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked
 * list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
 * <p>
 * Solution:
 * Iterating over the list, and comparing each node to a copy stored in a {@link HashSet}.
 */
public final class App1 {
    public static void main(final String[] args) {
        {
            final var list1 = new LinkedList();
            list1.append(3);
            list1.append(1);
            list1.append(5);
            list1.append(9);
            final var intersection = list1.append(7);
            list1.append(2);
            list1.append(1);
            final var list2 = new LinkedList();
            list2.append(4);
            list2.append(6);
            list2.getTail().setNext(intersection);
            test(list1, list2);
        }
        {
            final var list1 = new LinkedList();
            list1.append(3);
            list1.append(1);
            list1.append(5);
            list1.append(9);
            list1.append(7);
            list1.append(2);
            list1.append(1);
            final var list2 = new LinkedList();
            list2.append(4);
            list2.append(6);
            list2.append(7);
            list2.append(2);
            list2.append(1);
            test(list1, list2);
        }
    }

    private static void test(final LinkedList list1, final LinkedList list2) {
        System.out.print("List 1: ");
        list1.display();
        System.out.print("\nList 2: ");
        list2.display();
        final var intersection = findIntersection(list1, list2);
        System.out.printf("\nIntersection: %d\n", intersection == null ? null : intersection.getData());
    }

    private static LinkedList.Node findIntersection(final LinkedList list1, final LinkedList list2) {
        final var map = new HashSet<LinkedList.Node>();
        for (
                LinkedList.Node node1 = list1.getHead(), node2 = list2.getHead();
                node1 != null || node2 != null;
                node1 = node1 == null ? null : node1.getNext(), node2 = node2 == null ? null : node2.getNext()
        ) {
            if (map.contains(node1)) return node1;
            map.add(node1);
            if (map.contains(node2)) return node2;
            map.add(node2);
        }
        return null;
    }
}

final class LinkedList {
    private Node head = null, tail = null;

    Node getHead() {
        return head;
    }

    Node getTail() {
        return tail;
    }

    /**
     * @return the list's length computed in O(n) time, where "n" is the length of the list.
     */
    int getLength() {
        var length = 0;
        for (var node = getHead(); node != null; node = node.getNext(), ++length) ;
        return length;
    }

    /**
     * @return the head node.
     */
    Node prepend(final int data) {
        if (head == null) head = tail = new Node(data, null);
        else if (head == tail) head = new Node(data, tail);
        else head = new Node(data, head);
        return head;
    }

    /**
     * @return the tail node.
     */
    Node append(final int data) {
        final var node = new Node(data, null);
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
        var node = getHead();
        for (var index = 0; index < getLength() - 1; ++index, node = node.getNext())
            System.out.printf("%d, ", node.getData());
        System.out.print(node.getData());
    }

    static final class Node {
        private int data;
        private Node next;

        Node(final int data, final Node next) {
            this.data = data;
            this.next = next;
        }

        int getData() {
            return data;
        }

        void setData(final int data) {
            this.data = data;
        }

        Node getNext() {
            return next;
        }

        void setNext(final Node next) {
            this.next = next;
        }
    }
}
