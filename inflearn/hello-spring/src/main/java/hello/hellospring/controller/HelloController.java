package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }
    @GetMapping("/hello-mvc")
    public String helloMvx(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);   
        return "hello-template";
    }
    @GetMapping("/hello-string")
    @ResponseBody // http body부분에 이 부분을 직접 넣어 주겠다. view가 없이 그래도 문자가 내려감.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
