package Util.excepciones;

public class AssertException extends Exception {

    private static final long serialVersionUID = -3644049366582315352L;

    public AssertException(final String message) {
        super(message);
    }

    public AssertException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AssertException() {
        super();
    }

    public AssertException(final Throwable cause) {
        super(cause);
    }

}
