class MyLinkedList<T> {
    private var headNode: Node<T>? = null
    private var tailNode: Node<T>? = null
    var head: T? = null
        get() = headNode?.data
        private set
    var tail: T? = null
        get() = tailNode?.data
        private set
    var length: Int = 0
        private set

    /** Throws an [IndexOutOfBoundsException] if there's no such element. */
    fun read(index: Int): T {
        if (index >= length) throw IndexOutOfBoundsException()
        var node = headNode!!
        repeat(index) { node = node.next!! }
        return node.data
    }

    /** Throws an [IndexOutOfBoundsException] if there's no such element. */
    fun delete(index: Int) {
        if (index >= length) throw IndexOutOfBoundsException()
        when (index) {
            0 -> deleteHead()
            length - 1 -> deleteTail()
            else -> {
                var node = headNode!!
                repeat(index - 1) { node = node.next!! }
                node.next = node.next!!.next
                --length
            }
        }
    }

    /** Throws an [IllegalArgumentException] if there's no such element. */
    fun deleteHead() {
        if (length == 0) throw IllegalArgumentException()
        if (tailNode == headNode) tailNode = headNode!!.next
        headNode = headNode!!.next
        --length
    }

    /** Throws an [IllegalArgumentException] if there's no such element. */
    fun deleteTail() {
        when (length) {
            0 -> throw IllegalArgumentException()
            1 -> deleteHead()
            else -> {
                var node = headNode!!
                repeat(length - 2) { node = node.next!! }
                node.next = node.next!!.next
                tailNode = node
                --length
            }
        }
    }

    /**
     * For example, if the list is 1->2->3->4, and you insert 10 at [index] 2, then the list will become 1->2->10->3->4.
     *
     * Throws an [IllegalArgumentException] if the [index] is greater than the [length].
     */
    fun insert(data: T, index: Int) {
        if (index > length) throw IllegalArgumentException()
        when (index) {
            0 -> prepend(data)
            length -> append(data)
            else -> {
                var node = headNode!!
                repeat(index - 1) { node = node.next!! }
                node.next = Node(data, node.next)
                ++length
            }
        }
    }

    fun prepend(data: T) {
        headNode = Node(data, headNode)
        if (length == 0) tailNode = headNode
        else if (length == 1) tailNode = headNode!!.next
        ++length
    }

    fun append(data: T) {
        if (length == 0) prepend(data)
        else {
            tailNode!!.next = Node(data)
            tailNode = tailNode!!.next
            ++length
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("[")
        var node = headNode
        repeat(length) {
            if (it > 0) builder.append(" -> ")
            builder.append("${node!!.data}")
            node = node!!.next
        }
        builder.append("]")
        return builder.toString()
    }

    private data class Node<T>(val data: T, var next: Node<T>? = null)
}
