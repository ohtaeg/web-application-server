package webserver.controller.user;

import db.DataBase;
import model.User;
import webserver.controller.AbstractController;
import webserver.controller.resource.IndexController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.response.line.HttpStatus;

public class UserCreateController extends AbstractController {

    @Override
    public void doGet(final HttpRequest request, final HttpResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(final HttpRequest request, final HttpResponse response) {
        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");

        User user = new User(userId, password, name, email);
        DataBase.addUser(user);

        response.sendRedirect(IndexController.LOOT_PATH);
    }
}
