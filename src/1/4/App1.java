import java.util.HashMap;

/**
 * Question:
 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A
 * palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations: "taco cat", "atco cta", etc.)
 * <p>
 * Solution:
 * Using a {@link HashMap}.
 */
public final class App1 {
    public static void main(final String[] args) {
        for (final var string : new String[]{"Tact Coa", "TcCa"})
            System.out.printf("%s: %b\n", string, isPalindromePermutation(string));
    }

    private static boolean isPalindromePermutation(final String string) {
        final var input = string.toLowerCase().replace(" ", "");
        final var map = new HashMap<Character, Boolean>();
        for (var index = 0; index < input.length(); ++index) {
            final var character = input.charAt(index);
            map.put(character, !map.getOrDefault(character, false));
        }
        return map.values().stream().filter(value -> value).count() == input.length() % 2;
    }
}
