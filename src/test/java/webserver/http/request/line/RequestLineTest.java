package webserver.http.request.line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import webserver.exception.EmptyHttpRequestException;
import webserver.exception.EmptyHttpRequestResourceException;
import webserver.http.request.line.RequestLine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class RequestLineTest {
    private String requestStartLine = "GET /index.html HTTP1.1";

    @DisplayName("요청 시작줄 객체를 생성할 수 있다.")
    @Test
    void create() {
        RequestLine expect = RequestLine.of(requestStartLine);

        RequestLine actual = RequestLine.of(requestStartLine);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("빈 요청일 경우 예외를 발생한다.")
    @Test
    void throwException_empty() {
        assertThatExceptionOfType(EmptyHttpRequestException.class).isThrownBy(
                () -> RequestLine.of(null)
        );
    }

    @DisplayName("빈 리소스를 요청했을 경우 예외를 발생한다.")
    @Test
    void throwException_emptyResource() {
        assertThatExceptionOfType(EmptyHttpRequestResourceException.class).isThrownBy(
                () -> RequestLine.of("GET HTTP1.1")
        );
    }

    @DisplayName("Http Method를 가지고 올 수 있다.")
    @Test
    void getHttpMethod() {
        RequestLine request = RequestLine.of(requestStartLine);
        final String expect = "GET";

        final boolean httpMethod = request.isMatchMethod(expect);

        assertThat(httpMethod).isTrue();
    }

    @DisplayName("요청 URI를 가지고 올 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "GET /index.html HTTP1.1,/index.html"
            , "GET /user/create HTTP1.1,/user/create"
    })
    void getRequestUri(String uri, String expect) {
        RequestLine request = RequestLine.of(uri);

        final String path = request.getRequestUri();

        assertThat(path).isEqualTo(expect);
    }

    @DisplayName("요청한 쿼리스트링 데이터를 가지고 올 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "GET /user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com HTTP1.1"
    })
    void getParameter(String uri) {
        RequestLine request = RequestLine.of(uri);

        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        assertAll(
                () -> assertThat(userId).isEqualTo("ohtaeg")
                , () -> assertThat(password).isEqualTo("ohtaeg")
                , () -> assertThat(name).isEqualTo("tae")
                , () -> assertThat(email).isEqualTo("otk1090@naver.com")
        );
    }
}
