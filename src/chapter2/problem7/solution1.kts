/*
Question:
Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note
that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the
exact same node (by reference) as the jth node of the second linked list, then they are intersecting.

Answer:
Saving each node to a hash set.
 */

class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    /** Returns either the intersecting node's value or `null` if there's no intersection. */
    fun findIntersection(node: LinkedListNode<T>): T? {
        val set = mutableSetOf<LinkedListNode<T>>()
        var node1: LinkedListNode<T>? = this
        var node2: LinkedListNode<T>? = node
        while (node1 != null || node2 != null) {
            if (node1 in set) return node1!!.data else if (node1 != null) set.add(node1)
            if (node2 in set) return node2!!.data else if (node2 != null) set.add(node2)
            node1 = node1?.next
            node2 = node2?.next
        }
        return null
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: LinkedListNode<T>? = this
        while (node != null) {
            if (!builder.isEmpty()) builder.append("->")
            builder.append(node.data)
            node = node.next
        }
        builder.insert(0, "[")
        builder.append("]")
        return builder.toString()
    }
}

fun <T> printIntersection(list1: LinkedListNode<T>, list2: LinkedListNode<T>): Unit =
    println("List 1: $list1\nList 2: $list2\nIntersection: ${list1.findIntersection(list2)}")

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
