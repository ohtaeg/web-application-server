package webserver.http.request.header;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestHeadersTest {
    private RequestHeaders requestHeaders;

    @BeforeEach
    void setUp() {
        Map<String , String> map = new HashMap<>();
        map.put("Content-Length", "15");
        requestHeaders = new RequestHeaders(map);
    }

    @DisplayName("Header Content-length를 가지고 있다.")
    @Test
    void hasContent() {
        assertThat(requestHeaders.hasContent()).isTrue();
    }

    @DisplayName("Header Content-length 값을 가지고 올 수 있다.")
    @Test
    void getContentLength() {
        assertThat(requestHeaders.getContentLength()).isEqualTo(15);
    }
}
