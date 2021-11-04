/*
Question:
Palindrome: Implement a function to check if a linked list is a palindrome.

Answer:
Using the runner technique.
 */

class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    fun isPalindrome(): Boolean {
        var node: LinkedListNode<T>? = null
        var current: LinkedListNode<T>? = this
        var runner: LinkedListNode<T>? = this
        while (runner?.next != null) {
            node = LinkedListNode(current!!.data, node)
            current = current.next
            runner = runner.next!!.next
        }
        if (runner != null) current = current!!.next
        while (current != null) {
            if (node!!.data != current.data) return false
            node = node.next
            current = current.next
        }
        return true
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: LinkedListNode<T>? = this
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

/** Throws an [IndexOutOfBoundsException] if this has no elements. */
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
    listOf(1, 2),
    listOf(1, 1),
    listOf(1, 1, 1),
    listOf(1, 2, 3),
    listOf(1, 2, 3, 4),
    listOf(1, 2, 2, 1),
    listOf(1, 2, 3, 2, 1),
).forEach {
    with(it.toLinkedList()) { println("$this: ${isPalindrome()}") }
}
