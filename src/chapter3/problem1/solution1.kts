/*
Question:
Three in One: Describe how you could use a single array to implement three stacks.

Answer:
Using fixed divisions.
 */

import java.util.EmptyStackException

class ArrayOfStacks<T>(
    /** The size of each stack. */
    private val capacities: List<UInt>,
) {
    /** The underlying data structure. The topmost element of each stack is the rightmost element. */
    private val array: Array<Any?> = Array(capacities.sum().toInt()) { null }

    /** The number of elements in each stack. */
    private val sizes: MutableList<UInt> = MutableList(capacities.size) { 0U }

    /**
     * Whether there are no elements in the stack at the specified [index].
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack.
     */
    fun isEmpty(index: UInt): Boolean {
        validateIndex(index)
        return sizes[index.toInt()] == 0U
    }

    /**
     * Returns the [array] index of the topmost element in the stack at the [index], or `null` if the stack [isEmpty].
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack.
     */
    private fun computeTopIndex(index: UInt): UInt? {
        validateIndex(index)
        if (sizes[index.toInt()] == 0U) return null
        return computeBottomIndex(index) + sizes[index.toInt()].let { if (it == 0U) it else it - 1U }
    }

    /**
     * Deletes and returns the topmost element of the stack at the specified [index].
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack. Throws an [EmptyStackException] if the stack
     * [isEmpty].
     */
    fun pop(index: UInt): T {
        validateIndex(index)
        if (isEmpty(index)) throw EmptyStackException()
        val topIndex = computeTopIndex(index)!!.toInt()
        val data = array[topIndex]
        array[topIndex] = null
        --sizes[index.toInt()]
        return data as T
    }

    /**
     * Returns the topmost element at the stack at the specified [index].
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack. Throws a [EmptyStackException] if the stack
     * [isEmpty].
     */
    fun peek(index: UInt): T {
        validateIndex(index)
        if (isEmpty(index)) throw EmptyStackException()
        return array[computeTopIndex(index)!!.toInt()] as T
    }

    /**
     * Places the [data] as the topmost element for the stack at the specified [index].
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack. May throw a [StackFullException].
     */
    fun push(index: UInt, data: T) {
        validateIndex(index)
        if (isFull(index)) throw StackFullException()
        if (isEmpty(index)) array[computeBottomIndex(index).toInt()] = data
        else array[computeTopIndex(index)!!.toInt() + 1] = data
        ++sizes[index.toInt()]
    }

    /** Throws an [IndexOutOfBoundsException] if there's no such stack. */
    private fun validateIndex(index: UInt) {
        if (index.toInt() >= capacities.size) throw IndexOutOfBoundsException()
    }

    /**
     * Returns whether the stack at the specified [index] is full.
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack.
     */
    private fun isFull(index: UInt): Boolean {
        validateIndex(index)
        return sizes[index.toInt()] == capacities[index.toInt()]
    }

    /**
     * Returns the array index the stack the specified [index] has its first element at (either `null` or the bottommost
     * element).
     *
     * Throws an [IndexOutOfBoundsException] if there's no such stack.
     */
    private fun computeBottomIndex(index: UInt): UInt {
        validateIndex(index)
        return capacities.subList(0, index.toInt()).sum()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        capacities.withIndex().forEach { (index, size) ->
            if (index > 0) builder.append(", ")
            builder.append('[')
            val offset = computeBottomIndex(index.toUInt()).toInt()
            repeat(size.toInt()) {
                if (builder[builder.length - 1] != '[') builder.append("<-")
                builder.append(array[offset + it])
            }
            builder.append(']')
        }
        builder.insert(0, '{')
        builder.append('}')
        return builder.toString()
    }
}

class StackFullException : Exception()

val sizes = listOf(0U, 1U, 2U, 3U, 4U, 5U)
with(ArrayOfStacks<Int>(sizes)) {
    println(this)
    repeat(sizes.size) { println("isEmpty($it): ${isEmpty(it.toUInt())}") }
    try {
        println("pop(1): ${pop(1U)}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    try {
        println("pop(10): ${pop(10U)}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    try {
        println("peek(1): ${peek(1U)}")
    } catch (exception: EmptyStackException) {
        println(exception)
    }
    try {
        println("peek(10): ${peek(10U)}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("push(2, 100)")
    push(2U, 100)
    println(this)
    println("peek(2): ${peek(2U)}")
    println("isEmpty(1): ${isEmpty(1U)}")
    println("isEmpty(2): ${isEmpty(2U)}")
    println("pop(2): ${pop(2U)}")
    println("isEmpty(2): ${isEmpty(2U)}")
    repeat(sizes[3].minus(1U).toInt()) {
        val data = it * 100
        println("push(3, $data)")
        push(3U, data)
        println(this)
    }
    println("pop(3): ${pop(3U)}")
    println(this)
    repeat(sizes[4].toInt()) {
        val data = it * 100
        println("push(4, $data)")
        push(4U, data)
        println(this)
    }
    println("pop(4): ${pop(4U)}")
    println(this)
    repeat(sizes[5].plus(1U).toInt()) {
        try {
            val data = it * 100
            println("push(5, $data)")
            push(5U, data)
            println(this)
        } catch (exception: StackFullException) {
            println(exception)
        }
    }
}
