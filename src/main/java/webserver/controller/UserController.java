package webserver.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class UserController extends AbstractController {

    @Override
    void doGet(final HttpRequest request, final HttpResponse response) {
        response.forward(request, "/user/form.html");
    }

    @Override
    void doPost(final HttpRequest request, final HttpResponse response) {
        doGet(request, response);
    }
}
