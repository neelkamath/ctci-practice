import static java.util.Arrays.sort;

/**
 * Question:
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 * additional data structures?
 * <p>
 * Solution:
 * Without using additional data structures. Comparing neighboring characters after sorting the string.
 */
public final class App3 {
    public static void main(final String[] args) {
        for (final var string : new String[]{"abcad", "123"})
            System.out.printf("\"%s\": %s\n", string, isUnique(string) ? "unique" : "not unique");
    }

    private static boolean isUnique(final String string) {
        final var array = string.toCharArray();
        sort(array);
        for (var index = 1; index < array.length; ++index)
            if (array[index] == array[index - 1]) return false;
        return true;
    }
}
