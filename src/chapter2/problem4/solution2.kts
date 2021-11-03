/**
 * Question:
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all
 * nodes greater than or equal to x. If x is contained within the list the values of x only need to be after the
 * elements less than x (see below). The partition element x can appear anywhere in the "right partition"; it does not
 * need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 *
 * Answer:
 * Creating one linked list.
 */

data class LinkedListNode(var data: Int, var next: LinkedListNode? = null) {
    fun partition(value: Int): LinkedListNode {
        var head: LinkedListNode? = null
        var tail: LinkedListNode? = null
        var current: LinkedListNode? = this
        while (current != null) {
            val node = LinkedListNode(current.data)
            if (current.data < value) {
                node.next = head
                head = node
                if (tail == null) tail = head
            } else {
                if (tail == null) {
                    tail = node
                    head = tail
                } else {
                    tail.next = node
                    tail = tail.next
                }
            }
            current = current.next
        }
        return head!!
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: LinkedListNode? = this
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
fun Collection<Int>.toLinkedList(): LinkedListNode {
    if (size == 0) throw IndexOutOfBoundsException()
    val head = LinkedListNode(first())
    var node = head
    (1 until size).forEach {
        node.next = LinkedListNode(elementAt(it))
        node = node.next!!
    }
    return head
}

with(listOf(3, 5, 8, 5, 10, 2, 1).toLinkedList()) { println("Input: $this\nOutput: ${partition(5)}") }
