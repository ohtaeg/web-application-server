package webserver.controller;

import webserver.http.request.HttpRequest;

@FunctionalInterface
public interface Controller {
    String handleRequest(HttpRequest request);
}
