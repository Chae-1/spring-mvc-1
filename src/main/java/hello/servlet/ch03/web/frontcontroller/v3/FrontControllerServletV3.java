package hello.servlet.ch03.web.frontcontroller.v3;

import hello.servlet.ch03.web.frontcontroller.ModelView;
import hello.servlet.ch03.web.frontcontroller.MyView;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // save 인 경우, paramter 정보가 넘어온다.
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        Map<String, String> paramMap = createParamMap(request, requestURI);
        ModelView mv = controller.process(paramMap);

        Map<String, Object> model = mv.getModel(); // member, members
        String viewName = mv.getViewName(); // 논리이름
        MyView myView = viewResolver(viewName);

        myView.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        MyView myView = new MyView(prefix + viewName + suffix);
        return myView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request, String requestURI) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
