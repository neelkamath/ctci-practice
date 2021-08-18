import java.util.HashMap;

/**
 * Question:
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 *
 * Solution:
 * Using {@link HashMap}.
 */
public final class App2 {
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
        final var map = new HashMap<Character, Integer>();
        for (var index = 0; index < string1.length(); ++index) {
            final var character1 = string1.charAt(index);
            final var value1 = map.getOrDefault(character1, 0) + 1;
            map.put(character1, value1);
            final var character2 = string2.charAt(index);
            final var value2 = map.getOrDefault(character2, 0) - 1;
            map.put(character2, value2);
        }
        return map.entrySet().stream().allMatch(entry -> entry.getValue() == 0);
    }
}
