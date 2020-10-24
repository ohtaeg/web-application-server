package webserver.http.request;

import webserver.controller.Controller;
import webserver.controller.CreateUserController;
import webserver.controller.IndexController;
import webserver.controller.UserController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static Map<String, Controller> mappings;

    static {
        mappings = new HashMap<>();
        mappings.put("/", new IndexController());
        mappings.put("/index.html", new IndexController());
        mappings.put("/user/form.html", new UserController());
        mappings.put("/user/create", new CreateUserController());
    }

    public Controller getController(String path){
        return mappings.get(path);
    }
}
