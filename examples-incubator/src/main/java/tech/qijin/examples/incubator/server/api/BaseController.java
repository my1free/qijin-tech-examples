package tech.qijin.examples.incubator.server.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michealyang
 * @date 2019-12-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@RestController
public class BaseController {
    @RequestMapping("/alive")
    public String alive() {
        return "OK";
    }
}
