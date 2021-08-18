/**
 * Question:
 * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than
 * the original string, your method should return the original string. You can assume the string has only uppercase and
 * lowercase letters (a - z).
 * <p>
 * Solution:
 * Using {@link StringBuilder}.
 */
public final class App1 {
    public static void main(final String[] args) {
        for (final var string : new String[]{"a", "aa", "aaa", "aabcccccaaa"})
            System.out.printf("%s -> %s\n", string, compress(string));
    }

    private static String compress(String string) {
        var last = ' ';
        var count = 0;
        final var builder = new StringBuilder();
        for (var index = 0; index < string.length(); ++index) {
            final var character = string.charAt(index);
            if (character != last) {
                if (last != ' ') builder.append(count);
                builder.append(character);
                last = character;
                count = 1;
            } else ++count;
        }
        builder.append(count);
        final var compressed = builder.toString();
        return compressed.length() < string.length() ? compressed : string;
    }
}
