package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //autowired
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody   // 화면이 없기 때문에 문자 바로 반환(http 메세지에 데이터를 바로 출력하는 기능)
   public String logDemo(HttpServletRequest request) throws InterruptedException { //고객 요청 정보를 받을 수 있다.
        String requestURL = request.getRequestURL().toString(); // 고객이 어떤 url로 요청했는지 알 수 있다.

        MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId"); // service에서도 호출해 보는 것.
        return "OK";
    }
}
