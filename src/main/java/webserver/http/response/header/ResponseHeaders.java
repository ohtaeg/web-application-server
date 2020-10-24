package webserver.http.response.header;

import java.util.Map;
import java.util.Set;

public class ResponseHeaders {
    private final Map<String, String> headers;

    public ResponseHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public void put(final String key, final String value) {
        headers.put(key, value);
    }

    public Set<Map.Entry<String, String>> all() {
        return headers.entrySet();
    }
}
