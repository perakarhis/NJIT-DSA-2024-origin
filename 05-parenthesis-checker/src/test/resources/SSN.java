package oy.tol.tra;

/**
 * A class to handle Finnish social security numbers.
 * 
 * @author Antti Juustila
 */
public class SSN {

   private static final int SSN_LENGTH = 11;
   private static final int DATE_PART_END_SUBSTRING_INDEX = 6;
   private static final int DDMM_PART_END_SUBSTRING_INDEX = 4;
   private static final int CENTURY_CHAR_INDEX = 6;
   private static final int CHECK_CODE_DIVIDER = 31;
   private static final int PERSON_CODE_INDEX_START = 7;
   private static final int PERSON_CODE_LENGTH = 3;
   private static final int CHECKCODE_INDEX = 10;
   private static final String CHECKCHARS = "0123456789ABCDEFHJKLMNPRSTUVWXY";

   /**
    * The result of verifying a SSN.
    */
   public enum Result {
      INVALID_SSN,          /** SSN is invalid. */ 
      VALID_TEST_SSN,       /** SSN is a valid one but is for testing SSNs only. */
      VALID_SSN             /** SSN is valid. */
   }

   /**
    * Verifies a SSN and returns a Result value to indicate if the SSN is valid or not.
    * @param ssn The SSN to verify.
    * @return  See Result enum for return values.
    */
   public static Result verifySSN(String ssn) {
      Result result = Result.INVALID_SSN;
      if (null != ssn && ssn.length() == SSN_LENGTH) {
         String date = ssn.substring(0, DATE_PART_END_SUBSTRING_INDEX);
         if (date.matches("[0-9]+")) {
            String century = null;
            switch (ssn.charAt(CENTURY_CHAR_INDEX)) {
               case '+':
                  century = "18";
                  break;
               case '-':
                  century = "19";
                  break;
               case 'A':
                  century = "20";
                  break;
               default:
                  break;
            }
            if (null != century) {
               String personNumberString = ssn.substring(PERSON_CODE_INDEX_START, PERSON_CODE_INDEX_START + PERSON_CODE_LENGTH);
               Integer personNumber = Integer.parseInt(personNumberString);
               StringBuilder builder = new StringBuilder();
               builder.append(ssn.substring(0, DDMM_PART_END_SUBSTRING_INDEX));
               builder.append(ssn.substring(4, DATE_PART_END_SUBSTRING_INDEX));
               builder.append(personNumberString);
               String checkSumString = builder.toString();
               Long checkSum = Long.parseLong(checkSumString) % CHECK_CODE_DIVIDER;
               if (CHECKCHARS.charAt(checkSum.intValue()) == ssn.charAt(CHECKCODE_INDEX)) {
                  if (personNumber > 2 && personNumber < 900) {
                     result = Result.VALID_SSN;
                  } else if (personNumber >= 900 && personNumber <= 999) {
                     result = Result.VALID_TEST_SSN;
                  }
               }
            }
         }
      }
      return result;
   }
}
