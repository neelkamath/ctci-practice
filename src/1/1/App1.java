/**
 * Question:
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 * additional data structures?
 * <p>
 * Solution:
 * Using additional data structures.
 */
public final class App1 {
    public static void main(final String[] args) {
        for (final var string : new String[]{"abcad", "123"})
            System.out.printf("\"%s\": %s\n", string, isUnique(string) ? "unique" : "not unique");
    }

    private static boolean isUnique(final String string) {
        final var array = new boolean[128];
        for (var index = 0; index < string.length(); ++index) {
            if (array[string.charAt(index)]) return false;
            array[string.charAt(index)] = true;
        }
        return true;
    }
}
