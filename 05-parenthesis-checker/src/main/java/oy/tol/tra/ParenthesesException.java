package oy.tol.tra;

/**
 * Exception class to be used in implementing ParenthesisChecker.checkParentheses().
 * 
 * When your code sees that the text to analyse has too many closing parentheses,
 * throw this exeption like this:
 * 
 * <code>
 * throw new ParenthesesException("Invalid parenthesis in file", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES) ;
 * </code>
*/
class ParenthesesException extends Exception {
   private static final long serialVersionUID = 1426141947123353829L;
   public static final int TOO_MANY_CLOSING_PARENTHESES = -1;
   public static final int TOO_FEW_CLOSING_PARENTHESES = -2;
   public static final int PARENTHESES_IN_WRONG_ORDER = -3;
   public static final int STACK_FAILURE = -4;

   private final int code;

   public ParenthesesException(String message, int code) {
      super(message);
      this.code = code;
   }

   @Override
   public String toString() {
      return "Parentheses error: " + code + " " + getMessage();
   }
}
