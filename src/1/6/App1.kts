/**
 * Question:
 * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than
 * the original string, your method should return the original string. You can assume the string has only uppercase and
 * lowercase letters (a - z).
 *
 * Solution:
 * Using a [StringBuilder].
 */

fun compress(string: String): String {
    val builder = StringBuilder()
    var last = ' '
    var count = 0
    for (char in string)
        when (last) {
            ' ' -> {
                last = char
                ++count
            }
            char -> ++count
            else -> {
                builder.append(last, count)
                last = char
                count = 1
            }
        }
    builder.append(last, count)
    return if (builder.length < string.length) builder.toString() else string
}

setOf("aabcccccaaa", "a", "aa", "aaa", "ab", "baba", "bba").forEach { println("$it: ${compress(it)}") }
