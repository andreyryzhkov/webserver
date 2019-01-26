package com.aryzhkov.webserver;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {

    private BufferedWriter bufferedWriter;

    public ResponseWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public void writeResponse(String startResponse, String content) throws IOException {
        bufferedWriter.write(startResponse);
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        bufferedWriter.write(content);
        bufferedWriter.flush();

        System.out.println("Response:" + startResponse);
    }

    public void writeSuccessResponse(String content) throws IOException {
        writeResponse("HTTP/1.0 200 OK", content);
    }

    public void writeNotFoundResponse() throws IOException {
        writeResponse("HTTP/1.0 404 Not Found", "");
    }

    public void writeInternalServerErrorResponse() throws IOException {
        writeResponse("HTTP/1.0 500 Internal Service Error", "");
    }

    public void writeBadRequestResponse() throws IOException {
        writeResponse("HTTP/1.0 400 Bad Resource", "");
    }
}
