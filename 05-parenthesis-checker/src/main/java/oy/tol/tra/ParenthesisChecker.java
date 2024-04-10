package oy.tol.tra;
public class ParenthesisChecker {

   private ParenthesisChecker() {
   }
   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      int count=0;
      for (int i = 0; i < fromString.length(); i++) {
         char ch = fromString.charAt(i);
         if (ch == '(' || ch == '[' || ch == '{') {
            if(stack==null){
               throw new ParenthesesException("stack failure", ParenthesesException.STACK_FAILURE);
            }
            stack.push(ch);
            count++;
         } else if (ch == ')' || ch == ']' || ch == '}') {
            if(stack.isEmpty()){
               throw new ParenthesesException("Too many closing parentheses.",ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }
            
            Character popped = stack.pop();
            count++;
            if ((ch == ')' && popped != '(') ||
                  (ch == ']' && popped != '[') ||
                  (ch == '}' && popped != '{')) {
               throw new ParenthesesException("Mismatched parentheses.",ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
            }
         }
      }
      if (!stack.isEmpty()) {
         throw new ParenthesesException("Too many opening parentheses.",ParenthesesException.STACK_FAILURE);
      }

      return count;
   }
}
