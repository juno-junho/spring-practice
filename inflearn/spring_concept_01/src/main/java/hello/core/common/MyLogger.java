package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {


    private String uuid;
    private String requestURL;

    // requestURL은 bean이 생성되는 시점에 알 수 없기때문에 setter로 외부에서 받음.
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        // 고객요청 들어올때. 호출 필요하다 달라하면 호출
        uuid = UUID.randomUUID().toString(); // 겹치지 않는다. (다른 http 요청과 구분 할 수 있다)
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    // 고객요청이 서버에서 빠져나가면 소멸되기전 호출
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
