package webserver.http.request.header;

import webserver.http.cookie.Cookie;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public String getValue(final String key) {
        return headers.get(key);
    }

    public Cookie getCookie() {
        final String cookies = headers.getOrDefault("Cookie", "");
        final Map<String, String> collect = Stream.of(cookies.split(";"))
                                                  .map(String::trim)
                                                  .map(keysAndValues -> keysAndValues.split("="))
                                                  .filter(keyValue -> keyValue.length == 2)
                                                  .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
        return new Cookie(collect);
    }
}
