package be;

public class MovieException extends Exception {
    /**
     * different constructors for when we are throwing the exception.
     * depending on if we just want to get the message or also the cause.
     */
    public MovieException()
    {
        super();
    }

    public MovieException(String message)
    {
        super(message);
    }

    public MovieException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MovieException(String message, Exception ex)
    {
        super(message, ex);
    }
}
