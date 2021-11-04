/*
Question:
Remove Dups: Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?

Answer:
Using a temporary buffer.
 */

data class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    fun removeDuplicates() {
        val set = mutableSetOf(data)
        var node = this
        while (node.next != null)
            if (node.next!!.data in set) node.next = node.next!!.next
            else {
                node = node.next!!
                set.add(node.data)
            }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node = this as LinkedListNode<T>?
        while (node != null) {
            if (!builder.isEmpty()) builder.append(" -> ")
            builder.append(node!!.data)
            node = node!!.next
        }
        builder.insert(0, "[")
        builder.append("]")
        return builder.toString()
    }
}

/** Throws an [IndexOutOfBoundsException] if the [Collection] has no elements. */
fun <T> Collection<T>.toLinkedList(): LinkedListNode<T> {
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
    listOf(1),
    listOf(1, 1),
    listOf(1, 2),
    listOf(1, 1, 1),
    listOf(1, 2, 3),
    listOf(1, 1, 2),
    listOf(1, 2, 1),
    listOf(1, 2, 3, 4, 5),
    listOf(1, 2, 3, 4, 5, 4, 3, 2, 1),
).forEach {
    val list = it.toLinkedList()
    println("Before: $list")
    list.removeDuplicates()
    println("After: $list")
}
