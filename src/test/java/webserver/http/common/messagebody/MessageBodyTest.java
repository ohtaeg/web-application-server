package webserver.http.common.messagebody;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MessageBodyTest {
    private MessageBody messageBody = createBody();

    @DisplayName("MessageBody를 생성할 수 있다.")
    @Test
    void create() {
        MessageBody expect = messageBody;

        MessageBody actual = createBody();

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("MessageBody가 비어있는지 확인할 수 있다.")
    @Test
    void isEmpty() {
        MessageBody actual = MessageBody.empty();

        assertThat(actual.isEmpty()).isTrue();
    }

    @DisplayName("MessageBody에서 값을 갖고올 수 있다")
    @Test
    void getParameter() {
        String expect = "ohtaeg";

        final String userId = messageBody.getParameter("userId");
        final String password = messageBody.getParameter("password");
        final String name = messageBody.getParameter("name");

        assertAll(
                () -> assertThat(userId).isEqualTo(expect)
                , () -> assertThat(password).isEqualTo(expect)
                , () -> assertThat(name).isEqualTo(expect)
        );
    }

    private MessageBody createBody() {
        Map<String, String> body = new HashMap<>();
        body.put("userId", "ohtaeg");
        body.put("password", "ohtaeg");
        body.put("name", "ohtaeg");
        return new MessageBody(body);
    }
}
