package webserver.controller;

import model.User;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.response.line.HttpStatus;

public class CreateUserController extends AbstractController {

    @Override
    void doGet(final HttpRequest request, final HttpResponse response) {
        doPost(request, response);
    }

    @Override
    void doPost(final HttpRequest request, final HttpResponse response) {
        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        User user = new User(userId, password, name, email);

        response.sendRedirect("/index.html");
    }
}
