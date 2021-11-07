/*
Question:
Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real
life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure
SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the
previous one exceeds capacity. SetOfStacks.push() and SetOfStacks. pop() should behave identically to a single stack
(that is, pop() should return the same values as it would if there were just a single stack).
FOLLOW UP
Implement a function popAt(int index) which performs a pop operation on a specific sub stack.

Answer:
Using an ArrayList of stacks.
 */

import java.util.EmptyStackException

class MyStack<T> {
    private var top: Node<T>? = null

    /** Whether there are no elements. */
    val isEmpty: Boolean get() = top == null

    /** The number of elements in this stack. */
    var size: UInt = 0U
        private set

    /** Removes and returns the topmost element. Throws an [EmptyStackException] if [isEmpty]. */
    fun pop(): T {
        if (isEmpty) throw EmptyStackException()
        val data = top!!.data
        top = top!!.next
        --size
        return data
    }

    /** Adds the [data] to the top of this stack. */
    fun push(data: T) {
        top = Node(data, top)
        ++size
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

class SetOfStacks<T>(
    /** The maximum number of elements in a stack. */
    private val stackCapacity: UInt,
) {
    /** The underlying data structure. */
    private val list: MutableList<MyStack<T>> = mutableListOf()

    /** Whether there are no elements. */
    val isEmpty: Boolean get() = list.isEmpty()

    /** Removes and returns the topmost element. Throws an [EmptyStackException] if [isEmpty]. */
    fun pop(): T {
        if (isEmpty) throw EmptyStackException()
        val stack = list.last()
        val data = stack.pop()
        if (stack.isEmpty) list.removeLast()
        return data
    }

    /**
     * Removes and returns the topmost element at the specified [index].
     *
     * Throws an [EmptyStackException] if [isEmpty]. Throws a [NoSuchElementException] if there's no such stack.
     */
    fun popAt(index: UInt): T {
        if (isEmpty) throw EmptyStackException()
        validateIndex(index)
        val data = list[index.toInt()].pop()
        shiftLeft(index)
        return data
    }

    /** Throws a [NoSuchElementException] if there's no such stack. */
    private fun validateIndex(index: UInt) {
        if (index >= list.size.toUInt()) throw NoSuchElementException()
    }

    /**
     * Shifts the stack elements to the right of the [index] to the left by one element. Assumes that either there are
     * no stacks to the right of the [index] or the stack at the [index] is at [stackCapacity] - `1`.
     *
     * For example, if this stack is { [3->2->1], [5->4], [9->8->7], [12->11->10] }, and [index] is `1U`, then this
     * stack will become { [3->2->1], [7->5->4], [8->9->10], [12->11] }.
     *
     * This operation performs in O(n) time complexity, and O(n) space complexity.
     *
     * Throws a [NoSuchElementException] if there's no such stack.
     */
    private fun shiftLeft(index: UInt) {
        validateIndex(index)
        if (index.plus(1U).toInt() !in list.indices) return
        val stack = MyStack<T>()
        val nextStack = list[index.plus(1U).toInt()]
        while (!nextStack.isEmpty) stack.push(nextStack.pop())
        list[index.toInt()].push(stack.pop())
        while (!stack.isEmpty) nextStack.push(stack.pop())
        shiftLeft(index + 1U)
    }

    /** Adds the [data] to the top of this stack. */
    fun push(data: T) {
        if (isEmpty || list.last().size == stackCapacity) list.add(MyStack())
        list.last().push(data)
    }

    /** Returns the topmost element. Throws an [EmptyStackException] if [isEmpty]. */
    fun peek(): T = if (isEmpty) throw EmptyStackException() else list.last().peek()

    override fun toString(): String {
        val builder = StringBuilder()
        list.forEach {
            if (!builder.isEmpty()) builder.append(", ")
            builder.append(it)
        }
        builder.insert(0, "{ ")
        builder.append(" }")
        return builder.toString()
    }
}

with(SetOfStacks<Int>(stackCapacity = 3U)) {
    println(this)
    println("isEmpty: $isEmpty")
    try {
        println("pop(): ${pop()}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    try {
        println("peek(): ${peek()}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    val size = 10
    repeat(size) {
        println("push($it)")
        push(it)
        println("peek(): ${peek()}")
        println(this)
    }
    println("isEmpty: $isEmpty")
    repeat(size + 1) {
        try {
            println("pop(): ${pop()}")
            println("peek(): ${peek()}")
            println(this)
        } catch (exception: EmptyStackException) {
            println(exception)
        }
    }
    println("isEmpty: $isEmpty")
    repeat(12) {
        val data = it + 1
        println("push($data)")
        push(data)
    }
    println(this)
    println("popAt(1)")
    popAt(1U)
    println(this)
}
