import java.util.Arrays;

/**
 * Question:
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to
 * 0.
 * <p>
 * Solution:
 * Without using additional data structures.
 */
public final class App2 {
    public static void main(final String[] args) {
        test0x0Matrix();
        test3x3Matrix();
        test3x4Matrix();
    }

    private static void test0x0Matrix() {
        final var matrix = new int[][]{};
        System.out.println("0x0 matrix\nBefore:");
        display(matrix);
        zeroMatrix(matrix);
        System.out.println("After:");
        display(matrix);
    }

    private static void test3x3Matrix() {
        final var matrix = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9},
        };
        System.out.println("3x3 matrix\nBefore:");
        display(matrix);
        zeroMatrix(matrix);
        System.out.println("After:");
        display(matrix);
    }

    private static void test3x4Matrix() {
        final var matrix = new int[][]{
                new int[]{1, 2, 3, 0},
                new int[]{4, 4, 5, 6},
                new int[]{7, 0, 8, 9},
        };
        System.out.println("3x4 matrix\nBefore:");
        display(matrix);
        zeroMatrix(matrix);
        System.out.println("After:");
        display(matrix);
    }

    private static void display(final int[][] matrix) {
        for (final var row : matrix) System.out.println(Arrays.toString(row));
    }

    /**
     * @param matrix Assumed to be of the form MxN.
     */
    private static void zeroMatrix(final int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        var isZeroInFirstRow = false;
        var isZeroInFirstCol = false;
        for (var element : matrix[0])
            if (element == 0) {
                isZeroInFirstRow = true;
                break;
            }
        for (var index = 0; index < matrix.length; ++index)
            if (matrix[0][index] == 0) {
                isZeroInFirstCol = true;
                break;
            }
        for (var rowIndex = 1; rowIndex < matrix.length; ++rowIndex)
            for (var colIndex = 1; colIndex < matrix[0].length; ++colIndex)
                if (matrix[rowIndex][colIndex] == 0) {
                    matrix[0][colIndex] = 0;
                    matrix[rowIndex][0] = 0;
                }
        for (var rowIndex = 1; rowIndex < matrix.length; ++rowIndex)
            for (var colIndex = 1; colIndex < matrix[0].length; ++colIndex)
                if (matrix[0][colIndex] == 0 || matrix[rowIndex][0] == 0) matrix[rowIndex][colIndex] = 0;
        if (isZeroInFirstRow) Arrays.fill(matrix[0], 0);
        if (isZeroInFirstCol) for (var index = 0; index < matrix.length; ++index) matrix[index][0] = 0;
    }
}
