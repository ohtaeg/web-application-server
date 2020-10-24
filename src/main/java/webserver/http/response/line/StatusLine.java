package webserver.http.response.line;

import webserver.http.common.model.HttpVersion;
import webserver.http.response.HttpResponse;

// todo 테스트 코드
public class StatusLine {
    private final HttpVersion version;
    private HttpStatus status;

    public StatusLine(final HttpVersion version) {
        this.version = version;
        this.status = HttpStatus.OK;
    }

    /**
     * TODO 리팩토링 public setter
     * @param status
     */
    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return version.getVersion() + " " + status.getCode() + " " +status.getReason() + HttpResponse.CRLF;
    }
}
