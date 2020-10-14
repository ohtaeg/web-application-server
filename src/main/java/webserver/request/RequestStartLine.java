package webserver.request;

import webserver.exception.EmptyHttpRequestException;
import webserver.exception.EmptyHttpRequestResourceException;

import java.util.Objects;

public class RequestStartLine {
    private static final String DELIMITER = " ";
    private static final int HTTP_METHOD_INDEX = 0;
    private static final int REQUEST_RESOURCE_INDEX = 1;
    private static final int HTTP_VERSION_INDEX = 2;

    private final String method;
    private final String resource;
    private final String httpVersion;

    private RequestStartLine(final String method, final String resource, final String httpVersion) {
        this.method = method;
        this.resource = resource;
        this.httpVersion = httpVersion;
    }

    protected static RequestStartLine of(final String value) {
        final String[] startLine = parse(value);
        validResource(startLine.length);
        return new RequestStartLine(startLine[HTTP_METHOD_INDEX], startLine[REQUEST_RESOURCE_INDEX], startLine[HTTP_VERSION_INDEX]);
    }

    boolean hasResource() {
        return resource.startsWith("/") && resource.length() > 1;
    }

    String getResource() {
        return resource;
    }

    private static void validResource(final int length) {
        if (length != 3) {
            throw new EmptyHttpRequestResourceException();
        }
    }

    private static void validEmpty(final String value) {
        if (value == null) {
            throw new EmptyHttpRequestException();
        }
    }

    private static String[] parse(final String startLine) {
        validEmpty(startLine);
        return startLine.split(DELIMITER);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RequestStartLine that = (RequestStartLine) o;
        return Objects.equals(method, that.method) &&
                Objects.equals(resource, that.resource) &&
                Objects.equals(httpVersion, that.httpVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, resource, httpVersion);
    }
}
