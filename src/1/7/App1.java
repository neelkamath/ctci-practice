import java.util.Arrays;

/**
 * Question:
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method
 * to rotate the image by 90 degrees. Can you do this in place?
 * <p>
 * Solution:
 * Modifying the array in place.
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
        for (final var row : rotate(matrix)) System.out.println(Arrays.toString(row));
    }

    private static String[][] rotate(final String[][] matrix) {
        final var newMatrix = new String[matrix.length][matrix.length];
        for (var rowIndex = 0; rowIndex < matrix.length; ++rowIndex)
            for (var colIndex = 0; colIndex < matrix.length; ++colIndex)
                newMatrix[colIndex][newMatrix.length - 1 - rowIndex] = matrix[rowIndex][colIndex];
        return newMatrix;
    }
}
