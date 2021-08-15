/**
 * Question:
 *     Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use
 *     additional data structures?
 * Solution:
 *     Without using additional data structures. Comparing each character to each other character.
 */
public final class App2 {
    public static void main(String[] args) {
        for (final var string : new String[]{"abcad", "123"})
            System.out.printf("\"%s\": %s\n", string, isUnique(string) ? "unique" : "not unique");
    }

    private static boolean isUnique(String string) {
        for (var currentIndex = 1; currentIndex < string.length(); ++currentIndex)
            for (var index = 0; index < currentIndex; ++index)
                if (string.charAt(index) == string.charAt(currentIndex)) return false;
        return true;
    }
}
