package hello.servlet.ch03.web.frontcontroller.v5.adapter;

import hello.servlet.ch03.web.frontcontroller.ModelView;
import hello.servlet.ch03.web.frontcontroller.v3.ControllerV3;
import hello.servlet.ch03.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        if (handler instanceof ControllerV3) {
            return true;
        }
        return false;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }
}
