package webserver.http.response.line;

public enum HttpStatus {
    OK(200, "OK"),
    FOUND(302, "FOUND");

    private int code;
    private String reason;

    HttpStatus(final int code, final String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
