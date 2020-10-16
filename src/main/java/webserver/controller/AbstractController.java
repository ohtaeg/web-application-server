package webserver.controller;

import webserver.request.model.RequestStartLine;

public abstract class AbstractController implements Controller {
    @Override
    public String handleRequest(final RequestStartLine request) {
        final String httpMethod = request.getHttpMethod();

        if (httpMethod.equals("GET")) {
            return doGet(request);
        } else {
            return doPost(request);
        }
    }

    abstract String doGet(final RequestStartLine request);

    abstract String doPost(final RequestStartLine request);
}
