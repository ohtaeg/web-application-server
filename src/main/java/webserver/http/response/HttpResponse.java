package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.request.HttpRequest;
import webserver.http.response.header.ResponseHeaders;
import webserver.http.response.line.HttpStatus;
import webserver.http.response.line.StatusLine;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    public static final String CRLF = System.lineSeparator();
    private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);

    private final StatusLine line;
    private final ResponseHeaders headers;
    private final DataOutputStream outputStream;

    public HttpResponse(final DataOutputStream outputStream, final HttpRequest request) {
        this.outputStream = outputStream;
        this.line = new StatusLine(request.getVersion());
        this.headers = new ResponseHeaders(new HashMap<>());
    }

    public void forward(final HttpRequest request, final String path) {
        final byte[] body;
        try {
            body = Files.readAllBytes(new File("./webapp" + path).toPath());
            headers.put("Content-Type", getHeaderContentType(request));
            headers.put("Content-Length", String.valueOf(body.length));
            responseHeader();
            responseBody(body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void sendRedirect(String path) {
        headers.put("Location", path);
        line.setStatus(HttpStatus.FOUND);
        responseHeader();
    }

    private void responseHeader() {
        try {
            this.outputStream.writeBytes(this.line.toString());
            for (Map.Entry<String, String> header : headers.all()) {
                this.outputStream.writeBytes(header.getKey() + ": " + header.getValue() + CRLF);
            }
            this.outputStream.writeBytes(CRLF);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(final byte[] body) {
        try {
            this.outputStream.write(body, 0, body.length);
            this.outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String getHeaderContentType(HttpRequest request) {
        return request.getHeader("Accept").split(",")[0];
    }
}
