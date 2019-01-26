package com.aryzhkov.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static Request parseRequest(BufferedReader bufferedReader) throws IOException {
        Request request = new Request();
        injectHeaders(request, bufferedReader);
        return request;
    }

    public static void injectUrlAndMethod(Request request, String line) {
        String[] split = line.split(" ");
        request.setHttpMethod(HttpMethod.getByName(split[0]));
        request.setUri(split[1]);
    }

    public static void injectHeaders(Request request, BufferedReader bufferedReader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line = bufferedReader.readLine();
        if (line != null) {
            injectUrlAndMethod(request, line);
            line = bufferedReader.readLine();
            while (!line.isEmpty()) {
                String[] split = line.split(":");
                headers.put(split[0], split[1]);
                line = bufferedReader.readLine();
            }
            request.setHeaders(headers);
        }
    }
}