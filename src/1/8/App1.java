import java.util.Arrays;

/**
 * Question:
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to
 * 0.
 * <p>
 * Solution:
 * Using additional data structures.
 */
public final class App1 {
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
        if (matrix.length == 0) return;
        final var rows = new boolean[matrix.length];
        final var cols = new boolean[matrix[0].length];
        for (var rowIndex = 0; rowIndex < matrix.length; ++rowIndex)
            for (var colIndex = 0; colIndex < matrix[0].length; ++colIndex)
                if (matrix[rowIndex][colIndex] == 0) {
                    rows[rowIndex] = true;
                    cols[colIndex] = true;
                }
        for (var rowIndex = 0; rowIndex < matrix.length; ++rowIndex)
            for (var colIndex = 0; colIndex < matrix[0].length; ++colIndex)
                if (rows[rowIndex] || cols[colIndex]) matrix[rowIndex][colIndex] = 0;
    }
}
