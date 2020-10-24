package webserver.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class IndexController extends AbstractController {
    private static final String LOOT_PATH = "/index.html";

    @Override
    void doGet(final HttpRequest request, final HttpResponse response) {
        response.forward(request, LOOT_PATH);
    }

    @Override
    void doPost(final HttpRequest request, final HttpResponse response) {
        doGet(request, response);
    }
}
