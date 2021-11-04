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
Solving for forward ordered numbers.
 */

data class LinkedListNode(var data: UInt, var next: LinkedListNode? = null) {
    /** Returns a [LinkedListNode] that's the sum of this and the [rhs]. */
    operator fun plus(rhs: LinkedListNode): LinkedListNode {
        val lhsLength = readLength()
        val rhsLength = rhs.readLength()
        if (lhsLength < rhsLength) pad(rhsLength - lhsLength) else rhs.pad(lhsLength - rhsLength)
        val (node, hasCarry) = add(this, rhs)
        return if (hasCarry) LinkedListNode(1U, node) else node
    }

    private fun readLength(): UInt {
        var length = 0U
        var node: LinkedListNode? = this
        while (node != null) {
            ++length
            node = node.next
        }
        return length
    }

    /** Prepends this with the number of [zeros]. For example, padding [1 -> 2] with one zero makes it [0 -> 1 -> 2]. */
    private fun pad(zeros: UInt): Unit = repeat(zeros.toInt()) {
        next = LinkedListNode(data, next)
        data = 0U
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: LinkedListNode? = this
        while (node != null) {
            if (!builder.isEmpty()) builder.append(" -> ")
            builder.append(node!!.data)
            node = node!!.next
        }
        builder.insert(0, "[")
        builder.append("]")
        return builder.toString()
    }

    private companion object {
        /**
         * Returns the sum of the [lhs] and [rhs] (assumed to have the same lengths).
         *
         * [sum] and [hasCarry] are meant to be passed from within this function for recursion, and must not be passed
         * by the caller.
         */
        private fun add(lhs: LinkedListNode, rhs: LinkedListNode): TempSum {
            var tempSum: TempSum? = null
            if (lhs.next != null) tempSum = add(lhs.next!!, rhs.next!!)
            val sum = lhs.data + rhs.data + if (tempSum?.hasCarry == true) 1U else 0U
            val node = LinkedListNode(sum % 10U, tempSum?.node)
            return TempSum(node, sum > 9U)
        }

        private data class TempSum(val node: LinkedListNode, val hasCarry: Boolean)
    }
}

/** Throws an [IndexOutOfBoundsException] if this has no elements. */
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
    listOf(6U, 1U, 7U) to listOf(2U, 9U, 5U),
    listOf(9U, 9U, 9U) to listOf(9U, 9U, 9U),
    listOf(1U) to listOf(1U, 2U, 3U),
    listOf(1U, 2U, 3U) to listOf(1U),
).forEach { (first, second) ->
    val lhs = first.toLinkedList()
    val rhs = second.toLinkedList()
    println("$lhs + $rhs = ${lhs + rhs}")
}
