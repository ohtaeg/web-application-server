package webserver.http.exception;

public class CannotFindHttpVersionException extends IllegalArgumentException {
    private static final String MESSAGE = "HTTP Version을 찾을 수 없습니다.";

    public CannotFindHttpVersionException() {
        super(MESSAGE);
    }
}
