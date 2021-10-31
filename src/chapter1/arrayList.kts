import kotlin.collections.ArrayList

// TODO: clear(), toString()
class MyArrayList<T>(initialCapacity: UInt = 10U) {
    /** The number of elements that can be currently stored. */
    var capacity: UInt = initialCapacity
        private set

    /** The number of elements currently stored. */
    var size: UInt = 0
        private set

    /** Whether there are no elements in this array. */
    val isEmpty: Boolean get() = size == 0U

    /** The underlying data structure. */
    private var array: Array<T?> = Array(capacity) { null }

    operator fun contains(element: T): Boolean = element in array

    /** Returns the index of the [element], or `-1` if it doesn't exist. */
    fun indexOf(element: T): Boolean = array.indexOf(element)

    /** Throws an [IndexOutOfBoundsException] if there's no element at the [index]. */
    operator fun get(index: UInt): T =
        if (index >= size) throw IndexOutOfBoundsException() else array.elementAt(index.toInt())

    /** Throws an [IndexOutOfBoundsException] if there's no element at the [index]. */
    operator fun set(index: UInt, element: T) {
        if (index >= size) throw IndexOutOfBoundsException()
        array[index] = element
    }

    /** Appends the [element]. If this array is full, it'll double its size, and then add it. */
    fun add(element: T) {
        if (size == capacity) {
            capacity *= 2
            array = array.copyOf(capacity)
        }
        array[size++] = element
    }

    /** Either removes the element at the [index] if there is one or throws an [IndexOutOfBoundsException]. */
    fun remove(index: UInt) {
        if (index >= size) throw IndexOutOfBoundsException()
        
        --size
    }
}
