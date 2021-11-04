/*
Question:
Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional
data structures?

Solution:
Not using an additional data structure.
 */

setOf("hello", "bye").forEach { println("$it: ${it.isUnique()}") }

fun String.isUnique(): Boolean {
    val list = toList().sorted()
    for (index in 1 until list.size) if (list[index] == list[index - 1]) return false
    return true
}
