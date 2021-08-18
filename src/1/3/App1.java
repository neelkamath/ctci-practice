/**
 * Question:
 * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient
 * space at the end to hold the additional characters, and that you are given the "true" length of the string. (Note: if
 * implementing in Java, please use a character array so that you can perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith ", 13
 * Output: "Mr%20John%20Smith"
 * <p>
 * Solution:
 * Creating a new string.
 */
public final class App1 {
    public static void main(final String[] args) {
        System.out.println(urlify("Mr John Smith    "));
    }

    private static char[] urlify(final String string) {
        final var url = new char[string.length()];
        for (int stringIndex = 0, urlIndex = 0; urlIndex < string.length(); ++stringIndex, ++urlIndex) {
            if (string.charAt(stringIndex) == ' ') {
                url[urlIndex] = '%';
                url[++urlIndex] = '2';
                url[++urlIndex] = '0';
            } else url[urlIndex] = string.charAt(stringIndex);
        }
        return url;
    }
}
