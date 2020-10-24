package webserver.http.exception;

public class EmptyHttpRequestResourceException extends IllegalArgumentException {
    private static final String MESSAGE = "요청하신 자원 uri가 없습니다.";

    public EmptyHttpRequestResourceException() {
        super(MESSAGE);
    }
}
