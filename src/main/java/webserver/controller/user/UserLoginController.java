package webserver.controller.user;

import db.DataBase;
import model.User;
import webserver.controller.AbstractController;
import webserver.controller.resource.IndexController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class UserLoginController extends AbstractController {

    @Override
    public void doGet(final HttpRequest request, final HttpResponse response) {
        // 404
    }

    @Override
    public void doPost(final HttpRequest request, final HttpResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = DataBase.findUserById(userId);

        if (user == null || !password.equals(user.getPassword())) {
            response.setCookie(false);
            response.sendRedirect("/user/login_failed.html");
            return;
        }

        response.setCookie(true);
        response.sendRedirect(IndexController.LOOT_PATH);
    }
}
