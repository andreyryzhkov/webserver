import com.aryzhkov.webserver.HttpMethod;
import com.aryzhkov.webserver.Request;
import com.aryzhkov.webserver.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebServerTest {

    @Test
    public void testInjectUrlAndMethod() {
        String requestLine = "GET /index.html HTTP/1.0";
        Request request = new Request();
        RequestParser.injectUrlAndMethod(request, requestLine);

        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/index.html", request.getUri());
    }
}
