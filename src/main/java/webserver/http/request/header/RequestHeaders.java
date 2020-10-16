package webserver.http.request.header;

import java.util.Map;

public class RequestHeaders {
    private static final String HEADER_CONTENT_LENGTH_ATTRIBUTE = "Content-Length";

    private final Map<String, String> headers;

    public RequestHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean hasContent() {
        String length = headers.get(HEADER_CONTENT_LENGTH_ATTRIBUTE);
        return length != null && Integer.parseInt(length) > 0;
    }

    public int getContentLength() {
        return Integer.parseInt(headers.get(HEADER_CONTENT_LENGTH_ATTRIBUTE));
    }
}
