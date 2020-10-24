package webserver.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public interface Controller {
    void handleRequest(final HttpRequest request, final HttpResponse response);
}
