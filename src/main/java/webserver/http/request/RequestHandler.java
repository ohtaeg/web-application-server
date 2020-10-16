package webserver.http.request;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;
import webserver.controller.Controller;
import webserver.http.request.header.RequestHeaders;
import webserver.http.request.line.RequestLine;
import webserver.http.request.messagebody.MessageBody;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private static final String END_OF_LINE = "";
    private static final String REQUEST_HEADER_DELIMITER = ": ";

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            DataOutputStream dos = new DataOutputStream(out);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            HttpRequest httpRequest = createHttpRequest(bufferedReader);
            RequestMapper requestMapper = new RequestMapper();

            final String uri = httpRequest.getRequestUri();
            final Controller controller = requestMapper.getController(uri);
            final String action = controller.handleRequest(httpRequest);

            byte[] body = action.getBytes();
            if (action.contains(".")) {
                body = Files.readAllBytes(new File("./webapp" + action).toPath());
            }

            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private HttpRequest createHttpRequest(final BufferedReader bufferedReader) throws IOException {
        final String requestStartLine = bufferedReader.readLine();
        RequestLine requestLine = RequestLine.of(requestStartLine);
        RequestHeaders requestHeaders = readRequestHeaders(bufferedReader);
        MessageBody messageBody = null;

        if (isExistMessageBody(requestLine, requestHeaders)) {
            messageBody = readRequestBody(bufferedReader, requestHeaders.getContentLength());
        }

        return new HttpRequest.Builder(requestLine)
                              .requestHeaders(requestHeaders)
                              .messageBody(messageBody)
                              .build();
    }

    private boolean isExistMessageBody(final RequestLine requestLine, final RequestHeaders requestHeaders) {
        return requestLine.isMatchMethod("POST") && requestHeaders.hasContent();
    }

    private RequestHeaders readRequestHeaders(final BufferedReader bufferedReader) throws IOException {
        Map<String, String> headers = new HashMap<>();

        String line;
        while (!(line = bufferedReader.readLine()).equals(END_OF_LINE)) {
            log.debug(line);
            int separatedIndex = line.indexOf(REQUEST_HEADER_DELIMITER);
            headers.put(line.substring(0, separatedIndex), line.substring(separatedIndex + REQUEST_HEADER_DELIMITER.length()));
        }

        return new RequestHeaders(headers);
    }

    private MessageBody readRequestBody(final BufferedReader bufferedReader, final int contentLength) throws IOException {
        Map<String, String> body = new HashMap<>();
        String message = IOUtils.readData(bufferedReader, contentLength);
        return new MessageBody(HttpRequestUtils.parseQueryString(message));
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
