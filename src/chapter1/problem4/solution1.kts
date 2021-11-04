/*
Question:
Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome
is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome
does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat", "atco cta", etc.)

Solution:
Using a set.
 */

setOf("tact coa", "neel", "neeln").forEach { println("$it: ${isPermutationOfPalindrome(it)}") }

fun isPermutationOfPalindrome(string: String): Boolean {
    val set = mutableSetOf<Char>()
    val input = string.filter { it != ' ' }
    input.forEach { if (it in set) set.remove(it) else set.add(it) }
    return set.size == input.length % 2
}
