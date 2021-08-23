/**
 * Question:
 * StringRotation: Assume you have a method isSubstring which checks if one word is a substring of another. Given two
 * strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g.,
 * "waterbottle" is a rotation of" erbottlewat").
 * <p>
 * Solution:
 * Rebuilding the string n times.
 */
public final class App1 {
    public static void main(final String[] args) {
        System.out.println(isRotation("a", "a"));
        System.out.println(isRotation("ab", "a"));
        System.out.println(isRotation("ab", "ba"));
        System.out.println(isRotation("waterbottle", "erbottlewat"));
        System.out.println(isRotation("waterbottle", "etrbottlewa"));
    }

    private static boolean isRotation(final String s1, final String s2) {
        if (s1.length() != s2.length()) return false;
        var string = s2;
        for (var index = 0; index < s1.length(); ++index) {
            string = string.charAt(string.length() - 1) + string.substring(0, string.length() - 1);
            if (s1.equals(string)) return true;
        }
        return false;
    }
}
