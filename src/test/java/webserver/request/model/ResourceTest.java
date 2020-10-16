package webserver.request.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ResourceTest {
    private final String url = "/user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com";

    @DisplayName("객체 생성을 성공한다.")
    @Test
    void create() {
        Resource expect = Resource.parse(url);

        Resource actual = Resource.parse(url);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("해당 uri가 root일 경우 성공한다.")
    @Test
    void root() {
        final String expect = "/index.html";

        Resource actual = Resource.parse("/");

        assertThat(actual.getPath()).isEqualTo(expect);
    }

    @DisplayName("uri를 가지고 올 수 있다.")
    @Test
    void getPath() {
        final String expect = "/user/create";

        Resource actual = Resource.parse(url);

        assertThat(actual.getPath()).isEqualTo(expect);
    }

    @DisplayName("요청한 쿼리스트링 데이터를 가지고 올 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "/user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com"
    })
    void getParameter(String uri) {
        Resource resource = Resource.parse(uri);

        final String userId = resource.getParameter("userId");
        final String password = resource.getParameter("password");
        final String name = resource.getParameter("name");
        final String email = resource.getParameter("email");

        assertAll(
                () -> assertThat(userId).isEqualTo("ohtaeg")
                , () -> assertThat(password).isEqualTo("ohtaeg")
                , () -> assertThat(name).isEqualTo("tae")
                , () -> assertThat(email).isEqualTo("otk1090@naver.com")
        );
    }
}
