package webserver.http.exception;

public class IllegalURIException extends IllegalArgumentException {
    private static final String MESSAGE = "잘못된 URI 입니다.";

    public IllegalURIException() {
        super(MESSAGE);
    }
}
