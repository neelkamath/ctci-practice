package com.neelkamath.ctci.chapter1.problem5

import kotlin.math.abs

/*
Question:
One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or
replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
EXAMPLE
pale, ple -> true
pales, pale -> true
pale, bale -> true
pale, bae -> false

Solution:
Using no additional data structures.
 */

listOf("pale" to "ple", "pales" to "pale", "pale" to "bale", "pale" to "bae")
    .forEach { (string1, string2) -> println("$string1, $string2: ${isAtMostOneEditAway(string1, string2)}") }

fun isAtMostOneEditAway(string1: String, string2: String): Boolean = when (abs(string1.length - string2.length)) {
    0 -> isAtMostOneReplacementAway(string1, string2)
    1 -> isAtMostOneInsertionAway(string1, string2)
    else -> false
}

fun isAtMostOneInsertionAway(string1: String, string2: String): Boolean {
    val (longer, shorter) = if (string1.length > string2.length) listOf(string1, string2) else listOf(string2, string1)
    var longerIndex = 0
    var shorterIndex = 0
    while (longerIndex in longer.indices && shorterIndex in shorter.indices)
        if (longer[longerIndex] == shorter[shorterIndex]) {
            ++longerIndex
            ++shorterIndex
        } else if (longerIndex++ != shorterIndex) return false
    return true
}

fun isAtMostOneReplacementAway(string1: String, string2: String): Boolean {
    var hasBeenEdited = false
    for (index in string1.indices)
        if (string1[index] != string2[index]) {
            if (hasBeenEdited) return false
            hasBeenEdited = true
        }
    return true
}
