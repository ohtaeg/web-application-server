package webserver.controller.resource;

import webserver.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class IndexController extends AbstractController {
    public static final String LOOT_PATH = "/index.html";

    @Override
    public void doGet(final HttpRequest request, final HttpResponse response) {
        response.forward(request, LOOT_PATH);
    }

    @Override
    public void doPost(final HttpRequest request, final HttpResponse response) {
        doGet(request, response);
    }
}
