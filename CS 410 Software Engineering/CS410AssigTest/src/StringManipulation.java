import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StringManipulation implements StringManipulationInterface {
	private String str;
	
	
    @Override
    public String getString() {
        return str;
    }

    @Override
    public void setString(String string) {
    	str = string;
    }

    @Override
    public int count() {
    	int numWords = 0;
    	String[] temp = str.split(" ");
    	for (String word : temp) {
    		if (word.matches("[a-zA-Z!,'?]+")) {
    			numWords++;
    		}
    	}
    	
        return numWords;
    }

    @Override
    public String removeNthCharacter(int n, boolean maintainSpacing) {
    	if (n <= 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (n > str.length()) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	
        String result = "";
    	if (maintainSpacing == false) { //will not replace with space
            for (int i = 0; i < str.length(); i++) {
                int positionIndex = i + 1; //add one to index, since position starts at 1
                
                if (positionIndex % n == 0) {
                    continue;
                }

                result += str.charAt(i);

            }
    	} else {
            for (int i = 0; i < str.length(); i++) { //replace with string
                int positionIndex = i + 1;
                if (positionIndex % n == 0) {
                    result += " ";
                    continue;
                }
                result += str.charAt(i);
            }
        }
        return result;
    }

    @Override
    public String[] getSubStrings(int startWord, int endWord) {
    	if (endWord <= 0 || startWord <= 0 || startWord > endWord) {
    		throw new IllegalArgumentException();
    	}
    	
        String[] array = str.split(" ");
        
        if (array.length < endWord) {
        	throw new IndexOutOfBoundsException();
        }
        
        String[] result = new String[array.length];
        int isResultFilled = 0;
        
        for (int i = 0; i < array.length; i++) {
        	int positionIndex = i + 1;
        		if (positionIndex <= endWord && positionIndex >= startWord) {
        			if (array[i].matches("[a-zA-Z!,'?]+")) {
        				result[isResultFilled] = array[i];
        				isResultFilled++;
        			}
        		}
        }
        return result;
    }

    @Override
    public String restoreString(int[] indices) {
    	
    	if (str.length() != indices.length) {
    		throw new IllegalArgumentException();
    	}
    	
    	String tempCompare = indices.toString();    	
    	String result = "";
    	
    	for (int i = 0; i < indices.length; i++) {
    		if (indices[i] < 0 || indices[i] > str.length()) {
    			throw new IndexOutOfBoundsException();
    		} else if (str.charAt(indices[i]) == '\0') {
    			throw new IllegalArgumentException();
    		}
    		
    		result += str.charAt(indices[i]);
    	}
    	
        return result;
    }

}
