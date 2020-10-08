package seedu.address.model.service.exceptions;

/**
 * Indicates that SuperSalon has reached the maximum number of Services that it can store - 1000.
 */
public class MaximumServiceException extends RuntimeException {
    public MaximumServiceException(String message) {
        super(message);
    }
}
