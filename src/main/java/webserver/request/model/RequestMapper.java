package webserver.request.model;

import webserver.controller.Controller;
import webserver.controller.UserController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static Map<String, Controller> mappings;

    static {
        mappings = new HashMap<>();
        mappings.put("/index.html", (requestStartLine) -> "/index.html");
        mappings.put("/user/create", new UserController());
    }

    public Controller getController(String path){
        return mappings.get(path);
    }
}
