import HashMap.Bucket
import java.util.*
import kotlin.math.abs

private typealias Bucket<K, V> = LinkedList<Entry<K, V>>

private data class Entry<K, V>(val key: K, var value: V)

class MyHashMap<K, V>(initialCapacity: UInt = 16U, val loadFactor: Float = 0.75F) {
    /** The number of buckets this map currently contains. */
    var capacity: UInt = initialCapacity
        private set

    /** The underlying data structure. */
    private var buckets: Array<Bucket<K, V>> = Array(capacity.toInt()) { Bucket() }

    /** The number of entries. */
    var size: UInt = 0U
        private set

    /** Whether there are no entries. */
    val isEmpty: Boolean get() = size == 0U

    /** Returns either the [key]'s associated value, or `null` if there's no such [key]. */
    operator fun get(key: K): V? = readEntry(key)?.value

    fun containsKey(key: K): Boolean = this[key] != null

    operator fun set(key: K, value: V) {
        prepareForEntry()
        buckets[computeIndex(key).toInt()].add(Entry(key, value))
    }

    /**
     * Increments the [size], and checks if it's greater than the product of the [capacity] and [loadFactor]. If it is,
     * then the [capacity] is doubled, and the [buckets] are reassigned accordingly. Otherwise, there's no rehashing.
     */
    private fun prepareForEntry() {
        if (size.inc().toFloat() <= capacity.toInt() * loadFactor) return
        capacity *= 2U
        val newBuckets = Array(capacity.toInt()) { Bucket<K, V>() }
        for (bucket in buckets) bucket.forEach { newBuckets[computeIndex(it.key).toInt()].add(it) }
        buckets = newBuckets
    }

    /** Throws an [IllegalArgumentException] if the [key] doesn't exist in this map. */
    fun remove(key: K) {
        if (!containsKey(key)) throw IllegalArgumentException()
        buckets[computeIndex(key).toInt()].removeIf { it.key == key }
        --size
    }

    /** Deletes every entry. */
    fun clear() {
        buckets.forEach { it.clear() }
        size = 0U
    }

    fun containsValue(value: V): Boolean = buckets.any { bucket ->
        bucket.any { it.value == value }
    }

    /**
     * Maps the [key] to the [value] if the [key] exists, and does nothing otherwise. Returns whether the [value] was
     * replaced.
     */
    fun replace(key: K, value: V): Boolean = readEntry(key)?.let { it.value = value } != null

    /** Returns the index the [key] must be in in [buckets]. */
    private fun computeIndex(key: K): UInt = abs(key.hashCode()).mod(capacity.toInt()).toUInt()

    /** Returns the [key]'s entry if it exists, or `null` if it doesn't. */
    private fun readEntry(key: K): Entry<K, V>? = buckets[computeIndex(key).toInt()].firstOrNull { it.key == key }

    /**
     * If the [key] already exists, then nothing will happen, and the existing value it's mapped to will be returned.
     * Otherwise, the [key]-[value] pair will be entered into this map, and `null` will be returned.
     */
    fun putIfAbsent(key: K, value: V): V? {
        val existingValue = readEntry(key)?.value
        return if (existingValue == null) {
            prepareForEntry()
            buckets[computeIndex(key).toInt()].add(Entry(key, value))
            null
        } else existingValue
    }

    override fun toString(): String = with(StringBuilder("{")) {
        for (bucket in buckets)
            for ((key, value) in bucket) {
                if (length > 1) append(", ")
                append("$key=$value")
            }
        append("}").toString()
    }
}

println("***MyHashMap()***")
val namesToAges = MyHashMap<String, UInt>()
with(namesToAges) {
    println(
        """
        toString(): ${toString()}
        loadFactor: $loadFactor
        capacity: $capacity
        size: $size
        isEmpty: $isEmpty
        """.trimIndent()
    )
}

println("***set()***")
namesToAges["Neel Kamath"] = 21U
println("toString(): ${namesToAges.toString()}")
namesToAges["Archana Kamath"] = 47U
namesToAges["Rahul Kamath"] = 18U
println("toString(): ${namesToAges.toString()}")
namesToAges["Neel Kamath"] = 22U
println("toString(): ${namesToAges.toString()}")

println("***get()***")
println("""get("Neel Kamath"): ${namesToAges["Neel Kamath"]}""")
println("""get("Pavan"): ${namesToAges["Pavan"]}""")

println("***containsKey()***")
println("""containsKey("Neel Kamath"): ${namesToAges.containsKey("Neel Kamath")}""")
println("""containsKey("Arjun"): ${namesToAges.containsKey("Arjun")}""")

println("***containsValue()***")
println("containsValue(21): ${namesToAges.containsValue(21U)}")
println("containsValue(99): ${namesToAges.containsValue(99U)}")
println("containsValue(47): ${namesToAges.containsValue(47U)}")
println("containsValue(18): ${namesToAges.containsValue(18U)}")

println("***remove()***")
namesToAges.remove("Neel Kamath")
println("toString(): ${namesToAges.toString()}")
try {
    namesToAges.remove("Neel Kamath")
} catch (exception: IllegalArgumentException) {
    println(exception)
}

println("***replace()***")
namesToAges["Neel Kamath"] = 21U
println("toString(): ${namesToAges.toString()}")
println("""replace("Neel Kamath", 25): ${namesToAges.replace("Neel Kamath", 25U)}""")
println("""replace("Ashray", 25): ${namesToAges.replace("Ashray", 25U)}""")
println("toString(): ${namesToAges.toString()}")

println("***putIfAbsent()***")
println("""putIfAbset("Neel Kamath", 24): ${namesToAges.putIfAbsent("Neel Kamath", 24U)}""")
println("""putIfAbset("Pranav", 14): ${namesToAges.putIfAbsent("Pranav", 14U)}""")
println("toString(): ${namesToAges.toString()}")

println("***prepareForEntry()***")
with(namesToAges) {
    repeat(capacity.toInt()) {
        set("Name $it", it.toUInt())
        println("toString(): ${toString()}\nsize: $size\ncapacity: $capacity")
    }
}
println("***clear()***")
namesToAges.clear()
println("toString(): ${namesToAges.toString()}")
