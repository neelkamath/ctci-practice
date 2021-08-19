import java.util.Arrays;

/**
 * Question:
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method
 * to rotate the image by 90 degrees. Can you do this in place?
 * <p>
 * Solution:
 * Modifying the matrix in place.
 */
public final class App1 {
    public static void main(final String[] args) {
        test(1);
        System.out.println();
        test(2);
        System.out.println();
        test(3);
        System.out.println();
        test(4);
        System.out.println();
        test(5);
    }

    private static void test(final int length) {
        final var matrix = new String[length][length];
        for (var rowIndex = 0; rowIndex < length; ++rowIndex)
            for (var colIndex = 0; colIndex < length; ++colIndex)
                matrix[rowIndex][colIndex] = String.format("(%d, %d)", rowIndex, colIndex);
        rotate(matrix);
        for (final var row : matrix) System.out.println(Arrays.toString(row));
    }

    private static void rotate(final String[][] matrix) {
        if (matrix.length > 0 && matrix.length != matrix[0].length)
            throw new IllegalArgumentException("The length and breadth of the matrix must be the same.");
        for (var rowIndex = 0; rowIndex < matrix.length / 2; ++rowIndex)
            for (var colIndex = 0; colIndex < matrix.length - rowIndex; ++colIndex) {
                final var top = matrix[rowIndex][matrix.length - 1 - rowIndex];
                // left -> top
                matrix[rowIndex][matrix.length - 1 - rowIndex] = matrix[rowIndex][rowIndex];
                // bottom -> left
                matrix[rowIndex][rowIndex] = matrix[matrix.length - 1 - rowIndex][colIndex];
                // right -> bottom
                matrix[matrix.length - 1 - rowIndex][matrix.length - 1 - rowIndex] =
                        matrix[rowIndex][matrix.length - 1 - rowIndex];
                // top -> right
                matrix[colIndex][matrix.length - 1 - rowIndex] = top;
            }
    }
}
