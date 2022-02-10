import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StringManipulationTest {

    private StringManipulationInterface manipulatedstring;

    @BeforeEach
    public void setUp() {
        manipulatedstring = new StringManipulation();
    }

    @AfterEach
    public void tearDown() {
        manipulatedstring = null;
    }

    @Test
    public void testCount1() { //given in project skeleton
        manipulatedstring.setString("This is my string");
        int length = manipulatedstring.count();
        assertEquals(4, length);
    }

    @Test
    public void testCount2() { //tests for the empty string case
    	manipulatedstring.setString("			");
    	int length = manipulatedstring.count();
    	assertEquals(0, length);
    }

    @Test
    public void testCount3() { //tests for a mix of special characters, that do not count as words
    	manipulatedstring.setString("7*^%$# abcd /()!@");
    	int length = manipulatedstring.count();
    	assertEquals(1, length);
    }

    @Test
    public void testCount4() { //tests if method works for string that includes escape characters and punctuation
    	manipulatedstring.setString("See, it's works!?! \n");
    	int length = manipulatedstring.count();
    	assertEquals(3, length);
    }

    @Test
    public void testRemoveNthCharacter1() { //given in project skeleton
        manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("I' bttr uts0e 16tsinths trn6 rgh?", manipulatedstring.removeNthCharacter(3, false));
    }

    @Test
    public void testRemoveNthCharacter2() { //given in project skeleton
        manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("I'  b tt r  ut s0 e  16 ts in th s  tr n6  r gh ?", manipulatedstring.removeNthCharacter(3, true));
    }

    @Test
    public void testRemoveNthCharacter3() { //tests for illegalArugment exception if number passed in is 0
        manipulatedstring.setString("Not bad eh? ");
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.removeNthCharacter(0, true));
    }

    @Test
    public void testRemoveNthCharacter4() { //tests for exception if number passed in is larger then string length
        manipulatedstring.setString("");
        assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.removeNthCharacter(2, false));
    }

    @Test
    public void testRemoveNthCharacter5() { // tests for correct string if original string contains escape characters.
        manipulatedstring.setString("1234\n\n");
        assertEquals("12 4\n ", manipulatedstring.removeNthCharacter(3, true));

    }
    
    @Test
    public void testRemoveNthCharacter6() { // tests for correct string if original string contains "*" or special characters
        manipulatedstring.setString("8*2*1*lmasd*3");
        assertEquals("821lad3", manipulatedstring.removeNthCharacter(2, false));
    }
    
    @Test
    public void testRemoveNthCharacter7() { // tests for correct string if string passed in is only escape sequence characters
        manipulatedstring.setString("\n_()1@~*&%*");
        assertEquals("\n ( 1 ~ & *", manipulatedstring.removeNthCharacter(2, true));

    }
    
    @Test
    public void testGeSubStrings1() { //given in skeleton
        manipulatedstring.setString("This is my string");
        String [] sStings = manipulatedstring.getSubStrings(3, 4);

        assertEquals(sStings[0], "my");
        assertEquals(sStings[1], "string");
    }
    
    @Test
    public void testGeSubStrings2() { //test for exception if end index passed in is negative
        manipulatedstring.setString("String");
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(3, -1));
    	
    }
    
    @Test
    public void testGeSubStrings3() { // test for exception if start index is greater then end index.
        manipulatedstring.setString(" ");
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(1, 0));
    }
    
    @Test
    public void testGeSubStrings4() { // test for exception if start index passed in is negative
        manipulatedstring.setString("First Number Negative");
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.getSubStrings(-1, 3)); 
    }
    
    @Test
    public void testGeSubStrings5() { // test for correct array for string that contains escape characters
    	manipulatedstring.setString("\n\n Hello \t \n");
    	String [] sStings = manipulatedstring.getSubStrings(2, 4);
    	
    	assertEquals(sStings[0], "Hello");
    	assertEquals(sStings[1], null);
    	assertEquals(sStings[2], null);
    	
    }   
    
    @Test
    public void testGeSubStrings6() { //test for words that only contain letters, and not letters/numbers combination
        manipulatedstring.setString("7*y*a*j*ab card's123, biology ain't fun!");
        String [] sStings = manipulatedstring.getSubStrings(3, 5);
        assertEquals(sStings[0], "biology");
        assertEquals(sStings[1], "ain't");
        assertEquals(sStings[2], "fun!");

    }
    
    @Test
    public void testGeSubStrings7() { //test for index out of bounds exception for endword index.
        manipulatedstring.setString("colin zhou");
        assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.getSubStrings(1, 3)); 
    }
    
    @Test
    public void testRestoreString1() //given in skeleton
    {
        manipulatedstring.setString("art");
        int [] array;
        array=new int[]{1,0,2};
        String restoreString = manipulatedstring.restoreString(array);
        assertEquals(restoreString, "rat");
    }
    
    @Test
    public void testRestoreString2() //test for exception when new array length is not equal to string
    {
        manipulatedstring.setString("artists");
        int [] array;
        array=new int[]{3,2,1};
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.restoreString(array));

    }

    @Test
    public void testRestoreString3() //test for longer string then given in skeleton
    {
        manipulatedstring.setString("artists");
        int [] array;
        array=new int[]{3, 2, 1, 4, 6, 5, 0};
        String restoreString = manipulatedstring.restoreString(array);
        assertEquals(restoreString, "itrssta");

    }

    @Test
    public void testRestoreString4() //test for exception if str length is not equal to the new array length
    {
        manipulatedstring.setString("this won't work");
        int [] array;
        array=new int[]{3, 2, 1, 1, 6, 5, 0};
        assertThrows(IllegalArgumentException.class, () -> manipulatedstring.restoreString(array));
    }

    @Test
    public void testRestoreString5() //test for string with special characters and escape sequence. 
    {
        manipulatedstring.setString("th*[\n]is");
        int [] array;
        array=new int[]{3, 2, 1, 1, 6, 5, 0, 7};
        String restoreString = manipulatedstring.restoreString(array);
        assertEquals(restoreString, "[*hhi]ts");
    	
    }
    
    @Test
    public void testRestoreString6() //test for string with a negative indice value 
    {
        manipulatedstring.setString("th*[\n]is");
        int [] array;
        array=new int[]{3, 2, 1, -2, 6, 5, 0, 7};
        assertThrows(IndexOutOfBoundsException.class, () -> manipulatedstring.restoreString(array));

    	
    }

}
