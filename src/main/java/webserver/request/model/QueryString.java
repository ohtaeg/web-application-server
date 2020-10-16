package webserver.request.model;

import util.HttpRequestUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QueryString {
    private final Map<String, String> query;

    private QueryString(final Map<String, String> query) {
        this.query = query;
    }

    public static QueryString parse(final String query) {
        if (query == null || query.trim().isEmpty()) {
            return empty();
        }
        return new QueryString(HttpRequestUtils.parseQueryString(query));
    }

    public static QueryString empty() {
        return new QueryString(new HashMap<>());
    }

    public boolean isEmpty() {
        return query.isEmpty();
    }

    public String getValue(final String key) {
        return query.get(key);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final QueryString that = (QueryString) o;
        return Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query);
    }
}
