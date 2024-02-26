package oy.tol.tra;

/**
 * A class for verifying Finnish social security numbers (SSN). a.k.a Personal
 * identity code.
 * <p>
 * Usage:
 * <p>
 * {@code SSN.Result result = SSN.verifySSN("123456-789X"); }
 * <p>
 * Where the result then contains one of:
 * <ul>
 * <li>{@code VALID_SSN} if the SSN was a valid Finnish SSN.
 * <li>{@code INVALID_SSN} if the SSN was not valid Finnish SSN.
 * <li>{@code VALID_TEST_SSN} if the SSN was valid Finnish SSN from the set of
 * SSNs reserved for testing only.
 * </ul>
 * 
 * @author Antti Juustila
 * @version 1.0.0
 * @see <a href="https://dvv.fi/henkilotunnus">Digi- ja väestotietovirasto</a>
 *      (page available also in English and Swedish).
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

   private SSN() {
      // Empty
   }

   /**
    * The result of verifying a SSN.
    */
   public enum Result {
      /** SSN is invalid. */
      INVALID_SSN,
      /** SSN is a valid one but is for testing SSNs only. */
      VALID_TEST_SSN,
      /** SSN is valid. */
      VALID_SSN
   }

   /**
    * Verifies a SSN and returns a Result value to indicate if the SSN is valid,
    * valid test SSN or invalid SSN.
    * <p>
    * The method MUST NOT throw exceptions.
    * 
    * @param ssn The SSN to verify.
    * @return See {@link SSN.Result} enum for possible return values.
    */
   public static Result verifySSN(String ssn) {
      // Pessimists never get disappointed, so let's assume invalid SSN.
      Result result = Result.INVALID_SSN;
      if (null != ssn && ssn.length() == SSN_LENGTH) {
         // If valid string and the length matches SSN length, start
         // checking the SSN string contents
         String date = ssn.substring(0, DATE_PART_END_SUBSTRING_INDEX);
         if (date.matches("[0-9]+")) {
            // The date part was positive number, good so far.
            // We area ready for the changes taken into use in 2023
            // when new century separator chars are taken into use for those
            // born in 1900's and 2000.
            String century = null;
            switch (ssn.charAt(CENTURY_CHAR_INDEX)) {
            case '+':
               century = "18";
               break;
            case '-':
            case 'Y':
            case 'X':
            case 'W':
            case 'V':
            case 'U':      
               century = "19";
               break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
               century = "20";
               break;
            default:
               break;
            }
            if (null != century) {
               // String had correct separator indicating century of date of birth; one of
               // those allowed before ("+-A") and the new ones taken into use in 2023.
               String personNumberString = ssn.substring(PERSON_CODE_INDEX_START,
                     PERSON_CODE_INDEX_START + PERSON_CODE_LENGTH);
               // Next checking the three digit number after the century separator.
               Integer personNumber = Integer.parseInt(personNumberString);
               if (personNumber > 0) {
                  // If it was a positive integer, then calculate the checksum.
                  StringBuilder builder = new StringBuilder();
                  builder.append(ssn.substring(0, DDMM_PART_END_SUBSTRING_INDEX));
                  builder.append(ssn.substring(4, DATE_PART_END_SUBSTRING_INDEX));
                  builder.append(personNumberString);
                  String checkSumString = builder.toString();
                  Long checkSum = Long.parseLong(checkSumString) % CHECK_CODE_DIVIDER;
                  if (CHECKCHARS.charAt(checkSum.intValue()) == ssn.charAt(CHECKCODE_INDEX)) {
                     if (personNumber > 2 && personNumber < 900) {
                        // Checksum was correct for a real SSN.
                        result = Result.VALID_SSN;
                     } else if (personNumber >= 900 && personNumber <= 999) {
                        // Checksum was correct for SSNs valid for testing systems.
                        result = Result.VALID_TEST_SSN;
                     }
                  }
               }
            }
         }
      }
      return result;
   }
}
