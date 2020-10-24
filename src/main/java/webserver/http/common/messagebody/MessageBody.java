package webserver.http.common.messagebody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MessageBody {
    private final Map<String, String> bodies;

    public MessageBody(final Map<String, String> bodies) {
        this.bodies = bodies;
    }

    public static MessageBody empty() {
        return new MessageBody(new HashMap<>());
    }

    public boolean isEmpty() {
        return bodies.isEmpty();
    }

    public String getParameter(final String key) {
        return bodies.get(key);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MessageBody that = (MessageBody) o;
        return Objects.equals(bodies, that.bodies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodies);
    }
}
