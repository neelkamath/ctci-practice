import java.util.Stack;

/**
 * Question:
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 * <p>
 * Solution:
 * Using a stack.
 */
public final class App2 {
    public static void main(final String[] args) {
        test(new LinkedList());
        {
            final var list = new LinkedList();
            list.append(1);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(1);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(2);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(2);
            list.append(3);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(2);
            list.append(3);
            list.append(2);
            list.append(1);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(2);
            list.append(3);
            list.append(3);
            list.append(2);
            list.append(1);
            test(list);
        }
        {
            final var list = new LinkedList();
            list.append(1);
            list.append(2);
            list.append(3);
            list.append(2);
            list.append(4);
            test(list);
        }
    }

    private static void test(final LinkedList list) {
        list.display();
        System.out.printf(": %b\n", isPalindrome(list));
    }

    private static boolean isPalindrome(final LinkedList list) {
        final var stack = new Stack<Integer>();
        if (list.getLength() < 2) return true;
        var node = list.getHead();
        final var length = list.getLength();
        for (var index = 0; index < length / 2; ++index, node = node.getNext()) stack.push(node.getData());
        if (length % 2 == 1) node = node.getNext();
        for (; node != null; node = node.getNext()) if (stack.pop() != node.getData()) return false;
        return true;
    }
}

final class LinkedList {
    private LinkedListNode head = null, tail = null;
    private int length = 0;

    LinkedListNode getHead() {
        return head;
    }

    LinkedListNode getTail() {
        return tail;
    }

    int getLength() {
        return length;
    }

    void prepend(final int data) {
        if (head == null) head = tail = new LinkedListNode(data, null);
        else if (head == tail) head = new LinkedListNode(data, tail);
        else head = new LinkedListNode(data, head);
        ++length;
    }

    void append(final int data) {
        final var node = new LinkedListNode(data, null);
        if (head == null) head = node;
        else tail.setNext(node);
        tail = node;
        ++length;
    }

    void display() {
        if (head == null) {
            System.out.print("null");
            return;
        }
        var node = getHead();
        for (var index = 0; index < length - 1; ++index, node = node.getNext())
            System.out.printf("%d ", node.getData());
        System.out.print(node.getData());
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
}
