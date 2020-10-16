package webserver.http.request.messagebody;

import java.util.HashMap;
import java.util.Map;

// TODO 테스트 코드 추가
public class MessageBody {
    private final Map<String, String> bodies;

    public MessageBody(final Map<String, String> bodies) {
        this.bodies = bodies;
    }

    public static MessageBody empty() {
        return new MessageBody(new HashMap<>());
    }

    public String getParameter(final String key) {
        return bodies.get(key);
    }
}
