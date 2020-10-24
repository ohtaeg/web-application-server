package webserver.http.common.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HttpVersionTest {

    @DisplayName("버전을 생성할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideVersion")
    void findBy(String value, HttpVersion expect) {
        final HttpVersion actual = HttpVersion.findBy(value);

        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> provideVersion() {
        return Stream.of(
                Arguments.of("HTTP/1.1", HttpVersion.VERSION_1_1)
                , Arguments.of("HTTP/1.2", HttpVersion.VERSION_1_2)
        );
    }
}
