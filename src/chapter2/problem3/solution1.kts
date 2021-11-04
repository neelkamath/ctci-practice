/*
Question:
Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node,
not necessarily the exact middle) of a singly linked list, given only access to that node.
EXAMPLE
Input:the node c from the linked list a->b->c->d->e->f
Result: nothing is returned, but the new linked list looks like a->b->d->e->f

Answer:
Replacing the current node with the next one.
 */

data class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
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

fun <T> deleteMiddleNode(middleNode: LinkedListNode<T>) {
    middleNode.data = middleNode.next!!.data
    middleNode.next = middleNode.next!!.next
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

with((0..2).toLinkedList()) {
    println(this)
    deleteMiddleNode(next!!)
    println(this)
}
with((0..3).toLinkedList()) {
    println(this)
    deleteMiddleNode(next!!)
    println(this)
}
