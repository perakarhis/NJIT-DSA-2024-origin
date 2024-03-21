package oy.tol.tra;

/**
 * An exception thrown by the StackInterface implementations when
 * trying to access elements from an empty stack.
 */
public class StackIsEmptyException extends RuntimeException {
      /**
    * Constructor for the exception.
    * @param message The message visible to developer or user.
    */
   public StackIsEmptyException(String message) {
      super(message);
   }
}
