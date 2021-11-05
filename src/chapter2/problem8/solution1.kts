/*
Question:
Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
DEFINITION
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a
loop in the linked list.
EXAMPLE
Input: A -> B -> C -> D -> E -> C [the same C as earlier]
Output: C

Answer:
Using an additional data structure.
 */

class LinkedListNode<T>(var data: T, var next: LinkedListNode<T>? = null) {
    /** Returns either the corrupt node or `null` if there's no loop. */
    fun findLoop(): LinkedListNode<T>? {
        val set = mutableSetOf<LinkedListNode<T>>()
        var node: LinkedListNode<T>? = this
        while (node != null) {
            if (node in set) return node
            set.add(node)
            node = node.next
        }
        return null
    }
}

run {
    val e = LinkedListNode('E')
    val d = LinkedListNode('D', e)
    val c = LinkedListNode('C', d)
    val b = LinkedListNode('B', c)
    val a = LinkedListNode('A', b)
    e.next = c
    println(a.findLoop()?.data)
}
run {
    val list = LinkedListNode(
        'A',
        LinkedListNode(
            'B',
            LinkedListNode(
                'A',
            ),
        ),
    )
    println(list.findLoop()?.data)
}
run {
    val c = LinkedListNode('C')
    c.next = c
    val b = LinkedListNode('B', c)
    val a = LinkedListNode('A', b)
    println(a.findLoop()?.data)
}
run {
    val e = LinkedListNode('E')
    val d = LinkedListNode('D', e)
    e.next = d
    val c = LinkedListNode('C', d)
    val b = LinkedListNode('B', c)
    val a = LinkedListNode('A', b)
    println(a.findLoop()?.data)
}
