package webserver.controller;

import model.User;
import webserver.http.request.HttpRequest;

public class UserController extends AbstractController {

    @Override
    String doGet(final HttpRequest request) {
        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        User user = new User(userId, password, name, email);

        return "/user/form.html";
    }

    @Override
    String doPost(final HttpRequest request) {
        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        User user = new User(userId, password, name, email);

        return "/user/form.html";
    }
}
