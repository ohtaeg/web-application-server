package webserver.http.exception;

public class EmptyHttpRequestException extends IllegalArgumentException {
    private static final String MESSAGE = "요청의 값이 잘못되었습니다.";

    public EmptyHttpRequestException() {
        super(MESSAGE);
    }
}
