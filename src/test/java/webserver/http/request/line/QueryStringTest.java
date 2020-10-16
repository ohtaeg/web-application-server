package webserver.http.request.line;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import webserver.http.request.line.QueryString;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueryStringTest {

    @DisplayName("객체 생성을 성공한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "a=a"
            , "userId=ohtaeg&password=ohtaeg"
            , "userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com"
    })
    void create(String query) {
        QueryString expect = QueryString.parse(query);

        QueryString actual = QueryString.parse(query);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("빈 쿼리가 들어오면 빈 컬렉션을 반환한다.")
    @NullAndEmptySource
    @ParameterizedTest
    void empty(String query) {
        QueryString actual = QueryString.parse(query);

        assertThat(actual.isEmpty()).isTrue();
    }

    @DisplayName("key에 해당하는 value를 갖고올 수 있다.")
    @ParameterizedTest
    @MethodSource("provideQueryString")
    void getValue(String query, String[] keys, String expect) {
        QueryString actual = QueryString.parse(query);

        for (String key : keys) {
            assertThat(actual.getValue(key)).isEqualTo(expect);
        }
    }

    private static Stream<Arguments> provideQueryString() {
        return Stream.of(
                Arguments.of("a=a", new String[]{"a"}, "a"),
                Arguments.of("userId=ohtaeg&password=ohtaeg", new String[]{"userId", "password"}, "ohtaeg")
        );
    }
}
