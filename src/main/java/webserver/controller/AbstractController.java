package webserver.controller;

import webserver.http.request.HttpRequest;

public abstract class AbstractController implements Controller {
    @Override
    public String handleRequest(final HttpRequest request) {
        if (request.isMatchMethod("GET")) {
            return doGet(request);
        } else {
            return doPost(request);
        }
    }

    abstract String doGet(final HttpRequest request);

    abstract String doPost(final HttpRequest request);
}
