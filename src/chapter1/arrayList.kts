import java.util.Arrays

class MyArrayList<T>(initialCapacity: UInt = 10U) {
    /** The number of elements that can be currently stored. */
    var capacity: UInt = initialCapacity
        private set

    /** The number of elements currently stored. */
    var size: UInt = 0U
        private set

    /** Whether there are no elements in this array. */
    val isEmpty: Boolean get() = size == 0U

    /** The underlying data structure. */
    private var array: Array<Any?> = Array(capacity.toInt()) { null }

    operator fun contains(element: T): Boolean = element in array

    /** Returns the index of the [element], or `-1` if it doesn't exist. */
    fun indexOf(element: T): Int = array.indexOf(element)

    /** Throws an [IndexOutOfBoundsException] if there's no element at the [index]. */
    operator fun get(index: UInt): T = array.elementAtOrNull(index.toInt()) as T ?: throw IndexOutOfBoundsException()

    /** Throws an [IndexOutOfBoundsException] if there's no element at the [index]. */
    operator fun set(index: UInt, element: T) {
        if (index >= size) throw IndexOutOfBoundsException()
        array[index.toInt()] = element
    }

    /** Appends the [element]. If this array is full, it'll double its size, and then add it. */
    fun add(element: T) {
        if (size == capacity) {
            capacity *= 2U
            array = array.copyOf(capacity.toInt())
        }
        array[size.toInt()] = element
        ++size
    }

    /** Either removes the element at the [index] if there is one or throws an [IndexOutOfBoundsException]. */
    fun remove(index: UInt) {
        if (index >= size) throw IndexOutOfBoundsException()
        if (index.toInt() == capacity.toInt() - 1) array[index.toInt()] = null
        else System.arraycopy(array, index.toInt().inc(), array, index.toInt(), capacity.minus(index + 1U).toInt())
        --size
    }

    /** Deletes every entry. */
    fun clear() {
        array.fill(null)
        size = 0U
    }

    override fun toString(): String = Arrays.toString(array)
}

with(MyArrayList<Int>()) {
    println("***MyArrayList()***")
    println("capacity: $capacity")
    println("size: $size")
    println("isEmpty: $isEmpty")
    println("***add()***")
    repeat(capacity.toInt().times(2).minus(3)) {
        add(-it)
        println(this)
    }
    println("***contains()***")
    println("-3: ${-3 in this}")
    println("3: ${3 in this}")
    println("***indexOf()***")
    println("-4: ${indexOf(-4)}")
    println("4: ${indexOf(4)}")
    println("***get()***")
    println("6: ${this[6U]}")
    try {
        println("18: ${this[18U]}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("***set()***")
    println("Index 10 before set(): ${this[10U]}")
    this[10U] = 700
    println("Index 10 after set(): ${this[10U]}")
    try {
        println("Index 18 before set(): ${this[18U]}")
        this[18U] = 9_000
        println("Index 18 after set(): ${this[18U]}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("***remove()***")
    println("Before removing index 10: ${toString()}")
    println("size: $size")
    remove(10U)
    println("After removing index 10: ${toString()}")
    println("size: $size")
    try {
        println("Before removing index 18: ${toString()}")
        remove(18U)
        println("After removing index 18: ${toString()}")
    } catch (exception: IndexOutOfBoundsException) {
        println(exception)
    }
    println("***clear()***")
    clear()
    println(this)
}
