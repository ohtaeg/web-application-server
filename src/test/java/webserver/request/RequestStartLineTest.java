package webserver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.exception.EmptyHttpRequestException;
import webserver.exception.EmptyHttpRequestResourceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RequestStartLineTest {
    private String requestStartLine = "GET /index.html HTTP1.1";

    @DisplayName("요청 시작줄 객체를 생성할 수 있다.")
    @Test
    void create() {
        RequestStartLine expect = RequestStartLine.of(requestStartLine);

        RequestStartLine actual = RequestStartLine.of(requestStartLine);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("빈 요청일 경우 예외를 발생한다.")
    @Test
    void throwException_empty() {
        assertThatExceptionOfType(EmptyHttpRequestException.class).isThrownBy(
                () -> RequestStartLine.of(null)
        );
    }

    @DisplayName("빈 리소스를 요청했을 경우 예외를 발생한다.")
    @Test
    void throwException_emptyResource() {
        assertThatExceptionOfType(EmptyHttpRequestResourceException.class).isThrownBy(
                () -> RequestStartLine.of("GET HTTP1.1")
        );
    }
}
