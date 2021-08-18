import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.sort;

/**
 * Question:
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 *
 * Solution:
 * Using sort.
 */
public final class App1 {
    public static void main(final String[] args) {
        final var map = new HashMap<String, String>();
        map.put("abcad", "abcd");
        map.put("123", "231");
        map.put("aba", "abc");
        for (final var entry : map.entrySet()) {
            final var result = arePermutations(entry.getKey(), entry.getValue()) ? "permutations" : "not permutations";
            System.out.printf("\"%s\" and \"%s\" are %s.\n", entry.getKey(), entry.getValue(), result);
        }
    }

    private static boolean arePermutations(final String string1, final String string2) {
        if (string1.length() != string2.length()) return false;
        final var string1Copy = string1.toCharArray();
        final var string2Copy = string2.toCharArray();
        sort(string1Copy);
        sort(string2Copy);
        return Arrays.equals(string1Copy, string2Copy);
    }
}
