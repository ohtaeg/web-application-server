package webserver.controller;

import model.User;
import webserver.request.model.RequestStartLine;

public class UserController extends AbstractController {

    @Override
    String doGet(final RequestStartLine requestStartLine) {
        final String userId = requestStartLine.getParameter("userId");
        final String password = requestStartLine.getParameter("password");
        final String name = requestStartLine.getParameter("name");
        final String email = requestStartLine.getParameter("email");

        User user = new User(userId, password, name, email);

        return "/user/form.html";
    }

    @Override
    String doPost(final RequestStartLine request) {
        return null;
    }
}
