package webserver.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.request.model.RequestMapper;
import webserver.request.model.RequestStartLine;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {
    private final String url = "GET /user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com HTTP1.1";
    private final RequestStartLine request = RequestStartLine.of(url);
    private Controller controller;

    @DisplayName("GET method 유저 회원가입을 처리할 수 있다.")
    @Test
    void doGet() {
        RequestMapper requestMapper = new RequestMapper();
        controller = requestMapper.getController(request.getRequestUri());
        final String expect = "/user/form.html";

        final String action = controller.handleRequest(RequestStartLine.of(url));

        assertThat(action).isEqualTo(expect);
    }
}
