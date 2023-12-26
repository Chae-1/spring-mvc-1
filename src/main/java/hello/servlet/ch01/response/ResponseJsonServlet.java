package hello.servlet.ch01.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.ch01.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("/application/json");

        HelloData helloData = new HelloData();
        helloData.setUsername("chae");
        helloData.setAge(26);

        // {"username":"chae", "age":20};
        String jsn = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(jsn);
    }
}
