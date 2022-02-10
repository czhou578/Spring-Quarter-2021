public class StringCalculator {
   public static int add(final String numbers) {
	   
      int returnValue = 0;
      String[] numbersArray = numbers.split(",|\n"); 
      for (String number : numbersArray) {
         if (!number.trim().isEmpty()) {
        	 int test = Integer.parseInt(number.trim());
        	 if (test < -1) {
        		 throw new IllegalArgumentException();
        	 }
        	 
        	 if (test > 1000) {
        		 continue;
        	 }
            returnValue += Integer.parseInt(number.trim());
         }
      }
      return returnValue;
   }
}