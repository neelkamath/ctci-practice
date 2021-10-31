/**
 * Question:
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to
 * 0.
 *
 * Solution:
 * Modifying in-place.
 */

val matrix1 = arrayOf(
    arrayOf(0, 1, 2, 3, 4),
    arrayOf(5, 6, 7, 8, 9),
    arrayOf(10, 11, 12, 0, 14),
    arrayOf(15, 16, 17, 18, 19),
    arrayOf(20, 21, 22, 23, 0),
)
val matrix2 = arrayOf(
    arrayOf(1, 2, 3, 4, 5),
    arrayOf(6, 7, 0, 9, 10),
    arrayOf(11, 12, 13, 0, 15),
    arrayOf(16, 17, 18, 19, 20)
)
setOf(matrix1, matrix2).forEachIndexed { index, matrix ->
    println("Matrix ${index + 1}\nOriginal:")
    matrix.display()
    zeroMatrix(matrix)
    println("Modified:")
    matrix.display()
}

typealias Matrix = Array<Array<Int>>

fun zeroMatrix(matrix: Matrix) {
    val hasZeroInFirstRow = 0 in matrix[0]
    val hasZeroInFirstCol = matrix.any { it[0] == 0 }
    for (rowIndex in 1 until matrix.size)
        for (colIndex in 1 until matrix[0].size)
            if (matrix[rowIndex][colIndex] == 0) {
                matrix[0][colIndex] = 0
                matrix[rowIndex][0] = 0
            }
    for (rowIndex in 1 until matrix.size)
        for (colIndex in 1 until matrix[0].size)
            if (matrix[rowIndex][0] == 0 || matrix[0][colIndex] == 0) matrix[rowIndex][colIndex] = 0
    if (hasZeroInFirstRow) matrix[0] = Array(matrix[0].size) { 0 }
    if (hasZeroInFirstCol) matrix.forEach { it[0] = 0 }
}

fun Matrix.display(): Unit = forEach { println(it.contentToString()) }
