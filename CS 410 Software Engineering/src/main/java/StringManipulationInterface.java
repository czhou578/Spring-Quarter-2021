/**
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 */
public interface StringManipulationInterface {

    /**
     * Returns the current string. If the string is null, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string.
     *
     * @param string The value to be set
     */
    void setString(String string);

    /**
     * Returns the number of words in the current string
     *
     * @return Number of words in the current string
     */
    int count();

    /**
     * Returns a string that consists of all characters in the original string except for the characters
     * in positions n, 2n, 3n, and so on, either deleting those or replacing them
     * with a white space. The characters in the resulting string should be in the same order
     * and with the same case as in the current string.
     *
     *
     * Examples:
     * - For n=2 and maintainSpacing=false, the method would return the string without the 2nd, 4th,
     *   6th, and so on characters in the string.
     * - For n=3 and maintainSpacing=true, the method would return the string with a space replacing
     *  the 3nd, 6th, 9th, and so on characters in the string.
     *
     * Values n and maintainSpacing are passed as parameters. The starting character is considered to be in Position 1.
     * Special cases expected behavior
     *
     * throws IndexOutOfBoundsException If n is greater than the string length.
     * throws IllegalArgumentException If "n" less than or equal to zero.
     *
     * @param n Determines the positions of the characters to be returned
     * @param maintainSpacing Determines whether replace the missing characters with a space, in order
     *                        to maintain the length of the original string.
     * @return String made of characters at positions other than n, 2n, and so on in the current string
     *
     *
     */
    String removeNthCharacter(int n, boolean maintainSpacing);

    /**
     * Returns the words from position "startWord" to position "endWord"
     * in the sentence, with 1 being the first Word in the String
     *
     * @param startWord
     *            Position of the first word to return
     * @param endWord
     *            Position of the last word to return
     * @return
     * String array of the words from position "startWord" to position "endWord"
     * Special cases
     * throws IllegalArgumentException
     *             If either "startWord" or "endWord" are invalid (i.e.,
     *             "startWord" <= 0, "endWord" <= 0, or "startWord"
     *             > "endWord")
     * throws IndexOutOfBoundsException
     *             If the string has less than "endWord" words in it
     */
    String[] getSubStrings(int startWord, int endWord);

    /**
     * Given a string s and an integer array indices of the same length.
     * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
     * Return the shuffled string.
     * example:
     * Input: string = "UnitTest", indices = [4,5,6,7,0,2,1,3]
     * Output: "TestUnit"
     * Explanation:
     * indices: 4 5 6 7 0 2 1 3
     * String:  U n i t T e s t
     * Actions to Shuffle: Shift U to 4th position, n to 5th position, i to 6th position ......
     * Output: T e s t U n i t
     *         0 1 2 3 4 5 6 7
     * As shown, "UnitTest" becomes "TestUnit" after shuffling.
     *
     * special cases and assumptions
     * s contains lower-case or upper-case English letters.
     * All values of indices are unique (i.e. indices is a permutation of the integers from 0 to n - 1).
     * indices length is the same as the string length.
     *
     * throws IllegalArgumentException if not s.length == indices.length == n
     * throws IndexOutOfBoundsException if   indices[i]< 0  or  indices[i]> string length
     *
     * @param indices is an integer array for shuffled string new indices positions
     * the character at the ith position moves to indices[i] in the shuffled string.
     *
     * @return Return the shuffled string.
     *
     * */
    String restoreString(int[] indices);

}
