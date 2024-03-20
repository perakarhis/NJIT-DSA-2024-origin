package oy.tol.tra;

/**
 * Exception thrown when using an empty queue fails.
 */
public class QueueIsEmptyException extends RuntimeException {
   /** Instantiate with a message. 
    * @param message The explanation for the exception.
   */
  public QueueIsEmptyException(String message) {
      super(message);
   }
}
