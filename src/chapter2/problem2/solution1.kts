/**
 * Question:
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 *
 * Answer:
 * Using the runner technique.
 */

data class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    /**
     * Returns the element at length - 1 - k. For example, the linked list 1 -> 2 -> 3 will return 3 and 2 for k=0 and
     * k=1 respectively.
     *
     * Throws an [IllegalArgumentException] if there's no such element.
     */
    fun findKthToLast(k: UInt): T {
        var current = this
        var runner = this
        repeat(k.toInt()) { if (runner.next == null) throw IllegalArgumentException() else runner = runner.next!! }
        while (runner.next != null) {
            runner = runner.next!!
            current = current.next!!
        }
        return current.data
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node = this as LinkedListNode<T>?
        while (node != null) {
            if (!builder.isEmpty()) builder.append(" -> ")
            builder.append(node.data)
            node = node.next
        }
        builder.insert(0, "[")
        builder.append("]")
        return builder.toString()
    }
}

/** Throws an [IndexOutOfBoundsException] if the [Collection] has no elements. */
fun IntRange.toLinkedList(): LinkedListNode<Int> {
    if (isEmpty()) throw IndexOutOfBoundsException()
    val head = LinkedListNode(first())
    var node = head
    (1..last).forEach {
        node.next = LinkedListNode(elementAt(it))
        node = node.next!!
    }
    return head
}

repeat(5) { index ->
    val list = (0..index).toLinkedList()
    println(list)
    try {
        repeat(index + 2) {
            print("k=$it: ")
            println(list.findKthToLast(it.toUInt()))
        }
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
}
