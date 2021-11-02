class MyStringBuilder(initialCapacity: UInt = 16U) {
    private var array: Array<Char?> = Array(initialCapacity.toInt()) { null }

    /** The number of characters this builder can store. */
    val capacity: UInt get() = array.size.toUInt()

    /** The number of characters currently stored. */
    var length: UInt = 0U
        private set

    /** Whether there aren't any characters stored. */
    val isEmpty: Boolean get() = length == 0U

    fun append(string: String): MyStringBuilder {
        prepareForEntry(string)
        System.arraycopy(string.toArray(), 0, array, length.toInt(), string.length)
        length += string.length.toUInt()
        return this
    }

    private fun String.toArray(): Array<Char> = Array(length) { this[it] }

    /** Throws an [IndexOutOfBoundsException] if the [index] is greater than the [length]. */
    fun charAt(index: UInt): Char = if (index >= length) throw IndexOutOfBoundsException() else array[index.toInt()]!!

    /**
     * Deletes the characters from [start] until [end].
     *
     * [end] may be greater than [length] or [capacity]. For example, deleting from the [String] `"amazing"` with
     * [start] as `3` and [end] as `5` will give `"amang"`.
     *
     * Throws an [IndexOutOfBoundsException] if the [start] is greater than [end], or not less than the [length].
     */
    fun delete(start: UInt, end: UInt): MyStringBuilder {
        if (start >= length || start > end) throw IndexOutOfBoundsException()
        val actualEnd = if (end < length) end else length
        val difference = actualEnd - start
        System.arraycopy(array, actualEnd.toInt(), array, start.toInt(), capacity.minus(actualEnd).toInt())
        array.fill(null, capacity.minus(difference).toInt(), capacity.toInt())
        length -= difference
        return this
    }

    /** Throws an [IndexOutOfBoundsException] if there's no element at the [index]. */
    fun deleteCharAt(index: UInt): MyStringBuilder = delete(index, index + 1U)

    /**
     * For example, if this array is ["a", "b"], inserting "z" at index 1 will make this array ["a", "z", "b"].
     *
     * Throws an [IndexOutOfBoundsException] if there's no element at the [index].
     */
    fun insert(index: UInt, string: String): MyStringBuilder {
        if (index >= length) throw IndexOutOfBoundsException()
        prepareForEntry(string)
        System.arraycopy(
            array,
            index.toInt(),
            array,
            index.toInt() + string.length,
            capacity.toInt() - index.toInt() - string.length,
        )
        System.arraycopy(string.toArray(), 0, array, index.toInt(), string.length)
        length += string.length.toUInt()
        return this
    }

    /** Resizes the [array] with double the [capacity] if inserting the [string] would cause it to overflow. */
    private fun prepareForEntry(string: String) {
        if (length + string.length.toUInt() > capacity) array = array.copyOf(capacity.toInt().times(2))
    }

    /** Returns a [String] that's the concatenation of each inputted [String]. */
    override fun toString(): String = array.fold("") { string, char -> if (char == null) string else string + char }
}

with(MyStringBuilder()) {
    println("capacity: $capacity")
    println("length: $length")
    println("isEmpty: $isEmpty")
    println(this)
    repeat(capacity.times(2U).toInt()) {
        println("""append("$it"): ${append(it.toString())}""")
        println("capacity: $capacity")
        println("length: $length")
    }
    println("charAt(5): ${charAt(5U)}")
    try {
        println("charAt(100): ${charAt(100U)}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    try {
        delete(start = 5U, end = 4U)
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    try {
        delete(start = 500U, end = 501U)
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("delete(5, 5): ${delete(start = 5U, end = 5U)}")
    println("delete(5, 6): ${delete(start = 5U, end = 6U)}")
    println("delete(5, 10): ${delete(start = 5U, end = 10U)}")
    println("delete(length - 3, length): ${delete(start = length - 3U, end = length)}")
    println("deleteCharAt(1): ${deleteCharAt(1U)}")
    println("length: $length")
    try {
        println("deleteCharAt(100): ${deleteCharAt(1U)}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("""insert(3, "hello"): ${insert(3U, "hello")}""")
    println("""insert(length - 1, "bye"): ${insert(length - 1U, "bye")}""")
    try {
        println("""insert(length, "bye"): ${insert(length, "bye")}""")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
}
