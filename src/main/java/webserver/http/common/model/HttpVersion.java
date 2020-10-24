package webserver.http.common.model;

import webserver.http.exception.CannotFindHttpVersionException;

import java.util.Arrays;

public enum HttpVersion {
    VERSION_1_1("HTTP/1.1"),
    VERSION_1_2("HTTP/1.2");

    private final String version;

    HttpVersion(final String version) {
        this.version = version;
    }

    public static HttpVersion findBy(final String value) {
        return Arrays.stream(values())
                     .filter(httpVersion -> httpVersion.version.equals(value))
                     .findFirst()
                     .orElseThrow(CannotFindHttpVersionException::new);
    }

    public String getVersion() {
        return version;
    }
}
