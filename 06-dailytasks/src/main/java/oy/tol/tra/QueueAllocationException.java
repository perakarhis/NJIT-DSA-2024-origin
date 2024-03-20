package oy.tol.tra;

/**
 * Exception thrown when allocation in the queue fails.
 */
public class QueueAllocationException extends RuntimeException {
   /** Instantiate with a message. 
    * @param message The explanation for the exception.
   */
   public QueueAllocationException(String message) {
      super(message);
   }   
}
