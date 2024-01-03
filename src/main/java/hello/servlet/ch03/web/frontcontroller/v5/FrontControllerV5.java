package hello.servlet.ch03.web.frontcontroller.v5;

import hello.servlet.ch03.web.frontcontroller.ModelView;
import hello.servlet.ch03.web.frontcontroller.MyView;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.ch03.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.ch03.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.ch03.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.ch03.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.ch03.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.ch03.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapter();
    }

    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        MyHandlerAdapter adapter = findAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        return new MyView(prefix + viewName + suffix);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }

    private MyHandlerAdapter findAdapter(Object handler) {
        for (MyHandlerAdapter ad : handlerAdapters) {
            if (ad.supports(handler)) {
                return ad;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }
}
