package webserver.controller;

import webserver.request.model.RequestStartLine;

@FunctionalInterface
public interface Controller {
    String handleRequest(RequestStartLine request);
}
