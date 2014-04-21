package demo.service;

/**
 * Exception class for transactions
 *
 * @author Erofeev Danil
 */
public class TransactionalException extends Exception {

    private static final long serialVersionUID = -2018563926924851486L;

    public TransactionalException(String message) {
        super(message);
    }

    public TransactionalException(Throwable cause) {
        super(cause);
    }

    public TransactionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
