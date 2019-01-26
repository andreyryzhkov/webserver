package com.aryzhkov.webserver;

import java.io.*;

public class ResourceReader {

    public static String ReadContent(String uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(uri)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return stringBuilder.toString();
    }
}
