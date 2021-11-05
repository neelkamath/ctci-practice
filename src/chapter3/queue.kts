class MyQueue<T> {
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    /** Whether there are no elements. */
    val isEmpty: Boolean get() = first == null

    fun add(data: T) {
        val node = Node(data)
        last?.next = node
        last = node
        if (first == null) first = node
    }

    /** Deletes and returns the front most data. Throws a [NoSuchElementException] if [isEmpty]. */
    fun remove(): T {
        if (isEmpty) throw NoSuchElementException()
        val data = first!!.data
        if (last == first) last = null
        first = first!!.next
        return data
    }

    /** Returns the front most data. Throws a [NoSuchElementException] if [isEmpty]. */
    fun peek(): T = if (isEmpty) throw NoSuchElementException() else first!!.data

    override fun toString(): String {
        val builder = StringBuilder()
        var node: Node<T>? = first
        while (node != null) {
            if (!builder.isEmpty()) builder.append("->")
            builder.append(node.data)
            node = node.next
        }
        builder.insert(0, '[')
        builder.append(']')
        return builder.toString()
    }

    private class Node<T>(var data: T, var next: Node<T>? = null)
}

with(MyQueue<Int>()) {
    println(this)
    println("isEmpty: $isEmpty")
    try {
        println("peek(): ${peek()}")
    } catch (exception: NoSuchElementException) {
        println(exception)
    }
    try {
        println("remove(): ${remove()}")
    } catch (exception: NoSuchElementException) {
        println(exception)
    }
    var size = 5U
    repeat(size.toInt()) {
        add(it)
        println(this)
        println("peek(): ${peek()}")
    }
    println("isEmpty: $isEmpty")
    repeat(size.plus(1U).toInt()) {
        try {
            remove()
            println(this)
            println("peek(): ${peek()}")
        } catch (exception: NoSuchElementException) {
            println(exception)
        }
    }
    println("isEmpty: $isEmpty")
}
