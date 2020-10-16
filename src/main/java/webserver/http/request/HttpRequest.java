package webserver.http.request;

import webserver.http.request.header.RequestHeaders;
import webserver.http.request.line.RequestLine;
import webserver.http.request.messagebody.MessageBody;

// TODO 테스트 코드 추가
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
        if (requestLine.isMatchMethod("GET")) {
            return requestLine.getParameter(key);
        }
        return messageBody.getParameter(key);
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
}
