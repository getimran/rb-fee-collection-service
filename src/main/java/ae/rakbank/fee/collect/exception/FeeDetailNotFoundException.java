package ae.rakbank.fee.collect.exception;

/**
 * @author imran
 * Exception class for Fee Detail not found
 */
public class FeeDetailNotFoundException extends Exception {

    public FeeDetailNotFoundException() {
    }

    public FeeDetailNotFoundException(String message) {
        super(message);
    }
}
