/**
 * Question:
 * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient
 * space at the end to hold the additional characters, and that you are given the "true" length of the string. (Note: if
 * implementing in Java, please use a character array so that you can perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 *
 * Solution:
 * Modifying in-place.
 */

fun urlify(array: CharArray, trueLength: Int) {
    var replacementIndex = array.size - 1
    for (index in trueLength - 1 downTo 0)
        if (array[index] == ' ') {
            array[replacementIndex--] = '0'
            array[replacementIndex--] = '2'
            array[replacementIndex--] = '%'
        } else array[replacementIndex--] = array[index]
}

val array = "Mr John Smith    ".toCharArray()
urlify(array, trueLength = 13)
println(array)
