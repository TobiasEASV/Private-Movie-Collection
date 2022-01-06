package be;

public class CategoryException extends Exception {
    /**
     * different constructors for when we are throwing the exception.
     * depending on if we just want to get the message or also the cause.
     */
    public CategoryException()
    {
        super();
    }

    public CategoryException(String message)
    {
        super(message);
    }

    public CategoryException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CategoryException(String message, Exception ex)
    {
        super(message, ex);
    }
}
