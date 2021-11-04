/*
Question:
Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits are
stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers
and returns the sum as a linked list.
EXAMPLE
Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
Output: 2 -> 1 -> 9. That is, 912.
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem.
Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).
That is, 617 + 295.
Output: 9 -> 1 -> 2. That is, 912.

Answer:
Solving for reverse ordered numbers.
 */

data class LinkedListNode(var data: UInt, var next: LinkedListNode? = null) {
    /** Returns a [LinkedListNode] that's the sum of this and the [rhs]. */
    operator fun plus(rhs: LinkedListNode): LinkedListNode {
        var head: LinkedListNode? = null
        var tail: LinkedListNode? = null
        var lhsNode: LinkedListNode? = this
        var rhsNode: LinkedListNode? = rhs
        var hasCarry = false
        while (lhsNode != null || rhsNode != null) {
            val sum = (lhsNode?.data ?: 0U) + (rhsNode?.data ?: 0U) + (if (hasCarry) 1U else 0U)
            val digit = sum % 10U
            hasCarry = sum > 9U
            val node = LinkedListNode(digit)
            if (tail == null) {
                tail = node
                head = tail
            } else {
                tail.next = node
                tail = tail.next
            }
            lhsNode = lhsNode?.next
            rhsNode = rhsNode?.next
        }
        if (hasCarry) tail!!.next = LinkedListNode(1U)
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
fun Collection<UInt>.toLinkedList(): LinkedListNode {
    if (size == 0) throw IndexOutOfBoundsException()
    val head = LinkedListNode(first())
    var node = head
    (1 until size).forEach {
        node.next = LinkedListNode(elementAt(it))
        node = node.next!!
    }
    return head
}

setOf(
    listOf(7U, 1U, 6U) to listOf(5U, 9U, 2U),
    listOf(9U, 9U, 9U) to listOf(9U, 9U, 9U),
    listOf(1U) to listOf(1U, 2U, 3U),
    listOf(1U, 2U, 3U) to listOf(1U),
).forEach { (first, second) ->
    val lhs = first.toLinkedList()
    val rhs = second.toLinkedList()
    println("$lhs + $rhs = ${lhs + rhs}")
}
