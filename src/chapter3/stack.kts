import java.util.EmptyStackException

class MyStack<T> {
    private var top: Node<T>? = null

    /** Whether there are no elements. */
    val isEmpty: Boolean get() = top == null

    /** Removes and returns the topmost element. Throws an [EmptyStackException] if [isEmpty]. */
    fun pop(): T {
        if (isEmpty) throw EmptyStackException()
        val data = top!!.data
        top = top!!.next
        return data
    }

    /** Adds the [data] to the top of this stack. */
    fun push(data: T) {
        top = Node(data, top)
    }

    /** Returns the topmost element. Throws an [EmptyStackException] if [isEmpty]. */
    fun peek(): T = if (isEmpty) throw EmptyStackException() else top!!.data

    override fun toString(): String {
        val builder = StringBuilder()
        var node: Node<T>? = top
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

with(MyStack<Int>()) {
    println(this)
    println("isEmpty: $isEmpty")
    try {
        println("peek(): ${peek()}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    try {
        println("pop()")
        pop()
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    val size = 3U
    repeat(size.toInt()) {
        println("push($it)")
        push(it)
        println(this)
    }
    println("peek(): ${peek()}")
    println("isEmpty: $isEmpty")
    repeat(size.plus(1U).toInt()) {
        try {
            println("pop(): ${pop()}")
            println(this)
        } catch (exception: EmptyStackException) {
            println(exception)
        }
    }
}
