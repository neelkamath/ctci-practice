/*
Question:
Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.

Solution:
Using a map.
 */

listOf("hello" to "hello", "hello" to "hleol", "hello" to "helo", "" to "bye", "b" to "by", "bye" to "eyb")
    .forEach { (string1, string2) -> println("$string1, $string2: ${arePermutations(string1, string2)}") }

fun arePermutations(string1: String, string2: String): Boolean {
    val map = mutableMapOf<Char, Int>()
    val (longer, shorter) = if (string1.length > string2.length) listOf(string1, string2) else listOf(string2, string1)
    for (index in longer.indices) {
        map[longer[index]] = map.getOrDefault(longer[index], 0) + 1
        if (index in shorter.indices) map[shorter[index]] = map.getOrDefault(shorter[index], 0) - 1
    }
    return map.values.all { it == 0 }
}
