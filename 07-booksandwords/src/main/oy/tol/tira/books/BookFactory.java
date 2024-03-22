package oy.tol.tira.books;

/**
 * TODO: This class is used to create an instance of your implementation of the Book interface.
 * <p>
 * Implement the <code>createBook()</code> method to return your instance of the Book interface.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public final class BookFactory {
    private BookFactory() {
    }

    /**
     * TODO: You must implement this method so that it returns an instance of 
     * your concreted class implementing the Book interface.
     * @return Your implementation of the Book interface.
     */
    public static Book createBook() {
        // return null;
        return new BadBookImplementation();
    }
}
