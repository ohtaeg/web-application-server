package webserver.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestMapper;
import webserver.http.request.header.RequestHeaders;
import webserver.http.request.line.RequestLine;
import webserver.http.common.messagebody.MessageBody;
import webserver.http.response.HttpResponse;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UserLoginControllerTest {
    private final String getUrl = "GET /user/create?userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com HTTP1.1";
    private final String postUrl = "POST /user/create HTTP1.1";
    private final String bodyOfPostUrl = "userId=ohtaeg&password=ohtaeg&name=tae&email=otk1090@naver.com";

    private RequestLine requestLine;
    private HttpRequest request;
    private HttpResponse response;
    private Controller controller;

    @DisplayName("GET method 유저 회원가입을 처리할 수 있다.")
    @Test
    void doGet() {
        final String expect = "/user/form.html";
        RequestMapper requestMapper = new RequestMapper();
        requestLine = RequestLine.of(getUrl);
        controller = requestMapper.getController(requestLine.getRequestUri());
        request = new HttpRequest.Builder(requestLine)
                                     .requestHeaders(createHeader())
                                     .build();

        //final String action = controller.handleRequest(request, httpResponse);

        //assertThat(action).isEqualTo(expect);
    }

    @DisplayName("POST method 유저 회원가입을 처리할 수 있다.")
    @Test
    void doPost() {
        final String expect = "/user/form.html";
        RequestMapper requestMapper = new RequestMapper();
        requestLine = RequestLine.of(postUrl);
        controller = requestMapper.getController(requestLine.getRequestUri());
        request = new HttpRequest.Builder(requestLine)
                                     .requestHeaders(createHeader())
                                     .messageBody(createBody())
                                     .build();

        //final String action = controller.handleRequest(request, httpResponse);

        //assertThat(action).isEqualTo(expect);
    }

    private RequestHeaders createHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Content-Length", Integer.toString(bodyOfPostUrl.length()));
        return new RequestHeaders(headers);
    }

    private MessageBody createBody() {
        Map<String, String> body = new HashMap<>();
        body.put("userId", "ohtaeg");
        body.put("password", "ohtaeg");
        body.put("name", "ohtaeg");
        return new MessageBody(body);
    }
}
