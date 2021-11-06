/*
Question:
Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum
element? Push, pop and min should all operate in 0(1) time.

Answer:
Only storing new minimums.
 */

import java.util.EmptyStackException

class MyStack {
    private var top: Node? = null
    private var mins: MutableList<Int> = mutableListOf()

    /** Either the minimum element in this stack or `null` if the stack is empty. */
    val min: Int? get() = mins.lastOrNull()

    fun push(data: Int) {
        top = Node(data, top)
        if (mins.isEmpty() || mins.last() >= data) mins.add(data)
    }

    /** Throws an [EmptyStackException] if this stack is empty. */
    fun pop(): Int {
        if (top == null) throw EmptyStackException()
        val data = top!!.data
        top = top!!.next
        if (data == mins.last()) mins.removeLast()
        return data
    }

    override fun toString(): String {
        val builder = StringBuilder()
        var node: Node? = top
        while (node != null) {
            if (!builder.isEmpty()) builder.append("->")
            builder.append(node.data)
            node = node.next
        }
        builder.insert(0, '[')
        builder.append(']')
        return builder.toString()
    }

    private class Node(var data: Int, var next: Node? = null)
}

with(MyStack()) {
    println("min: $min")
    try {
        println("pop(): ${pop()}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    listOf(1, 2, 3, 300, 150, 450, -2, 9_000).forEach {
        println("push($it)")
        push(it)
    }
    println(this)
    while (min != null) {
        println("min: $min")
        println("pop(): ${pop()}")
    }
}
