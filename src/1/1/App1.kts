/**
 * Question:
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 * additional data structures?
 *
 * Solution:
 * Using an additional data structure, and assuming the input is limited to a known charset.
 */

fun isUnique(string: String): Boolean {
    val array = Array(26) { false }
    string.forEach {
        if (array[it.code - 65]) return false
        array[it.code - 65] = true
    }
    return true
}

setOf("HELLO", "BYE").forEach { println("$it: ${isUnique(it)}") }
