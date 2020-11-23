package webserver.controller.user;

import db.DataBase;
import model.User;
import webserver.controller.AbstractController;
import webserver.http.cookie.Cookie;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class UserListController extends AbstractController {

    @Override
    public void doGet(final HttpRequest request, final HttpResponse response) {
        final Cookie cookie = request.getCookie();
        final boolean isLogin = Boolean.valueOf(cookie.getAttribute("logined"));
        if (cookie.isEmpty() || !isLogin) {
            response.sendRedirect("/user/login.html");
            return;
        }

        final StringBuilder result = new StringBuilder();
        result.append("<table border='1'>");
        for (User user : DataBase.findAll()) {
            result.append("<tr>");
            result.append(" <td>").append(user.getUserId()).append("</td>");
            result.append(" <td>").append(user.getName()).append("</td>");
            result.append(" <td>").append(user.getEmail()).append("</td>");
            result.append("</tr>");
        }
        result.append("</table>");
        response.forwardBody(request, result.toString());
    }

    @Override
    public void doPost(final HttpRequest request, final HttpResponse response) {
        // 404
    }
}
