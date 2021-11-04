/*
Question:
StringRotation: Assume you have a method isSubstring which checks if one word is a substring of another. Given two
strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g.,
"waterbottle" is a rotation of" erbottlewat").

Solution:
Checking substrings.
 */

setOf("hi" to "ih", "hello" to "hello", "hello" to "llohe", "hello" to "elolh", "waterbottle" to "erbottlewat")
    .forEach { (string1, string2) -> println("$string1, $string2: ${isSubstring(string1, string2)}") }

fun isSubstring(string1: String, string2: String): Boolean =
    string2.length == string1.length && string2 in string1 + string1
