package webserver.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public abstract class AbstractController implements Controller {
    @Override
    public void handleRequest(final HttpRequest request, final HttpResponse response) {
        if (request.isMatchMethod("GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doGet(final HttpRequest request, final HttpResponse response);

    protected abstract void doPost(final HttpRequest request, final HttpResponse response);
}
