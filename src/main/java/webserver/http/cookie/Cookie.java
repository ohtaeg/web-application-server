package webserver.http.cookie;

import java.util.Map;

public class Cookie {
    private Map<String, String> cookies;

    public Cookie(final Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public boolean isEmpty() {
        return cookies.size() == 0;
    }

    public String getAttribute(final String key) {
        return cookies.get(key);
    }
}
