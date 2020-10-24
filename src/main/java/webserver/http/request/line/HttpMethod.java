package webserver.http.request.line;

import java.util.Arrays;

public enum HttpMethod {
    GET("GET")
    , POST("POST")
    , PUT("PUT")
    , DELETE("DELETE")
    , HEAD("HEAD")
    , OPTIONS("OPTIONS")
    , TRACE("TRACE"); 

    private final String name;

    HttpMethod(final String method) {
        this.name = method;
    }

    public static HttpMethod findBy(String value) {
        return Arrays.stream(values())
                     .filter(method -> method.name.equals(value))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new);
    }
}
