package th.mfu;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hi/{name}")
    String hiname(@PathVariable String name) {
        return name + " ไก่ๆๆๆๆๆๆๆ กุ้กๆๆๆๆๆ";
    }
   
}
