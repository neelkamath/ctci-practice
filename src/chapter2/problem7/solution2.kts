/*
Question:
Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note
that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the
exact same node (by reference) as the jth node of the second linked list, then they are intersecting.

Answer:
Using no additional data structures.
 */

class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    /** Returns either the intersecting node or `null` if there's no intersection. */
    fun findIntersection(node: LinkedListNode<T>): LinkedListNode<T>? {
        val thisLength = readLength()
        val otherLength = node.readLength()
        var thisNode: LinkedListNode<T>? = this
        var otherNode: LinkedListNode<T>? = node
        if (thisLength < otherLength) repeat(otherLength.minus(thisLength).toInt()) { otherNode = otherNode!!.next }
        else repeat(thisLength.minus(otherLength).toInt()) { thisNode = thisNode!!.next }
        while (thisNode != null) {
            if (thisNode == otherNode) return thisNode
            thisNode = thisNode!!.next
            otherNode = otherNode!!.next
        }
        return null
    }

    private fun readLength(): UInt {
        var length = 1U
        var node = this
        while (node.next != null) {
            node = node.next!!
            ++length
        }
        return length
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: LinkedListNode<T>? = this
        while (node != null) {
            if (!builder.isEmpty()) builder.append("->")
            builder.append(node!!.data)
            node = node!!.next
        }
        builder.insert(0, "[")
        builder.append("]")
        return builder.toString()
    }
}

fun <T> printIntersection(list1: LinkedListNode<T>, list2: LinkedListNode<T>): Unit =
    println("List 1: $list1\nList 2: $list2\nIntersection: ${list1.findIntersection(list2)?.data}")

run {
    val list1 = LinkedListNode(
        1,
    )
    val list2 = LinkedListNode(
        1,
    )
    printIntersection(list1, list2)
}
run {
    val list1 = LinkedListNode(
        1,
        LinkedListNode(
            2,
        ),
    )
    val list2 = LinkedListNode(
        1,
        LinkedListNode(
            2,
            LinkedListNode(
                3,
            ),
        ),
    )
    printIntersection(list1, list2)
}
run {
    val intersectingNode = LinkedListNode(
        2,
        LinkedListNode(
            3,
        ),
    )
    val list1 = LinkedListNode(
        1,
        intersectingNode,
    )
    val list2 = LinkedListNode(
        1,
        intersectingNode,
    )
    printIntersection(list1, list2)
}
run {
    val intersectingNode = LinkedListNode(
        2,
        LinkedListNode(
            3,
        ),
    )
    val list1 = LinkedListNode(
        100,
        LinkedListNode(
            200,
            LinkedListNode(
                300,
                intersectingNode,
            ),
        ),
    )
    val list2 = LinkedListNode(
        1,
        intersectingNode,
    )
    printIntersection(list1, list2)
}
