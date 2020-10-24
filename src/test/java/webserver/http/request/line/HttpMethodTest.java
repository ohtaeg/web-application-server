package webserver.http.request.line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpMethodTest {

    @DisplayName("GET Method를 생성할 수 있다.")
    @Test
    void createGet() {
        HttpMethod expect = HttpMethod.GET;

        final HttpMethod actual = HttpMethod.findBy("GET");

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("POST Method를 생성할 수 있다.")
    @Test
    void createPOST() {
        HttpMethod expect = HttpMethod.POST;

        final HttpMethod actual = HttpMethod.findBy("POST");

        assertThat(actual).isEqualTo(expect);
    }
}
