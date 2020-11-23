package webserver.controller.resource;

import webserver.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class ResourceController extends AbstractController {
    @Override
    protected void doGet(final HttpRequest request, final HttpResponse response) {
        response.forward(request, request.getRequestUri());
    }

    @Override
    protected void doPost(final HttpRequest request, final HttpResponse response) {
        // response.badRequest(); 404
    }
}
