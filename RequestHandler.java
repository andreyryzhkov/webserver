package com.aryzhkov.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RequestHandler {

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String webAppPath;

    public RequestHandler(BufferedReader bufferedReader, BufferedWriter bufferedWriter, String webAppPath) {
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        ResponseWriter responseWriter = new ResponseWriter(bufferedWriter);
        try {
            Request request = RequestParser.parseRequest(bufferedReader);
            try {
                String content = ResourceReader.ReadContent(webAppPath + request.getUri());
                responseWriter.writeSuccessResponse(content);
            } catch (FileNotFoundException e) {
                responseWriter.writeNotFoundResponse();
            } catch (IOException e) {
                responseWriter.writeInternalServerErrorResponse();
            }
        } catch (IOException e) {
            responseWriter.writeBadRequestResponse();
        }
    }
}