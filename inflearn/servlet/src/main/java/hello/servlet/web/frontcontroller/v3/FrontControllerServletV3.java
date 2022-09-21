package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
// v1/ 하위 어떤 url이 들어와도 이 서블릿이 무조건 호출된다.
public class FrontControllerServletV3 extends HttpServlet {
    // mapping 정보. url , controllerV1 객체.
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        // 1. 컨트롤러 조회
        ControllerV3 controller= controllerMap.get(requestURI);
        // 다형성 사용
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(request);
        // 2. 컨트롤러 호출 (request의 parameter에 대한 정보를 넘김)
        // 3. ModelView 반환
        ModelView mv = controller.process(paramMap);
        // controller에서 반환받은 ModelView에서 view이름 꺼내기(controller에서 논리이름으로 설정)
        String viewName = mv.getViewName();// 논리이름 new-form
        // 4. viewResolver 통해서 논리이름 -> 절대 경로 변환. 그리고 MyView 객체 생성해 viewPath로 세팅
        MyView view = viewResolver(viewName); // new-form 으로 viewResolver 호출

        // 5. 생성된 view객체에 request, response와 model 넘겨 jsp 통해서 rendering 처리.
        view.render(mv.getModel(),request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
