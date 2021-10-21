/**
 * Question:
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method
 * to rotate the image by 90 degrees. Can you do this in place?
 *
 * Solution:
 * Modifying in-place.
 */

/*
(1, 1) (1, 2) (1, 3)    (3, 1) (2, 1) (1, 1)
(2, 1) (2, 2) (2, 3) -> (3, 2) (2, 2) (1, 2)
(3, 1) (3, 2) (3, 3)    (3, 3) (2, 3) (1, 3)

(1, 1) (1, 2) (1, 3) (1, 4)    (4, 1) (3, 1) (2, 1) (1, 1)
(2, 1) (2, 2) (2, 3) (2, 4) -> (4, 2) (3, 2) (2, 2) (1, 2)
(3, 1) (3, 2) (3, 3) (3, 4)    (4, 3) (3, 3) (2, 3) (1, 3)
(4, 1) (4, 2) (4, 3) (4, 4)    (4, 4) (3, 4) (2, 4) (1, 4)

(1, 1) -> (1, 4)
(1, 2) -> (2, 4)
(1, 3) -> (3, 4)
(1, 4) -> (4, 4)

(2, 1) -> (1, 3)
(2, 2) -> (2, 3)
(2, 3) -> (3, 3)
(2, 4) -> (4, 3)
 */

typealias Point = Pair<Int, Int>

typealias Matrix = Array<Array<Point>>

fun rotate(matrix: Matrix) {
    for (rowIndex in 0 until matrix.size / 2) {
        val lastIndex = matrix.size - 1 - rowIndex
        for (colIndex in rowIndex until lastIndex) {
            val top = matrix[rowIndex][colIndex]
            val offset = colIndex - rowIndex
            // top = left
            matrix[rowIndex][colIndex] = matrix[lastIndex - offset][rowIndex]
            // left = bottom
            matrix[lastIndex - offset][rowIndex] = matrix[lastIndex][matrix.size - 1 - rowIndex - offset]
            // bottom = right
            matrix[lastIndex][matrix.size - 1 - rowIndex - offset] = matrix[colIndex][lastIndex]
            // right = top
            matrix[colIndex][lastIndex] = top
        }
    }
}

fun createMatrix(size: Int): Matrix = Array(size) { rowIndex ->
    Array(size) { colIndex -> rowIndex + 1 to colIndex + 1 }
}

fun Matrix.display(): Unit = forEach { println(it.contentToString()) }

(1..7).forEach {
    println("$it x $it\n-----")
    val matrix = createMatrix(it)
    println("Input:")
    matrix.display()
    rotate(matrix)
    println("Output:")
    matrix.display()
}
