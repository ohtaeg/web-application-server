package webserver.http.request;

import webserver.http.common.model.HttpVersion;
import webserver.http.request.header.RequestHeaders;
import webserver.http.request.line.RequestLine;
import webserver.http.common.messagebody.MessageBody;

import java.util.Objects;

public class HttpRequest {
    private final RequestLine requestLine;
    private final RequestHeaders requestHeaders;
    private final MessageBody messageBody;

    private HttpRequest(final Builder builder) {
        this.requestLine = builder.requestLine;
        this.requestHeaders = builder.requestHeaders;
        this.messageBody = builder.messageBody;
    }

    public String getRequestUri() {
        return requestLine.getRequestUri();
    }

    public boolean isMatchMethod(final String method) {
        return requestLine.isMatchMethod(method);
    }

    public String getParameter(final String key) {
        if (messageBody.isEmpty()) {
            return requestLine.getParameter(key);
        }
        return messageBody.getParameter(key);
    }

    public HttpVersion getVersion() {
        return requestLine.getVersion();
    }

    public String getHeader(final String key) {
        return requestHeaders.getValue(key);
    }

    public static class Builder {
        private final RequestLine requestLine;

        private RequestHeaders requestHeaders;
        private MessageBody messageBody;

        public Builder(final RequestLine requestLine) {
            this.requestLine = requestLine;
        }

        public Builder requestHeaders(final RequestHeaders requestHeaders) {
            this.requestHeaders = requestHeaders;
            return this;
        }

        public Builder body(final RequestHeaders requestHeaders) {
            this.requestHeaders = requestHeaders;
            return this;
        }

        public Builder messageBody(MessageBody messageBody) {
            if (messageBody == null) {
                messageBody = MessageBody.empty();
            }
            this.messageBody = messageBody;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HttpRequest that = (HttpRequest) o;
        return Objects.equals(requestLine, that.requestLine) &&
                Objects.equals(requestHeaders, that.requestHeaders) &&
                Objects.equals(messageBody, that.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestLine, requestHeaders, messageBody);
    }
}
