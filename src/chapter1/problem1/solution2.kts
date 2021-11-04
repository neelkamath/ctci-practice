/*
Question:
Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional
data structures?

Solution:
Using an additional data structure, and assuming the input isn't limited to a known charset.
 */

setOf("hello", "bye").forEach { println("$it: ${isUnique(it)}") }

fun isUnique(string: String): Boolean {
    val set = mutableSetOf<Char>()
    string.forEach {
        if (it in set) return false
        set.add(it)
    }
    return true
}
