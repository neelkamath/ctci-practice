class MyLinkedList<T> {
    private var headNode: Node<T>? = null
    private var tailNode: Node<T>? = null
    val head: T? get() = headNode?.data
    val tail: T? get() = tailNode?.data
    var size: UInt = 0U
        private set

    /** Throws an [IndexOutOfBoundsException] if there's no such element. */
    fun read(index: UInt): T {
        if (index >= size) throw IndexOutOfBoundsException()
        var node = headNode!!
        repeat(index.toInt()) { node = node.next!! }
        return node.data
    }

    /** Throws an [IndexOutOfBoundsException] if there's no such element. */
    fun delete(index: UInt) {
        if (index >= size) throw IndexOutOfBoundsException()
        when (index) {
            0U -> deleteHead()
            size - 1U -> deleteTail()
            else -> {
                var node = headNode!!
                repeat(index.minus(1U).toInt()) { node = node.next!! }
                node.next = node.next!!.next
                --size
            }
        }
    }

    /** Throws an [IllegalArgumentException] if there's no such element. */
    fun deleteHead() {
        if (size == 0U) throw IllegalArgumentException()
        headNode = headNode!!.next
        if (headNode == null) tailNode = null
        --size
    }

    /** Throws an [IllegalArgumentException] if there's no such element. */
    fun deleteTail() {
        when (size) {
            0U -> throw IllegalArgumentException()
            1U -> deleteHead()
            else -> {
                var node = headNode!!
                repeat(size.minus(2U).toInt()) { node = node.next!! }
                node.next = node.next!!.next
                tailNode = node
                --size
            }
        }
    }

    /**
     * For example, if the list is 1->2->3->4, and you insert 10 at [index] 2, then the list will become 1->2->10->3->4.
     *
     * Throws an [IllegalArgumentException] if the [index] is greater than the [size].
     */
    fun insert(index: UInt, data: T) {
        if (index > size) throw IllegalArgumentException()
        when (index) {
            0U -> prepend(data)
            size -> append(data)
            else -> {
                var node = headNode!!
                repeat(index.minus(1U).toInt()) { node = node.next!! }
                node.next = Node(data, node.next)
                ++size
            }
        }
    }

    fun prepend(data: T) {
        headNode = Node(data, headNode)
        if (size == 0U) tailNode = headNode
        ++size
    }

    fun append(data: T) {
        if (size == 0U) prepend(data)
        else {
            tailNode!!.next = Node(data)
            tailNode = tailNode!!.next
            ++size
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("[")
        var node = headNode
        repeat(size.toInt()) {
            if (it > 0) builder.append(" -> ")
            builder.append("${node!!.data}")
            node = node!!.next
        }
        builder.append("]")
        return builder.toString()
    }

    private data class Node<T>(val data: T, var next: Node<T>? = null)
}

fun <T> MyLinkedList<T>.printMetadata(): Unit = println("head: $head\ntail: $tail\nsize: $size\n$this")

with(MyLinkedList<Int>()) {
    printMetadata()
    repeat(3) {
        println("prepend($it)")
        prepend(it)
        printMetadata()
    }
}

with(MyLinkedList<Int>()) {
    printMetadata()
    try {
        deleteHead()
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
    repeat(3) {
        println("append($it)")
        append(it)
        printMetadata()
    }
    deleteHead()
    println(this)
}

with(MyLinkedList<Int>()) {
    try {
        deleteTail()
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
    repeat(3) {
        println("append($it)")
        append(it)
        printMetadata()
    }
    deleteTail()
    println(this)
}

with(MyLinkedList<Int>()) {
    try {
        insert(3U, 0)
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
    repeat(3) { append(it) }
    insert(0U, 100)
    println(this)
    insert(2U, 200)
    println(this)
    insert(size - 1U, 300)
    println(this)
    insert(size, 400)
    println(this)
    try {
        read(100U)
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("read(1): ${read(1U)}")
    try {
        delete(100U)
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    delete(3U)
    println(this)
    delete(0U)
    println(this)
    delete(size - 1U)
    println(this)
}
