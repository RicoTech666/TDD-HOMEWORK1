package tdd.exception;

/**
 * @author Gavin
 */
public class LockerException extends Exception {
    private static final long serialVersionUID = 5345800791722444L;
    
    public LockerException(String message) {
        super(message);
    }
}
