package webserver.request.model;

import webserver.exception.IllegalURIException;

import java.util.Objects;

public class Resource {
    // private static final Pattern PATH_PATTERN = Pattern.compile("^([^?#]*)(\\?([^#]*))?(#(.*))?");
    private static final String ROOT = "/";
    private static final String INDEX_PAGE = "/index.html";
    private static final String PATH_QUERY_DELIMITER = "?";
    private static final int DONT_SEPARATED = -1;

    private final String path;
    private final QueryString queryString;

    private Resource(final String path, final QueryString queryString) {
        this.path = path;
        this.queryString = queryString;
    }

    // TODO : /user/create? 로 넘어올 경우 유효성 검사 추가해줄 것
    public static Resource parse(String requestUriPath) {
        valid(requestUriPath);

        if (isRoot(requestUriPath)) {
            return new Resource(INDEX_PAGE, QueryString.empty());
        }

        int index = requestUriPath.indexOf(PATH_QUERY_DELIMITER);
        if (index == DONT_SEPARATED) {
            return new Resource(requestUriPath, QueryString.empty());
        }

        return new Resource(requestUriPath.substring(0, index), QueryString.parse(requestUriPath.substring(index + 1)));
    }

    private static void valid(final String requestUriPath) {
        if (requestUriPath == null || requestUriPath.trim().isEmpty()) {
            throw new IllegalURIException();
        }
    }

    private static boolean isRoot(final String requestUriPath) {
        return ROOT.equals(requestUriPath);
    }

    public String getPath() {
        return path;
    }

    public String getParameter(final String key) {
        return queryString.getValue(key);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Resource resource = (Resource) o;
        return Objects.equals(path, resource.path) &&
                Objects.equals(queryString, resource.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, queryString);
    }
}
