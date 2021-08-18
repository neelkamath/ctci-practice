import static java.lang.Math.abs;

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
 * Using a counter.
 */
public final class App2 {
    public static void main(final String[] args) {
        final var pairs = new Pair[]{
                new Pair("pale", "ple"),
                new Pair("pales", "pale"),
                new Pair("pale", "bale"),
                new Pair("pale", "bae"),
                new Pair("abc", "ab"),
                new Pair("abcd", "ab"),
                new Pair("ba", "ab"),
        };
        for (final var pair : pairs) System.out.printf("%s, %s -> %b\n", pair.longer, pair.shorter, isOneAway(pair));
    }

    private static boolean isOneAway(Pair pair) {
        if (abs(pair.longer.length() - pair.shorter.length()) > 1) return false;
        var hasDifference = false;
        var longerIndex = 0;
        var shorterIndex = 0;
        while (shorterIndex < pair.shorter.length()) {
            if (pair.longer.charAt(longerIndex) == pair.shorter.charAt(shorterIndex)) ++shorterIndex;
            else {
                if (hasDifference) return false;
                hasDifference = true;
                if (pair.longer.length() == pair.shorter.length()) ++shorterIndex;
            }
            ++longerIndex;
        }
        return true;
    }

    private static final record Pair(String longer, String shorter) {
        Pair {
            if (longer.length() < shorter.length())
                throw new IllegalArgumentException("The longer string must be passed as the first argument.");
        }
    }
}
