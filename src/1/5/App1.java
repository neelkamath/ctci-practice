import java.util.HashMap;

/**
 * Question:
 * One Away: There are three types of edits that can be performed on strings: insert a character, remove a character, or
 * replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bae -> false
 * <p>
 * Solution:
 * Using a {@link HashMap}.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var pairs = new Pair[]{
                new Pair("pale", "ple"),
                new Pair("pales", "pale"),
                new Pair("pale", "bale"),
                new Pair("pale", "bae"),
        };
        for (final var pair : pairs) System.out.printf("%s, %s -> %b\n", pair.string1, pair.string2, isOneAway(pair));
    }

    private static boolean isOneAway(Pair pair) {
        final var map = new HashMap<Character, Integer>();
        for (var index = 0; index < pair.string1.length() || index < pair.string2.length(); ++index) {
            if (index < pair.string1.length()) {
                final var character = pair.string1.charAt(index);
                map.put(character, map.getOrDefault(character, 0) + 1);
            }
            if (index < pair.string2.length()) {
                final var character = pair.string2.charAt(index);
                map.put(character, map.getOrDefault(character, 0) - 1);
            }
        }
        var insertions = 0;
        var deletions = 0;
        for (final var value : map.values())
            if (value == -1) ++deletions;
            else if (value == 1) ++insertions;
            else if (value != 0) return false;
        return insertions <= 1 && deletions <= 1;
    }

    private static final record Pair(String string1, String string2) {
    }
}
