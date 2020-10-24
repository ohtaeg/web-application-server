package webserver.http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import webserver.http.common.messagebody.MessageBody;
import webserver.http.common.model.HttpVersion;
import webserver.http.request.line.RequestLine;

import static org.assertj.core.api.Assertions.assertThat;

class HttpRequestTest {
    private static final String url = "/user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com";
    private static final String requestStartLine = "GET " + url + " HTTP/1.1";

    private RequestLine requestLine = RequestLine.of(requestStartLine);
    private HttpRequest httpRequest = new HttpRequest.Builder(requestLine)
                                                     .messageBody(MessageBody.empty())
                                                     .build();

    @DisplayName("HttpRequest 객체를 생성할 수 있다.")
    @Test
    void create() {
        HttpRequest actual = new HttpRequest.Builder(requestLine)
                                            .messageBody(MessageBody.empty())
                                            .build();

        assertThat(actual).isEqualTo(httpRequest);
    }

    @DisplayName("uri를 갖고올 수 있다.")
    @Test
    void getRequestUri() {
        String expect = "/user/create";

        assertThat(httpRequest.getRequestUri()).isEqualTo(expect);
    }

    @DisplayName("메서드가 일치하는지 확인할 수 있다.")
    @Test
    void isMatchMethod() {
        assertThat(httpRequest.isMatchMethod("GET")).isTrue();
    }

    @DisplayName("쿼리스트링을 가지고 올 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"userId=ohtaeg", "password=ohtaeg", "name=tae", "email=otk1090@naver.com"}, delimiter = '=')
    void getParameter(String key, String expect) {
        String actual = httpRequest.getParameter(key);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("HTTP Version을 갖고올 수 있다.")
    @Test
    void getVersion() {
        final HttpVersion expect = HttpVersion.VERSION_1_1;

        assertThat(httpRequest.getVersion()).isEqualTo(expect);
    }
}
