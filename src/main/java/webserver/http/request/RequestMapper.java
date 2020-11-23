package webserver.http.request;

import webserver.controller.Controller;
import webserver.controller.resource.ResourceController;
import webserver.controller.user.UserCreateController;
import webserver.controller.resource.IndexController;
import webserver.controller.user.UserLoginController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static Map<String, Controller> mappings;

    static {
        mappings = new HashMap<>();
        mappings.put("/", new IndexController());
        mappings.put("/user/login", new UserLoginController());
        mappings.put("/user/create", new UserCreateController());
    }

    public Controller getController(String path){
        return mappings.getOrDefault(path, new ResourceController());
    }
}
